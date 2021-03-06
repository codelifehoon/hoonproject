package socialUp.action;

import java.util.HashMap;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import socialUp.action.main.BaseActionSupport;
import socialUp.common.AuthInfo;
import socialUp.common.AuthService;
import socialUp.common.ServiceFactory;
import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;
import socialUp.common.util.DateTime;
import socialUp.common.util.DebugUtil;
import socialUp.common.util.ValidationUtil;
import socialUp.service.common.dto.BaseDTO;
import socialUp.service.content.ContentService;
import socialUp.service.content.dao.ContentTitleListTblDAO;
import socialUp.service.content.dao.ContentTitleListTblDAOImpl;
import socialUp.service.content.dto.ContentJoinMemDTO;
import socialUp.service.content.dto.ContentTitleTblDTO;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.validators.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.validators.VisitorFieldValidator;

public class MemberAction extends BaseActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304583857075513036L;

	public Logger log = Logger.getLogger(this.getClass());

	
	
	BaseDTO	 resultDTO	= null;		// 처리에 대한 결과를 담는용도로 사한다.	 
	
	
	
	/*jsp에 전달되어 사용될 값들*/
	private MemTblDTO loginMemTblDTO = null;			

	// login action일때 login 정보를 담고있다.
	public MemTblDTO getLoginMemTblDTO() {
		return loginMemTblDTO;
	}

	public BaseDTO getResultDTO() {
		return resultDTO;
	}
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
 	/**
 	 * 회원가입//수정  화면 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegForm() throws Exception 
 	{
		String returnVal = "DEFAULT";
		log.debug("RegForm 시작");

				
		
		return  returnVal;
	
	} 

 	
 	/**
 	 * 회원가입/수정 처리  action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegProc() throws Exception 
 	{
		String returnVal = "DEFAULT";
		log.debug("RegProc 시작");
		
		
		BaseDTO	 retBO	    = null;	 
		MemTblDTO memTblDTO = new MemTblDTO(); 
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		
		String mem_id =""
				,mem_nm =""
				,passwd ="";
		
		mem_id = this.getParam("mem_id");
		mem_nm = this.getParam("mem_nm");
		passwd = this.getParam("passwd");
		
		memTblDTO.setMem_id(mem_id);
		memTblDTO.setMem_nm(mem_nm);
		memTblDTO.setPasswd(passwd);
		memTblDTO.setRelation_kind("");
		memTblDTO.setCreate_ip(this.getRequest().getRemoteAddr());
		memTblDTO.setCreate_dt(sCurrentDate);
		
		
		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class); 
		
		
		
		// 회원가입에 대한 데이터 확인
		retBO = valiateRegMemData(memTblDTO);
		
		// 회원가입이 불가능한 상태
		if ( !"0".equals(retBO.getRetCode())) throw new Exception(retBO.getRetMsg());
		
			
		long regSeq = memberService.RegMemData(memTblDTO);
		
		if (regSeq <= 0 )
		{
			retBO.setRetCode("-1");
			retBO.setRetMsg("회원가입실패");
 		}
	
		if (log.isDebugEnabled()) log.debug("회원가입정보:" + DebugUtil.DebugBo(memTblDTO));
		// 회원가입이 되면 바로 로그인 처리 해준다.
		AuthInfo authInfo = new AuthInfo();
		
		memTblDTO.setMt_no(String.valueOf(regSeq));
		// 로그인  정보를 login결과 페이지로 넘긴다.
		authInfo.setMem_id(memTblDTO.getMem_id());
		authInfo.setMem_nm(memTblDTO.getMem_nm());
		authInfo.setMt_no(memTblDTO.getMt_no());
		
		// 로그인  쿠키 처리
		AuthService.setAuthInfo(authInfo, this.getRequest(), this.getResponse());
			
			
		
		
		// 처리결과를 jsp에 전달
		this.resultDTO = retBO;
		
		
		return  returnVal;
	
	}
 	
 	/**
 	 * 회원정보수정 화면 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String UpdateForm() throws Exception {
		String returnVal = "DEFAULT";
		log.debug("UpdateForm 시작");

		// 회원테이블 조회용 객체 생성
		MemTblDTO memTblDTO = new MemTblDTO(); 
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		AuthInfo authInfo  = AuthService.getAuthInfo( this.getRequest(), this.getResponse());
		
		
		memTblDTO.setMt_no(authInfo.getMt_no());
		memTblDTO.setMem_id(authInfo.getMem_id());
		
		List<MemTblDTO> resultList = memberService.validateRegMemData(memTblDTO);
		
		if (resultList.size() == 1) this.loginMemTblDTO = resultList.get(0);
		else throw new Exception("로그인후 개인정보 수정 해주세요."); 
		
		
		
		return  returnVal;
	
	
	}
 	/**
 	 * 회원정보수정 처리 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String UpdateProc() throws Exception {
		String returnVal = "DEFAULT";
		
		String passwd = this.getParam("passwd");
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		BaseDTO retBO		= null; 
		MemTblDTO memTblDTO = new MemTblDTO(); 
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		
		memTblDTO.setPasswd(passwd);
		memTblDTO.setUpdate_dt(sCurrentDate);
		memTblDTO.setUpdate_ip(this.getRequest().getRemoteAddr());
		
		retBO = valiateUpdateMemData(memTblDTO);
		if ( !"0".equals(retBO.getRetCode())) throw new Exception(retBO.getRetMsg());
			
		memberService.UpdateMemData(memTblDTO);
		
		return  returnVal;
	
	}
 	
 	/**
 	 * 회원로그인 처리 action
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String MemLogIn() throws Exception {
		String returnVal = "INDEX_LOGIN";
		
		log.debug("MemLogIn 시작");
		
		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		MemTblDTO memTblDTO = new MemTblDTO();
		
		
		
		memTblDTO.setMem_id((String)this.getRequest().getParameter("mem_id"));
		memTblDTO.setPasswd((String)this.getRequest().getParameter("passwd"));
		memTblDTO.setLoginYn("Y");
		
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
			AuthService.setAuthInfo(authInfo, this.getRequest(), this.getResponse());
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
		AuthService.setLogOut(this.getRequest(), this.getResponse());
		
			
		return  returnVal;
	
	}



 	/**
 	 * 회원가입전 유효성 검사
 	 * 
 	 * @return
 	 * @throws Exception
 	 */
 	public String RegValidateProc() throws Exception {
		String returnVal = null;
		log.debug("ValidateProc 시작");
		
		BaseDTO		retVal = null;
		JSONObject 	retJson = new JSONObject();	
		String mem_id  = this.getParam("mem_id");
		String mem_nm  = this.getParam("mem_nm");
		String passwd  = this.getParam("passwd");
		String jobKind  = this.getParam("jobKind");
		
		
		try
		{
			if ("I".equals(jobKind))
			{
				MemTblDTO memTblDTO = new MemTblDTO();
				memTblDTO.setMem_id(mem_id);
				memTblDTO.setMem_nm(mem_nm);
				memTblDTO.setPasswd(passwd);
				
				retVal = valiateRegMemData(memTblDTO);
			}
			else if ("U".equals(jobKind))
			{
				MemTblDTO memTblDTO = new MemTblDTO();
				memTblDTO.setPasswd(passwd);
				
				retVal = valiateUpdateMemData(memTblDTO);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		
		retJson.put("result",JSONObject.fromObject(retVal).toString());
		
		log.debug("retVal:" + retJson.toString());
		flushString(retJson.toString());
        	
		return  returnVal;
	}
 	
	
 	/**
 	 * 내고리현황 
 	 * 
 	 * @param memTblDTO
 	 * @return
 	 * @throws Exception
 	 */
 	public String MyGoreStateForm() throws Exception
 	{

 		String returnVal = "DEFAULT";
		
		
 		AuthInfo authInfo =  AuthService.getAuthInfo(this.getRequest(), this.getResponse());
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜
		
		
		ContentTitleTblDTO contentTitleDTO = new ContentTitleTblDTO();
		ContentJoinMemDTO  contentJoinMemDTO = new ContentJoinMemDTO();
		ContentTitleListTblDAO	contentTitleListTblDAO  = (ContentTitleListTblDAO)ServiceFactory.createService(ContentTitleListTblDAOImpl.class);
		
		
		contentTitleDTO.setMt_no(authInfo.getMt_no());
		contentJoinMemDTO.setMt_no(authInfo.getMt_no());
		
		
		contentTitleListTblDAO.initSql();
		
		this.getRequest().setAttribute("MYGOREE", contentTitleListTblDAO.selectMyGoreeList(contentTitleDTO));
		this.getRequest().setAttribute("MYIN",contentTitleListTblDAO.selectMyInList(contentJoinMemDTO));
		
		
		return  returnVal;
	
 		
 	}
 	
 	
 	
 	
 	
 	
/////////////////// action 이 아닌 코드들 ////////////////////// 	
 	
 	/**
 	 * 회원가입시 데이터 validation
 	 * 
 	 * @param memTblDTO
 	 * @return
 	 * @throws Exception
 	 */
 	private BaseDTO valiateRegMemData(MemTblDTO memTblDTO) throws Exception
 	{

		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		
		
		// 회원정보조회
		List<MemTblDTO> resultList = memberService.validateRegMemData(memTblDTO);
		BaseDTO retVal = null;
		
		// 기존 회원정보가 존재한다.
		if (resultList.size() > 0) 
			{
				retVal = new BaseDTO();
				retVal.setRetCode("-1");
				retVal.setRetCode("-1004");
				retVal.setRetMsg("같은 이메일 주소가 존재합니다.");
				return retVal;
			}
		
		retVal = ValidationUtil.emailV(memTblDTO.getMem_id());
		if (!"0".equals(retVal.getRetCode())) { retVal.setRetDetCode("-1000"); return retVal;}
			
		retVal = ValidationUtil.lenghV(memTblDTO.getMem_id(), 0, 100);
		if (!"0".equals(retVal.getRetCode())) { retVal.setRetDetCode("-1001"); return retVal;}
		
		retVal = ValidationUtil.lenghV(memTblDTO.getPasswd(), 4, 20);
		if (!"0".equals(retVal.getRetCode())) { retVal.setRetDetCode("-1002"); return retVal;}
		
		retVal = ValidationUtil.lenghV(memTblDTO.getMem_nm(), 2, 20);
		if (!"0".equals(retVal.getRetCode())) { retVal.setRetDetCode("-1003"); return retVal;}
		
			
		return retVal;
 		
 	}


 	/**
 	 * 회원수정시 데이터 validation
 	 * 
 	 * @param memTblDTO
 	 * @return
 	 * @throws Exception
 	 */
 	private BaseDTO valiateUpdateMemData(MemTblDTO memTblDTO) throws Exception
 	{

		// 회원테이블 조회용 객체 생성
		MemberService memberService = (MemberService)ServiceFactory.createService(MemberServiceImpl.class);
		
		
		// 회원정보조회
		List<MemTblDTO> resultList = memberService.validateRegMemData(memTblDTO);
		BaseDTO retVal = null;
		
		
		retVal = ValidationUtil.lenghV(memTblDTO.getPasswd(), 4, 20);
		if (!"0".equals(retVal.getRetCode())) { retVal.setRetDetCode("-1002"); return retVal;}
		
		return retVal;
 		
 	}
 	
 
 	




 	
 	

 	
 	

	
 	

}
