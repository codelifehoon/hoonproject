<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@ include file="/jsp/common/scriptCommon.jsp"%>

</head>
<body>
<!--  상단 시작 -->
<table class="headerTable" style="width:100%; margin:0 auto;" cellpadding="0" cellspacing="0">
	<tr><td>

<form method="post" action="<%=rootUrl %>/member/memberMemLogIn.action">
<table class="headerTable" style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">
	<tr>
		<td width='50%' valgin="bottom">
			<a href="http://goreee.com"><div style="font:30px Tahoma;font-weight:bold;color:#ffffff;margin:0;">Goreee</div></a>
			<div style="font:12px Tahoma;font-weight:bold;color:#ffffff;margin:0;">관심 있는 트위터,블로그,RSS를 하나로 모아서 보세요</div>
		</td>
		<td width='50%'>
		<!--  로그인전 -->
		<%if (!authInfo.isAuth()){ %>
			<table style="width:400px; margin:0 auto;" cellpadding="0" cellspacing="0" >
				<tr>
					<td>
						<div style="font:12px Tahoma;font-weight:bold;color:#ffffff;margin:0;">로그인ID</div>
					</td>
					<td>
						<div style="font:12px Tahoma;font-weight:bold;color:#ffffff;margin:0;">비밀번호</div>
					</td>
					<td rowspan=3>
						<span class="button blue"><button type="submit">로그인</button></span><br>
						<span class="button blue"><button type="button"  onClick="javascript:window.location.href='<%=rootUrl %>/member/memberRegForm.action'">회원가입</button></span>
					</td>
				</tr>
				<tr>
					<td>
						<input type='text' name="mem_id" class="input150"/>
					</td>
					<td>
						<input type='password' name="passwd" class="input150"/>
					</td>
					
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="keeplogin"/><a class="ALink1" href=''>로그인상태유지</a>
					</td>
					<td>
						 <a class="ALink1" href="">잊어버리셨나요</a>
					</td>
					
				</tr>
			</table>
		<!--  로그인후 -->	
		<%} else {%>
			<table style="width:400px; margin:0 auto;" cellpadding="0" cellspacing="0" >
					<tr>
						<td>
							<div style="font:12px Tahoma;font-weight:bold;color:#ffffff;margin:0;">
							<%=authInfo.getMem_nm() %>님 로그인 되었습니다. 
							<span class="button blue"><button type="button" onClick="javascript:window.location.href='<%=rootUrl %>/member/memberMemLogOut.action'">
							로그아웃</button></span>
							</div>
						</td>
						
					</tr>
					<tr>
						<td>
							
						</td>
						
						
					</tr>
					<tr>
						<td>
							<a class="ALink1" href="<%=rootUrl %>/member/memberUpdateForm.action">개인정보수정</a>
							|<a class="ALink1" href="<%=rootUrl %>/member/memberMyGoreStateForm.action">내고리현황</a>               
							|<a class="ALink1" href="<%=rootUrl %>/content/contentRegSetp1.action">고리만들기</a>
						</td>
					</tr>
				</table>
		<%} %>		
		</td>
	</tr>
</table>
</td></tr>
</table>
</form>
 <!--  상단 종료-->
 