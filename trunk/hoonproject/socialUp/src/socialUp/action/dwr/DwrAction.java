package socialUp.action.dwr;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import socialUp.common.DaoFactory;
import socialUp.common.ServiceFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class DwrAction extends BaseDwrAction 
{


	public Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 회원가입처리한다.
	 * 
	 * @param memtbldto
	 * @throws Exception 
	 */
	public HashMap RegMemData(MemTblDTO memTblDTO) throws Exception
	{
		log.debug("validateRegMemData 시작");
		
		HashMap resultMap = new HashMap();
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		resultMap.put("result_code","00");
		resultMap.put("result_msg","성공");
		
		
		memTblDTO.setCreate_ip(this.request.getRemoteAddr());
		memTblDTO.setCreate_dt(sCurrentDate);
		
		
		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class); 
		
		
		
		// 회원정보조회
		List<MemTblDTO> resultList = memberService.validateRegMemData(memTblDTO);
		
		// 기존 회원정보가 존재한다.
		if (resultList.size() > 0) 
			{
				resultMap.put("result_code","-01");
				resultMap.put("result_msg","기존에 사용자가 존재합니다.");
				
				return resultMap; 
			}
		
		long regSeq = memberService.RegMemData(memTblDTO);
		
		
		if (regSeq <= 0 )
		{
			resultMap.put("result_code","-02");
			resultMap.put("result_msg","회원가입실패.");
			
			return resultMap; 
		}
	
		if (log.isDebugEnabled()) log.debug("회원가입정보:" + DebugUtil.DebugBo(memTblDTO));
		// 회원가입이 되면 바로 로그인 처리 해준다.
		CookieUtil cookieUtil = new CookieUtil(this.request, this.response);
		// 쿠키에 한글이 필요할경우 차후 확인해서 처리함
		//cookieUtil.add(CookieUtil.TP,"mem_id", URLEncoder.encode(memTblDTO.getMem_id(),"EUC-KR"));
		cookieUtil.add(CookieUtil.TP,"mt_no", String.valueOf(regSeq));
		
		return resultMap;
	}
	
	
	

}
