package socialUp.action;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;

public class MemberAction extends ActionSupport 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304583857075513036L;

	public Logger logger = Logger.getLogger(this.getClass());
	
	
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
 	

}
