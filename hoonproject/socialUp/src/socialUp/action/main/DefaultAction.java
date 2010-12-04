package socialUp.action.main;

import org.apache.log4j.Logger;

import socialUp.common.util.CookieUtil;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 721999781797176947L;

	public Logger log = Logger.getLogger(this.getClass());
	
	String fromAction = "";						
	private MemTblDTO loginMemTblDTO;
	
	
	// jsp에 어떠한 action으로 부터왔는지 전달한다.
	public  MemTblDTO getLoginMemTblDTO() {
		return loginMemTblDTO;
	}

	// 로그인되었을때 로그인 상세 로그인 정보를 가지고있는다.
	public String getFromAction() {
		return fromAction;
	}

	
	
	
	@Override
	public String execute() throws Exception 
	{

		
		CookieUtil cookieUtil = new CookieUtil(this.request, this.response);
		
		String mem_id 	= cookieUtil.getSubCookie(CookieUtil.TP,"mem_id");
		String mt_no 	= cookieUtil.getSubCookie(CookieUtil.TP,"mt_no");
		
		
		
		// 회원정보가 쿠키에 있다면 로그인 상태로본다.
		if (mt_no != null) 
		{
			// 로그인된 사용자에 대한 추가 데이터 처리
			this.loginMemTblDTO = new MemTblDTO();
			this.loginMemTblDTO.setMem_id(mem_id);
			this.loginMemTblDTO.setMt_no(mt_no);
		}
		
		log.debug("index.jsp 실행");
		return "SUCCESS";
	}

	
	

}
