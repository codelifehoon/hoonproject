<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	
	String tt_no =  (String)request.getParameter("tt_no");
%>

<script language="javascript">
alert("수정이 완료 되었습니다.");
window.location.href="<%=rootUrl%>/content/contentTitleMngForm.action?tt_no=<%=tt_no%>";
</script> 