package socialUp.service.content.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.DebugUtil;
import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
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

	/**
	 * 글상세 이미지 목록 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long updateContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception 
	{

		return  this.sqlMap.update("socialUp.service.content.mapper.updateContentDtlImg", contentDtlImgParam);

	}
	
	/**
	 * 글상세 이미지 목록 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int insertContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception 
	{
		 
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentDtlImg", contentDtlImgParam);
		
		// 등록후 시퀀스를 넘겨준다.
		return NumUtil.toInt(contentDtlImgParam.getCdi_no());
	}
	
	/**
	 * 글상세 이미지 목록 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgList(ContentDtlImgDTO contentDtlImgParam) throws Exception
	{
		 List<ContentDtlImgDTO> contentDtlImgList = null;
		 
		 contentDtlImgList =   this.sqlMap.selectList("socialUp.service.content.mapper.selectContentDtlImgList", contentDtlImgParam);
		
		 
		 return contentDtlImgList;
	}
	
	
}