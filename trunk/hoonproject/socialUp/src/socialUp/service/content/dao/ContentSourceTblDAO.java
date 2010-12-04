package socialUp.service.content.dao;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentSourceTblDTO;

public interface ContentSourceTblDAO 
{
	


	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	/**
	 * 회원 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public int insertContentSourceTbl(ContentSourceTblDTO conParam) throws Exception ;
	
	/**
	 * 회원정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentSourceTbl(ContentSourceTblDTO conParam) throws Exception ;
	
	
	/**
	 * 회원정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO> selectContentSourceTbl(ContentSourceTblDTO conParam) throws Exception ;
	


	
	
}