package socialUp.action;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import socialUp.action.main.BaseActionSupport;
import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.ServiceFactory;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.service.content.ContentService;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class MemberAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304583857075513036L;

	public Logger log = Logger.getLogger(this.getClass());
	
	
	/*jsp에 전달되어 사용될 값들*/
	private MemTblDTO loginMemTblDTO = null;			

	// login action일때 login 정보를 담고있다.
	public MemTblDTO getLoginMemTblDTO() {
		return loginMemTblDTO;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
 	
 	/**
 	 * 회원가입 화면 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegForm() throws Exception {
		String returnVal = "SUCCESS";
		
		
		
		return  returnVal;
	
	} 

 	/**
 	 * 회원정보 처리시 validation 처리  action (DWR)
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegDateChkProc() throws Exception {
		String returnVal = "";
		
		
		return  returnVal = "";
	
	}
 	/**
 	 * 회원가입 처리  action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegrProc() throws Exception {
		String returnVal = "";
		
		
		return  returnVal = "";
	
	}
 	
 	/**
 	 * 회원정보수정 화면 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String UpdateForm() throws Exception {
		String returnVal = "";
		
		
		return  returnVal = "";
	
	}
 	/**
 	 * 회원정보수정 처리 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String UpdateProc() throws Exception {
		String returnVal = "";
		
		
		return  returnVal = "";
	
	}
 	
 	/**
 	 * 회원로그인 처리 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String MemLogIn() throws Exception {
		String returnVal = "INDEX_LOGIN";
		
		log.debug("validateRegMemData 시작");
		
		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		MemTblDTO memTblDTO = new MemTblDTO();
		
		
		
		memTblDTO.setMem_id((String)this.request.getParameter("mem_id"));
		memTblDTO.setPasswd((String)this.request.getParameter("passwd"));
		
		log.debug("memTblDTO:" + DebugUtil.DebugBo(memTblDTO));
		 
		// 회원정보조회
		List<MemTblDTO> resultList = memberService.MemLogin(memTblDTO);
		
		// 기존 회원정보가 존재한다면 login 정보를 담아서 넘긴다.
		if (resultList.size() == 1) 
		{
			this.loginMemTblDTO =  resultList.get(0);
			
			AuthInfo authInfo = new AuthInfo();

			// 로그인  정보를 login결과 페이지로 넘긴다.
			authInfo.setMem_id(this.loginMemTblDTO.getMem_id());
			authInfo.setMem_nm(this.loginMemTblDTO.getMem_nm());
			authInfo.setMt_no(this.loginMemTblDTO.getMt_no());
			
			// 로그인  쿠키 처리
			AuthService.setAuthInfo(authInfo, this.request, this.response);
		}
		
		this.fromAction  = "LOGIN_ACTION";
	
		return  returnVal;
	
	}
 	
 	/**
 	 * 회원로그아웃 처리 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String MemLogOut() throws Exception {
		String returnVal = "INDEX_LOGOUT";
		
			
		AuthInfo authInfo = new AuthInfo();
		
		// 로그아웃처리
		AuthService.setLogOut(this.request, this.response);
		
			
		return  returnVal;
	
	}


	
 	

}
