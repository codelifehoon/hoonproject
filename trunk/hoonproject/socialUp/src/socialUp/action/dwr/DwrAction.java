package socialUp.action.dwr;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import socialUp.common.DaoFactory;
import socialUp.common.ServiceFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class DwrAction 
{


	public Logger logger = Logger.getLogger(this.getClass());
	

	/**
	 * 회원가입시 회원가입 가능여부를 확인한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public List<MemTblDTO> validateRegMemData(MemTblDTO memTblDTO) throws Exception
	{
		logger.debug("validateRegMemData action 시작");
		
		// sql session 생성
		SqlSession sqlMap = MyBatisManager.getInstanceSqlSession("");

		// 회원가입 가능여부를 확인한다.
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		memberService.setSqlMap(sqlMap);
		
		
		return memberService.validateRegMemData(memTblDTO);
		
		
	}
	

}
