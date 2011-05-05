package socialUp.action.main;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;

import com.opensymphony.xwork2.ActionSupport;

public class BaseActionSupport extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4469099709419770197L;
	public Logger log = Logger.getLogger(this.getClass());
	
	/*jsp에 전달되어 사용될 값들*/
	protected String fromAction = "";		
	
	
	
	public String getFromAction() {
		return fromAction;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		// action에 interceptor를 사용하니 해당 메서드가 실행되지도 않고 request도상용 불가
	}
	@Override
	public void setServletResponse(HttpServletResponse response)
	{}
	
	public String getParam(String param)
	{
		String retVal;
		retVal = CmnUtil.nvl(this.getRequest().getParameter(param));
		
		return retVal;
	}

	public String[] getParams(String param)
	{
		return this.getRequest().getParameterValues(param);
	}
	
	protected void flushString(String flushString)
	{
		try
		{
		this.getResponse().setCharacterEncoding("UTF-8");
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().getWriter().write(flushString);
		this.getResponse().flushBuffer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	/*
	 * action에 interceptor를 사용시 ServletActionContext를 이용해서 request를 가져와야한다.
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest()
	{
		
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}
	
}