<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.common.dto.BaseDTO"%>
<%@page import="socialUp.service.common.dao.CodeDetailDAOImpl"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	BaseDTO resultDTO 	= (BaseDTO)request.getAttribute("resultDTO");
	String mResultMsg 	= (String)request.getAttribute("mResultMsg");
%>

<script type="text/javascript">

<%if ( resultDTO != null && "0".equals(resultDTO.getRetCode())) 
{%>
	alert("회원가입이 완료 되었습니다.")
	window.location.href = "http://goreee.com/";
<%} else { %>
	alert("<%=resultDTO.getRetMsg()%>");
	window.location.href = "http://goreee.com/";
<%}%>
 

</script>


