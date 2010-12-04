package socialUp.service.member;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CookieUtil;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

public class MemberServiceImpl implements MemberService 
{
	public Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 회원가입시 회원가입 가능여부를 확인한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public List<MemTblDTO> validateRegMemData(MemTblDTO memTblDTO) throws Exception
	{
		log.debug("validateRegMemData 시작");
		
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		List<MemTblDTO> resultList = null;
		
		// sql session 생성
		try {
		
		
		// 회원테이블 조회용 객체 생성
		MemTblDAO memTblDAO = (MemTblDAO)DaoFactory.createDAO(MemTblDAOImpl.class);
		memTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		resultList = memTblDAO.selectMemTbl(memTblDTO);
		
		sqlMap.commit();
		}
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally { sqlMap.close();}
		
	
		
		
		return resultList;
	}

	
	/**
	 * 회원가입처리한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public long RegMemData(MemTblDTO memTblDTO) throws Exception
	{
		log.debug("RegMemData 시작");
		long regSeq =0;
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");
		
		try 
		{
		// sql session 생성

		// 회원테이블 조회용 객체 생성
		MemTblDAO memTblDAO = (MemTblDAO)DaoFactory.createDAO(MemTblDAOImpl.class);
		memTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
		
		// 회원정보조회
		regSeq =  memTblDAO.insertMemTbl(memTblDTO);

		sqlMap.commit();
		} catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally { sqlMap.close();}
		
		return regSeq;
		
	}

	/**
	 * 로그인처리한다
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public List<MemTblDTO>  MemLogin(MemTblDTO memTblDTO) throws Exception
	{
		log.debug("MemLogin");
		long regSeq =0;
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");		
		List<MemTblDTO> resultList = null;
		
		try 
		{
				
			// 회원테이블 조회용 객체 생성
			MemTblDAO memTblDAO = (MemTblDAO)DaoFactory.createDAO(MemTblDAOImpl.class);
			memTblDAO.setSqlSesstion(sqlMap);			// sql session 설정
			
			// 회원정보조회
			resultList =  memTblDAO.selectMemTbl(memTblDTO);
	
			sqlMap.commit();
			} 
		catch (Exception e)
		{
			sqlMap.rollback();
			e.printStackTrace();
			throw e;
		}
		finally { sqlMap.close();}
		
		return resultList;
		
	}
	
	

	
}
