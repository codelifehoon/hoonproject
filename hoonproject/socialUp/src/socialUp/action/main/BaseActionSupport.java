package socialUp.action.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import socialUp.common.util.CookieUtil;

import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4469099709419770197L;
	
	/*jsp에 전달되어 사용될 값들*/
	protected String fromAction = "";		
	
	
	
	public String getFromAction() {
		return fromAction;
	}
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
	this.request=request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response){
	this.response=response;
	}

}