package socialUp.service.member.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.member.dto.MemTblDTO;

public class MemTblDAOImpl implements MemTblDAO {
	

	public Logger log = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;
	
	public void setSqlSesstion(SqlSession session)  throws Exception 
	{
		
		this.sqlMap = session;
	}
	
	/**
	 * 회원 테이블 정보 입력
	 * @param param
	 * @throws Exception
	 */
	public long insertMemTbl(MemTblDTO memParam) throws Exception
	{

		
		this.sqlMap.insert("socialUp.service.member.mapper.insertMemTbl", memParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toLong(memParam.getMt_no());
		
	}
	
	/**
	 * 회원정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateMemTbl(MemTblDTO memParam) throws Exception
	{
		log.debug("updateMemTbl 시작");
		return  this.sqlMap.update("socialUp.service.member.mapper.updateMemTbl", memParam);
		
	}
	
	
	/**
	 * 회원정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<MemTblDTO> selectMemTbl(MemTblDTO memParam) throws Exception
	{
		log.debug("selectMemTbl 시작");

		return  this.sqlMap.selectList("socialUp.service.member.mapper.selectMemTblList", memParam);
		
	}
	
	
	


	
	
}