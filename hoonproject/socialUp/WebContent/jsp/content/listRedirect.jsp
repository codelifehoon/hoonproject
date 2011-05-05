<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	
	String tt_no = "";
	List<ContentTitleTblDTO> contentTitleList = (List<ContentTitleTblDTO>)request.getAttribute("contentTitleList");
	if (contentTitleList != null && contentTitleList.size() == 1)
	{
		tt_no  = contentTitleList.get(0).getTt_no();
	}

		
%>

<script language="javascript">

<% if ("".equals(tt_no)){ %>
	alert("지정한 고리명은 존재하지 않습니다. 고리 메인 페이지로 이동 합니다.");
	window.location.href="http://goreee.com";
<%} else { %>
	window.location.href="<%=rootUrl%>/content/contentDtlList.action?tt_no=<%=tt_no%>";
<%}%>

</script> 