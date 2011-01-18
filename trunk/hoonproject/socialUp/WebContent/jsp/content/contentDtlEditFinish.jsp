<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%

	String newFlag = request.getParameter("newFlag");

	if ("U".equals(newFlag))
	{
%>
	수정완료
<%				
	}
	else if("D".equals(newFlag))
	{
%>
	삭제완료
<%				
	}else if("N".equals(newFlag))
	{
%>
	등록완료
<%				
	}
%>
	



