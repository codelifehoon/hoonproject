<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentSourceTblDTO" %>
<%@ page import="java.util.List" %>
<%@page import="socialUp.service.member.dto.MemTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
%>

<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>

<!--  내용 시작 -->
  
  getContextPath():<%=request.getContextPath() %><br/>
  getRequestURI():<%=request.getRequestURI()%><br/>
  orgUrl:<%=request.getParameter("orgUrl")%>
  testVal:<%=request.getParameter("testVal")%>

		
  
 <br>

 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >

	<tr>

		<td>

				<span class="TL1">고리 로그인</span>

		</td>

	</tr>

	<tr><td><br/><br/><br/></td></tr>

	<tr>

	<td>

		<table style="width:700px; margin:0 auto;" cellpadding="0" cellspacing="0">

			<tr style="background-color:#006699;color: #FFFFFF;" height="70px">

				<td> 

					<b>로그인을 하시면 더욱 다양한 서비스를 이용하실수 있습니다.<br/>

					회원 가입하셨을때 사용할수 있는 서비스를 확인하실려면 [여기]를 눌러주세요.

					</b>

				</td>

				<td>

					

				</td>

			</tr>

			<tr style="background-color:#ccffff">

				<td width='500px' >

				<table style="margin:0 auto;" cellpadding="0" cellspacing="0"  border=0>

					<tr>

						<td width='200px' height='50px'>사용자 ID

						</td>

						<td><input type='input' name="" class="input150"/>

						</td>

						<td>

						</td>

					</tr>

					<tr>

						<td height='50px'>비밀번호 

						</td>

						<td><input type='input' name="" class="input150"/>

						</td>

						<td>&nbsp;&nbsp;&nbsp;

							<a href="">잊어버리셨나요?</a>

						</td>

					</tr>

					<tr>

						<td>

						</td>

						<td> <INPUT TYPE=CHECKBOX NAME="maillist">로그인상태유지 

						</td>

						<td>

						</td>

					</tr>

				</table>

				

				</td>

				<td>

					<span class="button blue xLarge"><button type="button">회원가입</button>

				</td>

			</tr>

			<tr style="background-color:#ccffff">

				<td align='center'>

					<br/><br/>

					<span class="button blue xLarge"><button type="button">로그인 합니다</button></span>

					<br/><br/>

				</td>

				<td>

				</td>

			</tr>

			<tr >

				<td >

				<br/><br/>

				

				</td>

				<td>

				</td>

			</tr>

			

		</table>		

	</td>

	</tr>

	

</table>
  
  
<!--  내용 종료 -->
<script language="javascript">
	
</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

