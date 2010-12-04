package socialUp.service.content.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.util.NumUtil;
import socialUp.service.content.dto.ContentSourceTblDTO;

public class ContentSourceTblDAOImpl implements ContentSourceTblDAO {
	

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
	public int insertContentSourceTbl(ContentSourceTblDTO conParam) throws Exception
	{

		
		this.sqlMap.insert("socialUp.service.content.mapper.insertContentSourceTbl", conParam);
		
		// 회원가입후 가입일련번호를 넘겨준다.
		return NumUtil.toInt(conParam.getCs_no());
		
	}
	
	/**
	 * 회원정보 수정
	 * 
	 * @param param
	 * @throws Exception
	 */
	public int updateContentSourceTbl(ContentSourceTblDTO conParam) throws Exception
	{
		log.debug("updateContentSourceTbl 시작");
		return 0;
	}
	
	
	/**
	 * 회원정보 조회
	 * 
	 * @param param
	 * @throws Exception
	 */
	public List<ContentSourceTblDTO> selectContentSourceTbl(ContentSourceTblDTO conParam) throws Exception
	{
		log.debug("selectContentSourceTbl 시작");

		return  this.sqlMap.selectList("socialUp.service.content.mapper.selectContentSourceTblList", conParam);
		
	}
	
	
	


	
	
}