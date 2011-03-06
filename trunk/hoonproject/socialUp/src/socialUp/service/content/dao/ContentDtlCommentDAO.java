package socialUp.service.content.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.UploadFilesDTO;


public interface ContentDtlCommentDAO 
{

	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	
	/**
	 * 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception ;
	
	/**
	 * 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception ;
	
	
	/**
	 * 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlCommentDTO> selectContentDtlComment(ContentDtlCommentDTO ContentDtlCommentParam) throws Exception ;
	
	
	/**
	 * 업로드 파일 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception ;
	
	/**
	 * 업로드 파일 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception ;
	
	
	/**
	 * 업로드 파일 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<UploadFilesDTO> selectUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception ;
	
	
	/**
	 * 글상세 이미지 목록 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgList(ContentDtlImgDTO contentDtlImgParam) throws Exception ;
	
	/**
	 * 글상세 이미지 목록 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int insertContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception ;
	
	
	/**
	 * 글상세 이미지 목록 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public long updateContentDtlImg(ContentDtlImgDTO contentDtlImgParam) throws Exception ;
	

	/**
	 * html 본문에서  이미지를 태그를  뽑아내어 thumbnail을 생성한다.
	 * 본문에 있는 간략한 이미지를 뽑아내기 위해서 사용한다.
	 * @param 	contentDtlParam.getCd_no()				등록일련번호
	 * 			contentDtlParam.getContent_desc()		내용
	 * @return
	 */
	public int extractRegContentDtlThumbNail(ContentDtlTblDTO contentDtlParam) throws Exception;

	/**
	 * 썸네일 대상 이미지 목록
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgWaitList(ContentDtlImgDTO contentDtlImgParam) throws Exception;
	
}