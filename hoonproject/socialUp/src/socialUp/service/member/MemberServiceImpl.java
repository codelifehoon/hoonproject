package socialUp.service.member;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

public class MemberServiceImpl implements MemberService 
{
	public Logger logger = Logger.getLogger(this.getClass());
	
	private SqlSession sqlMap ;

	public SqlSession getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlSession sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	/**
	 * 회원가입시 회원가입 가능여부를 확인한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public List<MemTblDTO> validateRegMemData(MemTblDTO memTblDTO) throws Exception
	{
		logger.debug("validateRegMemData 시작");
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		// 회원테이블 조회용 객체 생성
		MemTblDAO memTblDAO = (MemTblDAO)DaoFactory.createDAO(MemTblDAOImpl.class);
		memTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		List<MemTblDTO> resultList = memTblDAO.selectMemTbl(memTblDTO);
		
		return resultList;
	}


	
}
