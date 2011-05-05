package socialUp.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;


/**
 * 
 * 로그인  인증에 대한 단일 관리를 위해서 Interceptor를 사용한다.
 * 
 * @author 장재훈
 *
 */
public class LoginSessionCheckInterceptor extends AbstractInterceptor{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 8709286651200676951L;

	public static Logger log = Logger.getLogger(LoginSessionCheckInterceptor.class);
	
    protected Set excludeActions = null;
    protected String redirectWebPage = null; // default page if not setted by param.    
 
    public void setExcludeActions(String excludeActions) {
        this.excludeActions = TextParseUtil.commaDelimitedStringToSet(excludeActions);
    }
    
	public void setRedirectWebPage(String redirectWebPage) {
		if( StringUtils.isNotBlank(redirectWebPage)) this.redirectWebPage = redirectWebPage;
	}
    
	/**
	 * check and deny the request unless rquested action is available action without login or has authenticated session.  
	 * 
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		
		
		log.debug("intercept 시작");
		
		// 로그인 체크를 해야되는 페이지 인지 확인한다.
		if( isAvailableActionWithoutLogin() ){ // check if action is available action without login
			return invocation.invoke(); // execute action
		}
		else{
			if( isLogined()){ // check if logged in or not
				return invocation.invoke(); // execute action
			}
			else{
				proccessDeniedResponse(); // redirect to login page, or send denial xml message
				return null;
			}
		}
	}
	
	private void proccessDeniedResponse() throws IOException { 
		if( false/*isMiplatformRequest(ServletActionContext.getRequest())(*/ ){
			// TODO send MiResponse denial message
			// example is following :
			// 		MiResponse miresponse = new MiResponse(ServletActionContext.getResponse());
			// 		miresponse.error("-1","sessionout");
		}
		else{
			String redirectUrl = null;
			if( redirectWebPage.startsWith("/") || redirectWebPage.startsWith("\\")){
				redirectUrl = ServletActionContext.getRequest().getContextPath() + redirectWebPage;
			}else{
				redirectUrl = ServletActionContext.getRequest().getContextPath() + "/" +redirectWebPage;
			}			
			
			// Login페이지로 보내기 전에 원페이지 호출이 어디인지 값을 저장해서 넘긴다.(로그인후 바로 해당 페이지로 갈수 있게 하기위해서)
			// 원페이지로 가던 parametor값도 같이 넘겨준다.
			{
				HttpServletRequest request = ServletActionContext.getRequest();
				
				redirectUrl += "?orgUrl=" + getRequestedResource(request);
				
				Enumeration paramNames = request.getParameterNames(); 
				while(paramNames.hasMoreElements()) 
				{ 
					String paramName = (String)paramNames.nextElement(); 
					String[] paramValues = request.getParameterValues(paramName);
					
					if (paramValues.length == 1) 
					{
						String paramValue = paramValues[0];
						redirectUrl += "&" +paramName+ "=" + paramValues[0];
						
						if (paramValue.length() == 0) {} 
						else {} 
					} else 
					{ 
					 
						for(int i=0; i<paramValues.length; i++) 
						{
							redirectUrl += "&" +paramName+ "=" + paramValues[i];
						} 
				 
					}
				}
			}
	
			
			log.debug("로그인전 org redirectUrl: " + redirectUrl );
			//ServletActionContext.getRequest().setAttribute("orgUrl", getRequestedResource(ServletActionContext.getRequest()));
			ServletActionContext.getResponse().sendRedirect(redirectUrl);
		}
	}
 /*
	private boolean isMiplatformRequest(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");		
		if( agent != null && ( agent.indexOf("MiPlatform") != -1 ) ){
			return true;
		}else{
			return false;
		}
	}
 */
	private boolean isLogined() 
	{
		AuthInfo authInfo;
		try 
		{
			authInfo = AuthService.getAuthInfo(ServletActionContext.getRequest(),ServletActionContext.getResponse());
			if( authInfo.isAuth()) return true;
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
 
	/**
	 * 로그인 대상에서 제외되는 페이지 확인
	 * @return
	 */
	private boolean isAvailableActionWithoutLogin() {
		String requestedResouece = getRequestedResource(ServletActionContext.getRequest());
		if( log.isDebugEnabled() ) log.debug("Requested action resource : " + requestedResouece);
		
		String action = null;
		Iterator excludeActionsIter = excludeActions.iterator();
		while( excludeActionsIter.hasNext() ){
			action = (String) excludeActionsIter.next();
			if( requestedResouece.equals(action) ) return true;	
		}
		
		return false;
	}
 
	// 현재 요청한 URL을 리턴한다.
	private String getRequestedResource(HttpServletRequest request){
		String requestedResouece = null;
		
		String contextPath = request.getContextPath();
		String requestedUri = request.getRequestURI();
		
		if( "/".equals(contextPath) ){
			requestedResouece = requestedUri;
		}
		else{
			requestedResouece = requestedUri.substring(contextPath.length());
		}		
		return requestedResouece;
	}
}