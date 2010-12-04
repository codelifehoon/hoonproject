<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="socialUp.common.util.CookieUtil"%>
<%@ page import="socialUp.common.util.CmnUtil" %>
<%@ page import="socialUp.common.AuthInfo" %>
<%@ page import="socialUp.common.AuthService" %>
<%@ page import="java.util.Map" %>

<%
	
	final String rootUrl = "/socialUp";
	Logger logger = Logger.getLogger(this.getClass());
	AuthInfo authInfo = AuthService.getAuthInfo(request,response);
	 
%>