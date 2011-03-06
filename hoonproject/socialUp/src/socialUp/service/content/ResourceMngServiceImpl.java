package socialUp.service.content;

import java.util.HashMap;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.properties.PropertiesManager;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.ImgProcUtil;
import socialUp.common.util.NumUtil;
import socialUp.service.common.dao.ReadContentDAO;
import socialUp.service.common.dao.ReadRssContentDAOImpl;
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

public class ResourceMngServiceImpl implements ResourceMngService 
{
	public Logger log = Logger.getLogger(this.getClass());
	
	
	
	/**
	 * 업로드 파일정보 insert
	 * 
	 * @param param
	 * @throws Exception
	 */
	public long insertUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception  
	{
		
		log.debug("updateUploadFiles 시작");
		
		ContentDtlCommentDAO 		contentDtlCommentDAO 	= (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		long uf_id;
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentDtlCommentDAO.setSqlSesstion(sqlMap);
		
		try
		{

			uf_id = contentDtlCommentDAO.insertUploadFiles(uploadFilesParam) ;
			sqlMap.commit();
		}
	
		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		return uf_id;
		
	}
	
	
	/**
	 * 업로드 파일 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<UploadFilesDTO> selectUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception
	{

		log.debug("updateUploadFiles 시작");
		
		ContentDtlCommentDAO 		contentDtlCommentDAO 	= (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		List<UploadFilesDTO>		uploadFilesList = null;
		 
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentDtlCommentDAO.setSqlSesstion(sqlMap);
		
		try
		{

			uploadFilesList = contentDtlCommentDAO.selectUploadFiles(uploadFilesParam) ;
			sqlMap.commit();
		}
	
		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		return uploadFilesList;
		
		
	}
	
	
	/**
	 * 썸네일 대상 목록 가져오기
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgWaitList(ContentDtlImgDTO contentDtlImgParam) throws Exception
	{

		log.debug("selectContentDtlImgWaitList 시작");
		
		ContentDtlCommentDAO 		contentDtlCommentDAO 	= (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
		List<ContentDtlImgDTO>		contentDtlImgList = null;
		 
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		contentDtlCommentDAO.setSqlSesstion(sqlMap);
		
		try
		{
			contentDtlImgList = contentDtlCommentDAO.selectContentDtlImgWaitList(contentDtlImgParam) ;
			sqlMap.commit();
		}
	
		catch (Exception e)
			{
				sqlMap.rollback();
				e.printStackTrace();
				throw e;
			}
		finally {sqlMap.close();}
	
		return contentDtlImgList;
		
		
	}


	/**
	 * content_dtl_img 테이블에 있는 다운로드 대기 목록에서 있는 이미지를 가져와서
	 * 썸네일 이미지를 생성한다.
	 * 
	 * @param contentDtlImgparam
	 */
	public void contentDtlImgMakeThumbnail(ContentDtlImgDTO  contentDtlImgparam) throws Exception
	{
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		CmnUtil cmnUtil = new CmnUtil(); 
		String filePath = PropertiesManager.getProperty("file.defpath");;
		String tempFileName   = "";
		String thumbFileName   = "";
		
		try
		{
			
			ContentDtlCommentDAO contentDtlCommentDAO = (ContentDtlCommentDAO)DaoFactory.createDAO(ContentDtlCommentDAOImpl.class);
			contentDtlCommentDAO.setSqlSesstion(sqlMap);
			
			tempFileName  = filePath +  "/temp/" + contentDtlImgparam.getCdi_no();
			
			// 원본 파일임시  다운로드
			if (!CmnUtil.httpFileDownload(contentDtlImgparam.getImg_url(), tempFileName))
				{
					log.debug("address : " + contentDtlImgparam.getImg_url());
					log.debug("tempFileName : " + tempFileName);
					throw new Exception("contentDtlImgMakeThumbnail 파일다운로드 에러");
				}
			
			
			//썸네일 생성
			UploadFilesDTO soParam = new UploadFilesDTO();
			UploadFilesDTO taParam = new UploadFilesDTO();
			
			soParam.setFileFullName(tempFileName);							// 변경전 파일
			taParam.setFileFullName(contentDtlImgparam.getThumbnail_url());	// 변경후 파일
		 	
			ImgProcUtil.createResizeImg(soParam, taParam, 100);
			
			
			// 원본 파일삭제(굳이 실시간 삭제안하고  트래픽이 없을때 일괄삭제처리해도된다.)
			// log.debug("del-msg:"  + cmnUtil.runDeleteFile(tempFileName));
			
			
			//썸네일 수집상태변경
			ContentDtlImgDTO  contentDtlImgparam2 = new ContentDtlImgDTO();
			contentDtlImgparam2.setCdi_no(contentDtlImgparam.getCdi_no());
			contentDtlImgparam2.setProc_stat("02");	// 처리완료
			contentDtlImgparam2.setCreate_no(contentDtlImgparam.getCreate_no());
			contentDtlImgparam2.setCreate_dt(contentDtlImgparam.getCreate_dt());
			contentDtlCommentDAO.updateContentDtlImg(contentDtlImgparam2);
			
			
		}catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
	finally {sqlMap.close();}
		
		
		
	}
	
}
