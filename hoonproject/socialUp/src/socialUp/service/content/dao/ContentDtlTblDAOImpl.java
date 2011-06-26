package socialUp.service.content.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.util.ConvertHashMap;
import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.SearchDTO;

public class ContentDtlTblDAOImpl implements ContentDtlTblDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	/**
	 * 컨텐츠 상세정보 추가
	 * @param param
	 * @throws Exception
	 */
	public long insertContentDtl(ContentDtlTblDTO contentDtl) throws Exception
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentDtl", contentDtl);
		
		// 최근순 글을 보기 위해서 index desc 값을 만들기 위해서 update 1회실행
		{
			ContentDtlTblDTO contentDtl2 = new ContentDtlTblDTO();
			
			contentDtl2.setCd_no(contentDtl.getCd_no());
			contentDtl2.setCreate_no(contentDtl.getCreate_no());
			contentDtl2.setCreate_dt(contentDtl.getCreate_dt());
			contentDtl2.setRevers_index("set");
			updateContentDtl(contentDtl2);
		}
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(contentDtl.getCd_no());

	}

	/**
	 * 컨텐츠 상세정보  수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentDtl(ContentDtlTblDTO contentDtl) throws Exception
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentDtl", contentDtl);
		
	}

	/**
	 * 컨텐츠 상세정보  조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> selectContentDtl(ContentDtlTblDTO contentDtl) throws Exception   
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlList", contentDtl);
	}


	/**
	 * 컨텐츠 상세정보 추가(기존에 등록된 정보가있는지 확인하고 중복이 없다면 등록한다.
	 * 	field : content_title,org_create_dt 가 같은 값이 있는지 확인함.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public long compareInsertContentDtl(ContentDtlTblDTO contentDtl) throws Exception
	{
		
		List<ContentDtlTblDTO> resultList  = selectContentDtl(contentDtl);

		//  기존에 같은 글이 존재하지않으면 신규글로 등록한다.
		if (resultList.size() == 0)
		{
			this.sqlMap.insert("socialUp.service.content.mapper.compareInsertContentDtl", contentDtl);
			
			if (!"".equals(contentDtl.getCd_no()))
			{
				ContentDtlCommentDAO contentDtlCommentDAO = (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
				ContentDtlTblDTO contentDtl2 = new ContentDtlTblDTO();
				
				contentDtlCommentDAO.setSqlSesstion(this.sqlMap);
				
				contentDtl2.setCd_no(contentDtl.getCd_no());
				contentDtl2.setCreate_no(contentDtl.getCreate_no());
				contentDtl2.setCreate_dt(contentDtl.getCreate_dt());
				contentDtl2.setRevers_index("set");
				updateContentDtl(contentDtl2);
				
				// 본문 썸네일 대상 이미지 등록
				int retCount = contentDtlCommentDAO.extractRegContentDtlThumbNail(contentDtl);
				
				log.debug("썸네일카운트:" + retCount);
			}
			else throw new Exception("컨텐츠 수집배치에서 신규글 등록시 오류발생(글일련번호알수없음)");
			
			// 글등록후 일련번호를 넘겨준다.
			return NumUtil.toInt(contentDtl.getCd_no());
			
		}
		else
		{
			return 0;
		}
		
	}
	
	/**
	 * 컨텐츠 상세정보조회(게시물조회)
	 * 	field : content_title,org_create_dt 가 같은 값이 있는지 확인함.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO>  selectContentDtlPageList(ContentDtlTblDTO contentDtl) throws Exception
	{
		List<ContentDtlTblDTO> contentDtlList = null;
		
		
		contentDtlList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlPageList", contentDtl);
		Long tt  		=   (Long)this.sqlMap.selectOne("socialUp.service.common.mapper.selectFoundRows");
		
		for(int i=0;i<contentDtlList.size();i++)
		{
			contentDtlList.get(i).setAllRowNum(tt.intValue());
		}
		
		
		return contentDtlList;
		
		
	}
	
	/**
	 * 컨텐츠 상세정보를 볼수 있는 권한이 있는지 확인
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List isContentDtlView(ContentDtlTblDTO contentDtl) throws Exception   
	{
		
		/*
		<!-- 본인이 관리하는 컨텐크 타이틀 인가 비회원일겨우 무조건안되겠지 -->
		<!-- 해당게시물 소유주가  글고리 타사용자에게 공개를 했거나 order_mem_open_yn:Y -->
		<!-- 읽을려는 사용자가 해당 글고리의 참여자-->
		<!-- 자신이 생성한 글은 아니지만 지금조회하는 컨텐츠 텍스트의 브랜치 목록에 존재하면 읽을수있다. -->
		*/
		

		//Map<String, String> resultList = new HashMap<String, String>();
		List resultList = null;
		
		resultList = this.sqlMap.selectList("socialUp.service.content.mapper.isContentDtlView", contentDtl);
		
		return resultList;
	}
	
	

	/**
	 * 컨텐츠 상세 검색 결과를 리턴한다.
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlTblDTO> SearchContentDtl(SearchDTO searchParam) throws Exception   
	{
		
		/*
		<!-- 본인이 관리하는 컨텐크 타이틀 인가 비회원일겨우 무조건안되겠지 -->
		<!-- 해당게시물 소유주가  글고리 타사용자에게 공개를 했거나 order_mem_open_yn:Y -->
		<!-- 읽을려는 사용자가 해당 글고리의 참여자-->
		<!-- 자신이 생성한 글은 아니지만 지금조회하는 컨텐츠 텍스트의 브랜치 목록에 존재하면 읽을수있다. -->
		*/
		
		List resultList = null;
		
		resultList = this.sqlMap.selectList("socialUp.service.content.mapper.searchContentDtl", searchParam);
		
		return resultList;
	}
	
	
	
}