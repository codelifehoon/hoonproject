package socialUp.service.content;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.ImgProcUtil;
import socialUp.common.util.NumUtil;
import socialUp.service.common.dao.ReadContentDAO;
import socialUp.service.common.dao.ReadRssContentDAOImpl;
import socialUp.service.common.dto.BaseDTO;
import socialUp.service.content.dao.ContentBranchDAO;
import socialUp.service.content.dao.ContentBranchDAOImpl;
import socialUp.service.content.dao.ContentCollectDAO;
import socialUp.service.content.dao.ContentCollectDAOImpl;
import socialUp.service.content.dao.ContentDtlCommentDAO;
import socialUp.service.content.dao.ContentDtlCommentDAOImpl;
import socialUp.service.content.dao.ContentDtlTblDAO;
import socialUp.service.content.dao.ContentDtlTblDAOImpl;
import socialUp.service.content.dao.ContentSourceTblDAOImpl;
import socialUp.service.content.dao.ContentSourceTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAOImpl;
import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentCollectDTO;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;
import socialUp.service.content.dto.UploadFilesDTO;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;
import org.apache.log4j.Logger;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

public class ContentServiceImpl implements ContentService 
{
	public Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 회원의 등록된 컨텐츠 소스를 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO> selectContentSourceTbl(ContentSourceTblDTO contentSourceParam) throws Exception
	{

		log.debug("selectContentSourceTbl 시작");
		
		List<ContentSourceTblDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
		
		// 회원테이블 조회용 객체 생성
		ContentSourceTblDAO contentSourceTblDAO = (ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
		contentSourceTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		resultList = contentSourceTblDAO.selectContentSourceTbl(contentSourceParam);
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		
		return resultList;
	}
	
	/**
	 * 회원의 최초 고리정보를 생성한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String addMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam , String branchTtno) throws Exception
	{
		String resultVal = "";
		
		
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		int tt_No = 0;			// 컨텐츠 타이틀 일련번호
		
		try
		{

		ContentTitleListTblDAO 	contentTitleListTblDAO 	= (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		ContentSourceTblDAO 	contentSourceTblDAO 	= (ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
		ContentBranchDAO		contentBranchDAO 		= (ContentBranchDAO)DaoFactory.createDAO(ContentBranchDAOImpl.class);
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);
		contentSourceTblDAO.setSqlSesstion(sqlMap);
		contentBranchDAO.setSqlSesstion(sqlMap);

		// content_title_list_tbl 생성
		tt_No = contentTitleListTblDAO.insertContentTitleListTbl(contentTitleListParam);
		
		// content_source_tbl 생성
		for (int i=0;i<contentSourceArr.size();i++)
		{
			ContentSourceTblDTO contentSource = contentSourceArr.get(i);
			contentSource.setTt_no(String.valueOf(tt_No));
			contentSource.setRead_fail_count("0");
			contentSource.setNext_prod_dt(contentTitleListParam.getCreate_dt());
			
			contentSourceTblDAO.insertContentSourceTbl(contentSource);
		}
		
		// 컨텐츠 소속테이블에 자신을 소속 시킨다.
		ContentJoinMemDTO contentJoinMemDTO = new ContentJoinMemDTO();
		
		contentJoinMemDTO.setTt_no(String.valueOf(tt_No));
		contentJoinMemDTO.setMt_no(contentTitleListParam.getMt_no());
		contentJoinMemDTO.setStat("01");				// 가입완료		
		contentJoinMemDTO.setMt_grade("00");		// 소유자
		contentJoinMemDTO.setCreate_dt(contentTitleListParam.getCreate_dt());
		contentJoinMemDTO.setCreate_no(contentTitleListParam.getCreate_no());
		
		contentTitleListTblDAO.insertContentTitleListTbl(contentJoinMemDTO);
		

		// 자신의브랜치 정보에 자신의 컨텐츠 타이틀을 등록한다.
		ContentBranchDTO contentBranchParam = new ContentBranchDTO();
			
		contentBranchParam.setTt_no(String.valueOf(tt_No));					// 신규생성된 컨텐츠 타이틀
		contentBranchParam.setMt_no(contentTitleListParam.getMt_no());		// 회원번호
		contentBranchParam.setBelong_tt_no(String.valueOf(tt_No));					// 신규생성된 컨텐트에 브랜치될 컨텐츠타이틀
		contentBranchParam.setUse_yn("Y");
		contentBranchParam.setCreate_dt(contentTitleListParam.getCreate_dt());
		contentBranchParam.setCreate_no(contentTitleListParam.getCreate_no());

		contentBranchDAO.insertContentBranch(contentBranchParam);
		
		
		
		// 브랜치 가져올 컨텐츠 타이틀이 존재하면 브랜치 정보를 추가한다.
		if (!"".equals(branchTtno))
		{
			contentBranchParam = new ContentBranchDTO();
			
			contentBranchParam.setTt_no(String.valueOf(tt_No));					// 신규생성된 컨텐츠 타이틀
			contentBranchParam.setMt_no(contentTitleListParam.getMt_no());		// 회원번호
			contentBranchParam.setOrgBranchTtNo(branchTtno);					// 신규생성된 컨텐트에 브랜치될 컨텐츠타이틀
			contentBranchParam.setCreate_dt(contentTitleListParam.getCreate_dt());
			contentBranchParam.setCreate_no(contentTitleListParam.getCreate_no());
			
			contentBranchDAO.insertContentNewBranch(contentBranchParam);
			
		}
		
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		
		return resultVal;
	}
	
	
	/**
	 * 회원의 등록된 타이틀목록을  가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentTitleTblDTO> selectContentTitleList(ContentTitleTblDTO contentTitleListParam) throws Exception
	{

		log.debug("selectContentSourceTbl 시작");
		
		List<ContentTitleTblDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
		
		// 회원테이블 조회용 객체 생성
		ContentTitleListTblDAO contentTitleListTblDAO = (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		contentTitleListTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		resultList = contentTitleListTblDAO.selectContentTitleListTbl(contentTitleListParam);
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		return resultList;
	}

	/**
	 * 회원의 고리정보를 update 한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public String  updtaeMemContent(List<ContentSourceTblDTO> contentSourceArr ,ContentTitleTblDTO contentTitleListParam) throws Exception
	{
		String resultVal ="";
		log.debug("updtaeMemContent 시작");
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try{
		
		
		// 회원테이블 조회용 객체 생성
		ContentTitleListTblDAO 	contentTitleListTblDAO 	= (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		ContentSourceTblDAO 	contentSourceTblDAO 	= (ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		contentSourceTblDAO.setSqlSesstion(sqlMap);
		
		// 타이틀 정보 업데이트
		contentTitleListTblDAO.updateContentTitle(contentTitleListParam);
		
		// 컨텐츠 소스 정보 업데이트
		for (int i=0;i<contentSourceArr.size();i++)
		{
			contentSourceTblDAO.updateContentSource(contentSourceArr.get(i));
		}
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return resultVal;
	}
	


	/**
	 * 컨텐츠 소스정보를 이용해서 자료를 수집하고 등록한다.
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * 		사용되는필드
	 * 		contentSource.getTt_no()
			contentSource.getCs_no()
			contentSource.getMt_no()
			contentSource.getSource_kind()
			contentSource.getCreate_dt()
			contentSource.getSource_kind()
			contentSource.getRss2_url()
			contentSource.getContentCollect().getColl_no()
	 * @return
	 * @throws Exception
	 */
	public String  addContentCollect(ContentSourceTblDTO contentSource) throws Exception
	{
		log.debug("addContentCollect 시작");
		
		String resultVal ="";
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// DAO 생성
			List <ContentDtlTblDTO> contentDtlList  = null;		// 수집된 결과 리스트.
			ReadContentDAO readContentDAO = null;						// 컨텐츠 수집객체
			ContentCollectDAO 	contentCollectDAO 	=(ContentCollectDAO)DaoFactory.createDAO(ContentCollectDAOImpl.class);
			ContentDtlTblDAO	contentDtlTblDAO	=(ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			ContentSourceTblDAO	contentSourceTblDAO	=(ContentSourceTblDAO)DaoFactory.createDAO(ContentSourceTblDAOImpl.class);
			
			// DB 설정
			contentCollectDAO.setSqlSesstion(sqlMap);
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			contentSourceTblDAO.setSqlSesstion(sqlMap);
			
			
			
			// 데이터 수집객체 생성
			// RSS,ATOM 정보일때
			if ("01".equals(contentSource.getSource_kind())) readContentDAO = (ReadContentDAO)DaoFactory.createDAO(ReadRssContentDAOImpl.class);
			
			
			
			
			contentDtlList = readContentDAO.readContent(contentSource);		// 컨텐츠를 읽은후  contentSource에 원본내용을 저장한다.
			
			// RSS,ATOM 정보인데 읽은 목록이 없다면 실패처리
			if ("01".equals(contentSource.getSource_kind()) && contentDtlList == null) 
			{
				log.debug("Source_kind:" +contentSource.getSource_kind() +"읽기실패." );
				
				// 수집 테이블에 실패코드 설정
				ContentCollectDTO contentCollectDTO = new ContentCollectDTO();
				
				contentCollectDTO.setColl_no(contentSource.getContentCollect().getColl_no());
				contentCollectDTO.setProc_stop("04");			// 수집실패
				contentCollectDTO.setCreate_dt(contentSource.getCreate_dt());
				contentCollectDAO.updateContentCollect(contentCollectDTO);
				
				// 컨텐츠 소스에 실패카운트 추가
				ContentSourceTblDTO contentSourceParam = new ContentSourceTblDTO();
				
				contentSourceParam.setCs_no(contentSource.getCs_no());
				contentSourceParam.setRead_fail_count("ai"); 	// 실패카운트를 자동으로 증가시킴
				contentSourceParam.setLast_proc_dt(contentSource.getCreate_dt());
				contentSourceParam.setNext_prod_dt(DateTime.addSeconds(contentSource.getCreate_dt(), 60*10, "yyyyMMddHHmmss"));
				contentSourceParam.setCreate_dt(contentSource.getCreate_dt());
				contentSourceParam.setCreate_no("0");
				contentSourceTblDAO.updateContentSource(contentSourceParam);
				
			}
			// 수집에 성공 했을때
			else
			{
				log.debug("Source_kind:" +contentSource.getSource_kind() +"읽기성공." );
				
					// 원본을 수집 테이블에 기록한다.
					{
						
						ContentCollectDTO contentCollectDTO = new ContentCollectDTO();
						
						contentCollectDTO.setColl_no(contentSource.getContentCollect().getColl_no());
						contentCollectDTO.setRow_data(contentSource.getContentCollect().getRow_data());		//readContent 메서드에서 set 처리됨 
						contentCollectDTO.setProc_stop("01");			// 수집완료
						contentCollectDTO.setCreate_dt(contentSource.getCreate_dt());
						contentCollectDAO.updateContentCollect(contentCollectDTO);
					}
					
					
					// 상세수집정보를 컨텐츠 상세 테이블에 기록한다.
					for (int i=0;i<contentDtlList.size();i++)
					{
						ContentDtlTblDTO contentDtlTblDTO = contentDtlList.get(i);
						
						contentDtlTblDTO.setTt_no(contentSource.getTt_no());
						contentDtlTblDTO.setCs_no(contentSource.getCs_no());
						contentDtlTblDTO.setMt_no(contentSource.getMt_no());
						contentDtlTblDTO.setSource_kind(contentSource.getSource_kind());
						contentDtlTblDTO.setCreate_dt(contentSource.getCreate_dt());
						contentDtlTblDTO.setCreate_no("0");
						contentDtlTblDTO.setUpdate_dt(contentSource.getCreate_dt());
						contentDtlTblDTO.setUpdate_no("0");
						
						contentDtlTblDAO.compareInsertContentDtl(contentDtlTblDTO);
						
					}
					
					// 수집테이블에 상세수집정보를 컨텐츠 상세 테이블에 등록완료된 내용을 기록함
					{
						ContentCollectDTO contentCollectDTO = new ContentCollectDTO();
						
						contentCollectDTO.setColl_no(contentSource.getContentCollect().getColl_no());
						contentCollectDTO.setProc_stop("02");			// 분석완료(등록완료)
						contentCollectDTO.setCreate_dt(contentSource.getCreate_dt());
						contentCollectDAO.updateContentCollect(contentCollectDTO);
					}
					
					// 컨텐츠 소스에 최종수집일 다음 수집일 수정
					{
						ContentSourceTblDTO contentSourceParam = new ContentSourceTblDTO();
						
						contentSourceParam.setCs_no(contentSource.getCs_no());
						contentSourceParam.setRead_fail_count("0");							// 수집했을경우 실패횟수 0으로 초기화
						contentSourceParam.setLast_proc_dt(contentSource.getCreate_dt());
						contentSourceParam.setNext_prod_dt(DateTime.addSeconds(contentSource.getCreate_dt(), 60*10, "yyyyMMddHHmmss"));
						contentSourceParam.setCreate_dt(contentSource.getCreate_dt());
						contentSourceParam.setCreate_no("0");
						contentSourceTblDAO.updateContentSource(contentSourceParam);
					
					}
			}
			
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return resultVal;
	}
	
	
	/**
	 * 사이트에서 신규컨텐츠 상세를 직업 등록한다.
	 * 
	 * @param contentDtlParam 컨텐츠 소스정보
	 * 		사용되는필드
	 * 		contentDtlTblDTO.setTt_no
			contentDtlTblDTO.setMt_no
			contentDtlTblDTO.setContent_title
			contentDtlTblDTO.setContent_desc
			contentDtlTblDTO.setSource_kind
			contentDtlTblDTO.setUse_yn
			contentDtlTblDTO.setCreate_dt
			contentDtlTblDTO.setCreate_no
	 * 		
	 * @return
	 * @throws Exception
	 */
	public String  insertContentDtl(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("insertContentDtl 시작");
		
		String resultVal ="";
		
		// sql session 생성 
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// DAO 생성
			List <ContentDtlTblDTO> contentDtlList  = null;		// 수집된 결과 리스트.
			ReadContentDAO readContentDAO = null;						// 컨텐츠 수집객체
			ContentDtlTblDAO		contentDtlTblDAO		=(ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			ContentDtlCommentDAO	contentDtlCommentDAO	=(ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
			
			// DB 설정
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			contentDtlCommentDAO.setSqlSesstion(sqlMap);
			
			contentDtlTblDAO.insertContentDtl(contentDtlParam);
			
			// 등록된글에 이미지가 존재한다면 썸네일 생성
			int imgRegCount = contentDtlCommentDAO.extractRegContentDtlThumbNail(contentDtlParam);
			
			log.debug("썸네일이미지수:" + imgRegCount);
					
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return resultVal;
	}
	
	
	/**
	 * 사이트에서 신규컨텐츠 상세를 직업 등록한다.
	 * 
	 * @param contentDtlParam 컨텐츠 소스정보
	 * 		사용되는필드
	 * 		contentDtlTblDTO.setCd_no
			contentDtlTblDTO.setMt_no
			contentDtlTblDTO.setContent_title
			contentDtlTblDTO.setContent_desc
			contentDtlTblDTO.setUse_yn
			contentDtlTblDTO.setCreate_dt
			contentDtlTblDTO.setCreate_no
	 * 		
	 * @return
	 * @throws Exception
	 */
	public String  updateContentDtl(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("updateContentDtl 시작");
		
		String resultVal ="";
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// DAO 생성
			List <ContentDtlTblDTO> contentDtlList  = null;		// 수집된 결과 리스트.
			ReadContentDAO readContentDAO = null;						// 컨텐츠 수집객체
			ContentDtlTblDAO		contentDtlTblDAO	=(ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			ContentDtlCommentDAO	contentDtlCommentDAO	=(ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
			
			// DB 설정
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			contentDtlCommentDAO.setSqlSesstion(sqlMap);
			
			contentDtlTblDAO.updateContentDtl(contentDtlParam);
			
			// 등록된글에 이미지가 존재한다면 썸네일 생성
			int imgRegCount =  contentDtlCommentDAO.extractRegContentDtlThumbNail(contentDtlParam);
			log.debug("썸네일이미지수:" + imgRegCount);
			
					
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return resultVal;
	}
	

	/**
	 * 수집대상이 되는 컨텐츠의 리스트
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO>  selectContentCollectWaitList() throws Exception
	{
		log.debug("contentCollectWaitList 시작");
		
		List<ContentSourceTblDTO>  contentSourceList =  null;
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// DAO 생성
			
			ContentCollectDAO 	contentCollectDAO 	= (ContentCollectDAO)DaoFactory.createDAO(ContentCollectDAOImpl.class);
			
			// DB 설정
			contentCollectDAO.setSqlSesstion(sqlMap);
			contentSourceList = contentCollectDAO.selectContentCollectWaitList();
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return contentSourceList;
	}
	
	/**
	 * 게시물 조회목록
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO  selectContentDtlPageList(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("selectContentDtlPageList 시작");
		
		ContentTitleTblDTO  contentTitle =  null;
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// DAO 생성
			ContentDtlTblDAO 		contentDtlTblDAO 	= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			ContentTitleListTblDAO 	contentTitleListTblDAO = (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
			ContentTitleTblDTO 		contentTitleTblDTO 	= new ContentTitleTblDTO();
			
			// DB 설정
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			contentTitleListTblDAO.setSqlSesstion(sqlMap);
			
			// 조회목록의  title 정보를 가져온다.
			{
				contentTitleTblDTO.setTt_no(contentDtlParam.getTt_no());
				 List<ContentTitleTblDTO>  contentTitleList = contentTitleListTblDAO.selectContentTitleListTbl(contentTitleTblDTO);
				 if (contentTitleList.size() == 0) throw new Exception("컨텐츠 타이틀이 존재하지 않습니다.");
				 contentTitle = contentTitleList.get(0);
					
			}
			
			contentTitle.setContentDtlList(contentDtlTblDAO.selectContentDtlPageList(contentDtlParam)) ;
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return contentTitle;
	
		
	}
	

	/**
	 * 컨텐츠 상세정보를 볼수 있는 권한이 있는지 확인
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * 		contentDtl.cd_no
	 * 		contentDtl.mt_no
	 * @return
	 * @throws Exception
	 */
	public boolean  isContentDtlViewAuth(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		boolean retVal ;
		try
		{
			ContentDtlTblDAO 		contentDtlTblDAO 	= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			if ( contentDtlTblDAO.isContentDtlView(contentDtlParam).size() > 0) retVal = true;
			else retVal = false;
			
			sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return retVal;
	}
	
	/**
	 * 게시물 상세내용
	 * 
	 * @param ContentDtlTblDTO 컨텐츠 상세정보
	 * 			contentDtl.getCd_no();
	 * 			contentDtl.getMt_no();
	 * 
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO  selectContentDtlView(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("selectContentDtlView 시작");
		
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		ContentTitleTblDTO 		contentTitle	 	= null;
		
		try
		{
			// DAO 생성
			ContentDtlTblDAO 		contentDtlTblDAO 	= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
			ContentTitleListTblDAO 	contentTitleListTblDAO = (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
			ContentTitleTblDTO 		contentTitleParam  	= new ContentTitleTblDTO();
			
			
			// DB 설정
			contentDtlTblDAO.setSqlSesstion(sqlMap);
			contentTitleListTblDAO.setSqlSesstion(sqlMap);

			// 컨텐츠 상세 정보를 가져온다.
			List <ContentDtlTblDTO> contentDtlList = contentDtlTblDAO.selectContentDtl(contentDtlParam);
			if ( contentDtlList.size() <=0 ) throw new Exception ("조회를 원하는 컨텐츠 상세값이 존재하지 않습니다.");

			// 컨텐츠 타이틀 가져오기
			contentTitleParam.setTt_no(contentDtlList.get(0).getTt_no());
			contentTitle =  contentTitleListTblDAO.selectContentTitleListTbl(contentTitleParam).get(0);


			// 컨텐츠 타이틀에 해당하는 상세정보를 같이 넘겨준다.
			contentTitle.setContentDtlList(contentDtlList);
			
			//해당 게시물을 읽는것이기 때문에 읽기카운트를 1 더해준다.
			if (contentDtlParam.getHit_flag() != null && !"".equals(contentDtlParam.getHit_flag()))
			{
				ContentDtlTblDTO contentDtlParam2 = new ContentDtlTblDTO();
				
				contentDtlParam2.setCd_no(contentDtlParam.getCd_no());
				contentDtlParam2.setHit_flag(contentDtlParam.getHit_flag());
				
				contentDtlTblDAO.updateContentDtl(contentDtlParam2);
			}

		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return contentTitle;
	
		
	}
	
	

	/**
	 * 컨텐츠 상세의 댓글을 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlCommentDTO> selectContentDtlCommentList(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{

		log.debug("selectContentDtlCommentList 시작");
		
		List<ContentDtlCommentDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
		
		// 회원테이블 조회용 객체 생성
		ContentDtlCommentDAO contentDtlCommentDAO = (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		contentDtlCommentDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		resultList = contentDtlCommentDAO.selectContentDtlComment(contentDtlCommentParam);
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		
		
		return resultList;
	}
	
	/**
	 * 컨텐츠 상세의 댓글을 등록한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public void insertContentDtlCommentList(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{

		log.debug("insertContentDtlCommentList 시작");
		
		List<ContentDtlCommentDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
		
		// 컨텐츠 상세댓글 댓글
		ContentDtlCommentDAO 	contentDtlCommentDAO 	= (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		ContentDtlTblDAO 		contentDtlTblDAO 		= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
		ContentDtlTblDTO 		contentDtlParam			= new ContentDtlTblDTO();
		
		contentDtlCommentDAO.setSqlSesstion(sqlMap);			// sql session 설정
		contentDtlTblDAO.setSqlSesstion(sqlMap);
		
		
		// 컨텐츠 상세댓글등록
		contentDtlCommentDAO.insertContentDtlComment(contentDtlCommentParam);
		
		// 댓글등록후 댓글 카운트 +1
		contentDtlParam.setCd_no(contentDtlCommentParam.getCd_no());
		contentDtlParam.setCdc_flag("+1");
		contentDtlTblDAO.updateContentDtl(contentDtlParam);
		
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
	}
	
	/**
	 * 컨텐츠 상세의 댓글을 수정한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public void updateContentDtlComment(ContentDtlCommentDTO contentDtlCommentParam) throws Exception
	{

		log.debug("insertContentDtlCommentList 시작");
		
		List<ContentDtlCommentDTO> resultList = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
		
		// 컨텐츠 상세댓글 댓글
		ContentDtlCommentDAO 	contentDtlCommentDAO 	= (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		ContentDtlTblDAO 		contentDtlTblDAO 		= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
		ContentDtlTblDTO 		contentDtlParam			= new ContentDtlTblDTO();
		
		contentDtlCommentDAO.setSqlSesstion(sqlMap);			// sql session 설정
		contentDtlTblDAO.setSqlSesstion(sqlMap);
		
		// 컨텐츠 상세댓글등록
		contentDtlCommentDAO.updateContentDtlComment(contentDtlCommentParam);
		
		// 댓글을 삭제할때는 카운트 -1
		if ("N".equals(contentDtlCommentParam.getUse_yn()))
		{
			contentDtlParam.setCd_no(contentDtlCommentParam.getCd_no());
			contentDtlParam.setCdc_flag("-1");
			contentDtlTblDAO.updateContentDtl(contentDtlParam);
		}
		
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
	}
	
	/**
	 * 타이틀 컨텐츠가 가지고있는 브렌치 정보를 가져온다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public ContentTitleTblDTO selectContentBranch(ContentTitleTblDTO contentTitleParam) throws Exception
	{
		

		log.debug("selectContentBranch 시작");
		
		ContentTitleTblDTO  contentTitleTblDTO = null;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
		
		// 컨텐츠 브랜치 정보 가져오기
		ContentTitleListTblDAO 	contentTitleListTblDAO 	= (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		ContentBranchDAO 		contentBranchDAO 	= (ContentBranchDAO)DaoFactory.createDAO(ContentBranchDAOImpl.class);
		ContentBranchDTO 		contentBranchParam  = new ContentBranchDTO();
		
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		contentBranchDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		contentBranchParam.setTt_no(contentTitleParam.getTt_no());
		
		
		// 컨첸츠 타이틀정보
		contentTitleTblDTO =  contentTitleListTblDAO.selectContentTitleListTbl(contentTitleParam).get(0);
		
		
		// 컨텐츠 브랜치 정보
		contentTitleTblDTO.setContentBranchList(contentBranchDAO.selectContentBranch(contentBranchParam));
		
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return contentTitleTblDTO;
	}
	
	
	
	/**
	 * 나의 컨텐츠 타이틀에 다른 컨텐츠 타이틀을 브랜치 따서 추가한다.
	 * 
	 * 
	 * contentBranchParam.setTt_no(tt_no);					// 브랜치 획득할 컨텐츠 타이틀
		contentBranchParam.setMt_no(authInfo.getMt_no());	// 브랜치(브랜치 소유주) 획득할 회원번호
		contentBranchParam.setOrgBranchTtNo(refTtNo);		// 브랜치 대상 컨텐츠 타이틀
		contentBranchParam.setCreate_dt(sCurrentDate);
		contentBranchParam.setCreate_no(authInfo.getMt_no());
		
	 * @throws Exception
	 */
	public ContentBranchDTO insertContentNewBranch(ContentBranchDTO contentBranchParam) throws Exception
	{
		
		log.debug("insertContentNewBranch 시작");
		
		ContentBranchDAO 		contentBranchDAO 	= (ContentBranchDAO)DaoFactory.createDAO(ContentBranchDAOImpl.class);
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentBranchDAO.setSqlSesstion(sqlMap);
		
		try
		{
			// 브랜치를 가져온것이 있는지 확인해야한다.(해당 부분은 쿼리에서 기존 브랜치가 있는지 확인함)

			//  브랜치 정보를 추가한다.
			contentBranchDAO.insertContentNewBranch(contentBranchParam);
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		
		return contentBranchParam;
	}

	/**
	 * 컨텐츠 상세 검색 결과를 리턴한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> SearchContentDtl(SearchDTO searchParam) throws Exception
	{
		log.debug("searchContentDtl 시작");
		
		List<ContentDtlTblDTO> contentDtlList = null;  
		ContentDtlTblDAO	contentDtlTblDAO 	= (ContentDtlTblDAO)DaoFactory.createDAO(ContentDtlTblDAOImpl.class);
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentDtlTblDAO.setSqlSesstion(sqlMap);
		
		try
		{

			//  브랜치 정보를 추가한다.
			contentDtlList = contentDtlTblDAO.SearchContentDtl(searchParam);
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		
		return contentDtlList;
		
	}

	/**
	 * 컨텐츠타이틀 참여회원조회
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public List<ContentJoinMemDTO> selectContentJoinMemList(ContentJoinMemDTO contentJoinMemParam) throws Exception 
	{
		log.debug("searchContentDtl 시작");
		
		List<ContentJoinMemDTO> contentJoinMemList = null;  
		ContentTitleListTblDAO	contentTitleListTblDAO 	= (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);
		
		try
		{

			//   컨텐츠타이틀 참여회원조회
			contentJoinMemList = contentTitleListTblDAO.selectContentJoinMemList(contentJoinMemParam);
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		
		return contentJoinMemList;
		
	}
	
	/**
	 * 컨텐츠타이틀  다른 회원 참여처리
	 * 
	 * @param contentJoinMem
	 * @return
	 * @throws Exception
	 */
	public ContentTitleTblDTO addContentJoin(ContentTitleTblDTO contentTitleParam) throws Exception 
	{
		log.debug("addContentJoin 시작");
		
		
		
		ContentTitleListTblDAO	contentTitleListTblDAO 	= (ContentTitleListTblDAO)DaoFactory.createDAO(ContentTitleListTblDAOImpl.class);
		ContentTitleTblDTO	contentTitle  = null;
		ContentJoinMemDTO	contentJoinMem = new ContentJoinMemDTO();
		

		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentTitleListTblDAO.setSqlSesstion(sqlMap);
		
		try
		{

			// 기존에 가입처리되어있는게 있는지 확인
			{
				ContentJoinMemDTO	contentJoinMemParam = new ContentJoinMemDTO();
				
				contentJoinMemParam.setTt_no(contentTitleParam.getTt_no());
				contentJoinMemParam.setMt_no(contentTitleParam.getCreate_no());
				
				
				// 기존에 가입되어 있다면 오류처리 확인한다.
				if ( selectContentJoinMemList(contentJoinMemParam).size() > 0) 
				{
					throw new Exception("이미 가입 처리가  되고 있습니다.");
				}
			}
			
			//   컨텐츠타이틀 참여회원조회
			contentTitle = selectContentTitleList(contentTitleParam).get(0);
			
			if ( "Y".equals(contentTitle.getOrder_mem_join_yn()))
			{
				
				// 참여자유
				if ("01".equals(contentTitle.getOrder_mem_join_metd()))
				{
					contentJoinMem.setTt_no(contentTitleParam.getTt_no());
					contentJoinMem.setMt_no(contentTitleParam.getCreate_no());
					contentJoinMem.setStat("01");		// 가입완료 
					contentJoinMem.setMt_grade("01");	// 회원등급:참여자
					contentJoinMem.setCreate_dt(contentTitleParam.getCreate_dt());
					contentJoinMem.setCreate_no(contentTitleParam.getCreate_no());

					contentTitleListTblDAO.insertContentJoinMem(contentJoinMem);
					
				}
				// 비밀번호방식
				else if ("02".equals(contentTitle.getOrder_mem_join_metd()))
				{

					
					if (!contentTitle.getOrder_mem_join_passwd().equals(contentTitleParam.getOrder_mem_join_passwd()))
					{
						// 참여에 필요한 비밀번호가 맞지않습니다
						contentTitle.setRetCode("-1");			// 실패
						contentTitle.setRetDetCode("-1000");	// 상세코드
						
					}
					else
					{
						contentJoinMem.setTt_no(contentTitleParam.getTt_no());
						contentJoinMem.setMt_no(contentTitleParam.getCreate_no());
						contentJoinMem.setStat("01");		// 가입완료 
						contentJoinMem.setMt_grade("01");	// 회원등급:참여자
						contentJoinMem.setCreate_dt(contentTitleParam.getCreate_dt());
						contentJoinMem.setCreate_no(contentTitleParam.getCreate_no());
	
						contentTitleListTblDAO.insertContentJoinMem(contentJoinMem);
					}
					
				}
				// 참여신청확인
				else if ("03".equals(contentTitle.getOrder_mem_join_metd()))
				{
					contentJoinMem.setTt_no(contentTitleParam.getTt_no());
					contentJoinMem.setMt_no(contentTitleParam.getCreate_no());
					contentJoinMem.setStat("00");		// 가입완료 
					contentJoinMem.setMt_grade("01");	// 회원등급:참여자
					contentJoinMem.setCreate_dt(contentTitleParam.getCreate_dt());
					contentJoinMem.setCreate_no(contentTitleParam.getCreate_no());

					contentTitleListTblDAO.insertContentJoinMem(contentJoinMem);
					
				}
				
			}
			else  throw new Exception("참여가 불가능한 컨텐츠 타이틀 입니다. ");
			
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		return contentTitle;
	}
	

	
	/**
	 * 나의 컨텐츠가 내가 참여를 원하는 퀀텐츠 목록에 브랜치로 등록되어 있는지확인한다.
	 * 
	 * @param contentJoinMem
	 * tt_no :  참여를 원하는 퀀텐츠 목록 번호 
 	   mt_no :  tt_no에 소속되어 있는 컨텐츠중 mt_no에 속한 컨텐츠 목록을 뽑아온다.
	 * @return
	 * @throws Exception
	 */
	public List<ContentBranchDTO> selectContentBranchSelfJoinList(ContentBranchDTO contentBranchParam) throws Exception 
	{
		log.debug("selectContentBranchSelfJoinList 시작");
		
		
		ContentBranchDAO		contentBranchDAO 	= (ContentBranchDAO)DaoFactory.createDAO(ContentBranchDAOImpl.class);
		List<ContentBranchDTO>	contentBranchList 	= null; 
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		contentBranchDAO.setSqlSesstion(sqlMap);
		
		try
		{

			//   컨텐츠타이틀 참여회원조회
			contentBranchList = contentBranchDAO.selectContentBranchSelfJoinList(contentBranchParam);
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		
		return contentBranchList;
		
	}
	

	/**
	 * 나의 컨텐츠를 다른사람의 컨텐츠 타이틀 브랜치 정보에 스스로 참여시킨다.
	 * 
	 * addContentBranchParam : 참여원하는 브랜치에 추가 대상 컨텐츠타이틀
	 * delContentBranchParam : 브랜치에 삭제 대상 컨텐츠타이틀
	 * @throws Exception
	 */
	public void insertContentBranchSelfJoin(ContentBranchDTO addContentBranchParam,ContentBranchDTO delContentBranchParam) throws Exception
	{
		
		log.debug("insertContentBranchSelfJoin 시작");
		
		ContentBranchDAO 		contentBranchDAO 	= (ContentBranchDAO)DaoFactory.createDAO(ContentBranchDAOImpl.class);
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentBranchDAO.setSqlSesstion(sqlMap);
		
		try
		{

			// 브랜치 정보를 삭제한다.
			if (delContentBranchParam.getOrgBranchTtNos().length > 0) contentBranchDAO.deleteContentBranchSelfJoins(delContentBranchParam);
			
			
			//  브랜치 정보를 추가한다.			
			if (addContentBranchParam.getOrgBranchTtNos().length > 0) contentBranchDAO.insertContentNewBranchs(addContentBranchParam);
			
			sqlMap.commit();
		}
		

		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
	}
	

	/**
	 * 게시물 조회목록
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public SyndFeed  makeFeedRss(ContentDtlTblDTO contentDtlParam) throws Exception
	{
		log.debug("makeFeedRss 시작");
		
		// 리턴될 feed값
		SyndFeed syndFeed = new SyndFeedImpl();
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try
		{
			// 해당 게시물에 대해서 RSS를 생성하기 위해서 글을 읽는다.
			ContentTitleTblDTO  contentTitle =  selectContentDtlPageList(contentDtlParam);
			
			List<SyndEntry> entries = new ArrayList<SyndEntry>();
			
			// rss 목록 묶음의 정보
			syndFeed.setFeedType("rss_2.0");
			syndFeed.setTitle(contentTitle.getTitle_name());				// 묶음의 타이틀
			syndFeed.setDescription(contentTitle.getTitle_name());		// 묶음의 설명
			syndFeed.setLink("http://goreee.com/content/contentDtlList.action?tt_no=" + contentTitle.getTt_no());				// 묶음의 목록링크
			
			for (int i =0;i<contentTitle.getContentDtlList().size();i++) 
			{
				ContentDtlTblDTO contentDtl  =  contentTitle.getContentDtlList().get(i);
				 
				SyndEntry 	syndEntry 	= new SyndEntryImpl();		// row단위 묶음생성
				SyndContent description = new SyndContentImpl();	// 글 내용
				List<SyndCategory> categories =new ArrayList<SyndCategory>();	// 카테고리목록
				String[] categoryArray = CmnUtil.nvl(contentDtl.getCategories()).split("·");
				
				description.setType("text/html");
				description.setValue(contentDtl.getContent_desc());
				
				// 카테고리 목록생성
				for(String ctg : categoryArray) 
				{
				  SyndCategory syndCategory = new SyndCategoryImpl();
				  syndCategory.setName(ctg);
				  categories.add(syndCategory);
				  
				}
					
				syndEntry.setTitle(contentDtl.getContent_title());					// 글타이틀
				syndEntry.setLink(contentDtl.getContent_title_link());					// 글타이틀링크
				syndEntry.setDescription(description);	// 글내용
				syndEntry.setAuthor(contentDtl.getAuthor_nm());				// 글작성자
				//DateTime.format(entry.getPublishedDate(),"yyyyMMddHHmmss")
				//DateTime.StringToDate();
			
				syndEntry.setPublishedDate(DateTime.StringToDate(contentDtl.getOrg_create_dt(),"yyyy-MM-dd HH:mm:ss"));			// 작성일
				syndEntry.setCategories(categories);				// 카테고리목록
			
				entries.add(syndEntry);
			}
			
			syndFeed.setEntries(entries);
			
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {sqlMap.close();}
		
		return syndFeed;
	
		
	}
	
}
