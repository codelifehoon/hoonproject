<%@page import="socialUp.service.content.dto.ContentJoinMemDTO"%>
<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	
	ContentTitleTblDTO contentTitle =  (ContentTitleTblDTO)request.getAttribute("contentTitle");
	
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>
	
	<script language="javascript">
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

			<div class="main_content">
			
			<div class="sd_main">
			<h4>
				나의 컨텐츠 타이틀을 원하는 컨텐츠타이틀의브랜치에 등록 완료
			</h4>
			</div>
				
			</div>


				
<%@ include file="/jsp/common/footer.jsp" %>


