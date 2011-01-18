package socialUp.service.content.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import socialUp.service.content.dto.ContentDtlCommentDTO;


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
	
	
	
}