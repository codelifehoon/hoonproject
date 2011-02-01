package socialUp.service.content;

import java.util.HashMap;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
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
	
		
}
