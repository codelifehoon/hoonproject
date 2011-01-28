package socialUp.service.content.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.UploadFilesDTO;

public class ContentDtlCommentDAOImpl implements ContentDtlCommentDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}

	@Override
	public long insertContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentDtlComment", ContentDtlCommentParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(ContentDtlCommentParam.getCdc_no());
	}

	@Override
	public int updateContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentDtlComment", ContentDtlCommentParam);
		
	}

	@Override
	public List<ContentDtlCommentDTO> selectContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception 
	{

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlCommentList", ContentDtlCommentParam);
	}

	
	// 업로드 파일 테이블 관련
	
	@Override
	public long insertUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertUploadFiles", uploadFilesParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(uploadFilesParam.getUf_id());
	}

	@Override
	public int updateUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception 
	{
		return  this.sqlMap.update("socialUp.service.content.mapper.updateUploadFiles", uploadFilesParam);
		
	}

	@Override
	public List<UploadFilesDTO> selectUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception  
	{
		 List<UploadFilesDTO> uploadFilesList = null;

		 uploadFilesList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectUploadFilesList", uploadFilesParam);
		 Long tt  		=   (Long)this.sqlMap.selectOne("socialUp.service.common.mapper.selectFoundRows");
		 
		 for(int i=0;i<uploadFilesList.size();i++) uploadFilesList.get(i).setAllRowNum(tt.intValue());
			
		 
		 return uploadFilesList;
		
		
		
	}

	
}