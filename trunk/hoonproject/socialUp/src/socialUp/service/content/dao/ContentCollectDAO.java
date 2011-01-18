package socialUp.service.content.dao;



import java.sql.SQLException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import socialUp.service.content.dto.ContentCollectDTO;
import socialUp.service.content.dto.ContentSourceTblDTO;
import socialUp.service.member.dto.MemTblDTO;


public interface ContentCollectDAO
{
	


	
	public void setSqlSesstion(SqlSession session)  throws Exception ;
	
	
	
	/**
	 * 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertContentCollect(ContentCollectDTO ContentCollectParam) throws Exception ;
	
	/**
	 * 정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentCollect(ContentCollectDTO ContentCollectParam) throws Exception ;
	
	
	/**
	 * 정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentCollectDTO> selectContentCollect(ContentCollectDTO ContentCollectParam) throws Exception ;
	

	/**
	 * 수집대상이 되는 컨텐츠의 리스트
	 * 
	 * @param contentSource 컨텐츠 소스정보
	 * @return
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO>  selectContentCollectWaitList() throws Exception;
	
}