package socialUp.common;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socialUp.common.util.CmnUtil;
import socialUp.common.util.CookieUtil;

public class AuthService 
{
	
	public static AuthInfo getAuthInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		AuthInfo authInfo = new AuthInfo();
		
		
		// 회원가입이 되면 바로 로그인 처리 해준다.
		CookieUtil cookieUtil = new CookieUtil(request,response);

		// 쿠키에 한글이 필요할경우 차후 확인해서 처리함
		authInfo.setMem_id(CmnUtil.nvl(cookieUtil.getSubCookie(CookieUtil.TP,"mem_id")));
		authInfo.setMem_nm(URLDecoder.decode(CmnUtil.nvl(cookieUtil.getSubCookie(CookieUtil.TP,"mem_nm")),"utf-8"));
		authInfo.setMt_no(CmnUtil.nvl(cookieUtil.getSubCookie(CookieUtil.TP,"mt_no")));
		
		return authInfo;
		
		
	}
	
	public static void setAuthInfo(AuthInfo authInfo,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
	
		
		// 회원가입이 되면 바로 로그인 처리 해준다.
		CookieUtil cookieUtil = new CookieUtil(request, response);

		// 쿠키에 한글이 필요할경우 차후 확인해서 처리함
		cookieUtil.add(CookieUtil.TP,"mem_id", authInfo.getMem_id());
		cookieUtil.add(CookieUtil.TP,"mem_nm", URLEncoder.encode(authInfo.getMem_nm(),"utf-8"));
		cookieUtil.add(CookieUtil.TP,"mt_no", authInfo.getMt_no());
		
		
	}
	
	public static void setLogOut(HttpServletRequest request,HttpServletResponse response)  throws Exception
	{
		
	
		
		// 회원가입이 되면 바로 로그인 처리 해준다.
		CookieUtil cookieUtil = new CookieUtil(request, response);
		
		cookieUtil.remove(CookieUtil.TP, "mem_id");
		cookieUtil.remove(CookieUtil.TP, "mem_nm");
		cookieUtil.remove(CookieUtil.TP, "mt_no");
		
	}
	

}
