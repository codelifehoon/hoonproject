package socialUp.service.content.dao;



import java.sql.SQLException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import socialUp.service.member.dto.MemTblDTO;


public interface ContentDtlTblDAO 
{
	


	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	/**
	 * 회원 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertMemTbl(MemTblDTO memParam) throws Exception ;
	
	/**
	 * 회원정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateMemTbl(MemTblDTO memParam) throws Exception ;
	
	
	/**
	 * 회원정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<MemTblDTO> selectMemTbl(MemTblDTO memParam) throws Exception ;
	


	
	
}