package socialUp.service.content;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentBranchDTO;
import socialUp.service.content.dto.ContentDtlCommentDTO;
import socialUp.service.content.dto.ContentDtlImgDTO;
import socialUp.service.content.dto.ContentDtlTblDTO;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.content.dto.SearchDTO;
import socialUp.service.content.dto.UploadFilesDTO;

// 각종자원에 대한 서비스를 제공한다.
// 서비스에 필요한 리소스 (파일 관리,  이메일  관련 ) 관리에 필요한 service들의 모음
public interface ResourceMngService 
{

	/**
	 * 업로드 파일 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception ;

	
	
	/**
	 * 업로드 파일 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<UploadFilesDTO> selectUploadFiles(UploadFilesDTO uploadFilesParam) throws Exception ;

	/**
	 * 썸네일 대상 목록 가져오기
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentDtlImgDTO> selectContentDtlImgWaitList(ContentDtlImgDTO contentDtlImgParam) throws Exception;

	
	/**
	 * content_dtl_img 테이블에 있는 다운로드 대기 목록에서 있는 이미지를 가져와서
	 * 썸네일 이미지를 생성한다.
	 * 
	 * @param contentDtlImgparam
	 */
	public void contentDtlImgMakeThumbnail(ContentDtlImgDTO  contentDtlImgparam) throws Exception;
	
}
