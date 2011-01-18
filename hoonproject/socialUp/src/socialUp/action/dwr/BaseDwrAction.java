package socialUp.action.dwr;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import socialUp.common.DaoFactory;
import socialUp.common.ServiceFactory;
import socialUp.common.mybatis.MyBatisManager;
import socialUp.common.util.DateTime;
import socialUp.service.member.MemberService;
import socialUp.service.member.MemberServiceImpl;
import socialUp.service.member.dao.MemTblDAO;
import socialUp.service.member.dao.MemTblDAOImpl;
import socialUp.service.member.dto.MemTblDTO;

import com.opensymphony.xwork2.ActionSupport;

public class BaseDwrAction  /*implements ServletRequestAware, ServletResponseAware*/
{


	public Logger log = Logger.getLogger(this.getClass());
	
/*	
	
	*//**
	 * DWR 호출시 생성되는 HttpServletRequest를 리턴한다.
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		WebContext wctx = WebContextFactory.get();
		return wctx.getHttpServletRequest();
	}
	

	/**
	 * DWR 호출시 생성되는 HttpServletResponse 리턴한다.
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse()
	{
		WebContext wctx = WebContextFactory.get();
		return wctx.getHttpServletResponse();
	}

	
	/*protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
	this.request=request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response){
	this.response=response;
	}*/
	
	

}


