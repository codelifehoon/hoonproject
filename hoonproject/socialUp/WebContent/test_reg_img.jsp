<%@page import="net.htmlparser.jericho.Source"%>
<%@page import="net.htmlparser.jericho.SourceFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>

<%@ include file="/jsp/common/pageCommon.jsp"%>
<%	


	Pattern p  		= null;
	Matcher matcher	= null;
	String contentStr = "<img src='/aaa.jpg>dsdadsdsd;dsdsad'";
	contentStr += "<img src='/bbbb.jpg size color>dsdadsdsd;dsdsad'";
	contentStr += "<img src='/cccc.jpg size color>dsdadsdsd;dsdsad'";
	contentStr += "<img src='/dddd.jpg size color>dsdadsdsd;dsdsad'";
	contentStr += "<img src='/bbbb.gif>dsdadsdsd;dsdsad'";
	contentStr += "<img src='/xxxx.bmp>dsdadsdsd;dsdsad'";
	
	// jpg , gif, bmp img태그를 뽑아내는 정규식.
	//p = Pattern.compile("<img src=(.*?)[.](jpg|gif|bmp)");
	//matcher = p.matcher(contentStr);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<META http-equiv="page-enter" content="revealtrans (duration=3, transition=23)"> 
	<META http-equiv="page-exit" content="revealtrans (duration=3, transition=23)">
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script> 
	<script language="javascript">
	
	</script>
</head>
 
<body>
	<%	
	while(matcher.find())
	{
	
		 	String g0 = matcher.group(0);
		    String g1 = matcher.group(1);
		    String g2 = matcher.group(2);
%>
		    g0 : <%=g0 %><br>
		    g1 : <%=g1 %><br>
		    g2 : <%=g2 %><br>
		    
	<%} %>

</body>
</html>
