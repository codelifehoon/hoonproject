<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%@ page import="socialUp.service.member.dto.MemTblDTO"%>
<%
	// action 에서 로그인 쿠키 생성후 바로 읽지 클라인트에 찍히기 전에 바로 읽지 못하기
	// 때문에 최초로그인쿠키 처리후 첫페이지에서는 login여부로 확인한 DTO를 참조해서 로그인여부 check 한다.
	MemTblDTO  loginMemTblDTO = (MemTblDTO)request.getAttribute("loginMemTblDTO");

%>

<<script type="text/javascript">
<!--

<%if ( loginMemTblDTO == null) 
{%>
	alert("id/pw를 다시 확인 해주세요.")
	window.location.href = "/socialUp/index.action";
<%} else { %>
	alert("로그인성공.")
	window.location.href = "/socialUp/index.action";
<%}%>
 
//-->
</script>


