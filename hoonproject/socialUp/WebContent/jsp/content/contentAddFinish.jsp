<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	
	ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");

		
%>

<script language="javascript">

alert("신규고리 생성이 완료 되었습니다. 생성된 신규고리 메인으로 이동합니다.");
window.location.href="<%=rootUrl%>/content/contentDtlList.action?tt_no=<%=contentTitle.getTt_no()%>";
</script> 