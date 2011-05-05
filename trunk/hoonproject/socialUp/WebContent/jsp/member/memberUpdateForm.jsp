<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.member.dto.MemTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	MemTblDTO  loginMemTblDTO	= (MemTblDTO)request.getAttribute("loginMemTblDTO");
%>


<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>

<!--  내용 시작 -->
  <br>
  <form name="defForm" action="<%=rootUrl %>/member/memberUpdateProc.action" method="post">
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<span class="TL1">고리 회원가입 합니다.</span>
		</td>
	</tr>
	<tr><td><br/><br/><br/></td></tr>
	<tr>
	<td>
		<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
			<tr>
				<td width='150px'>
					<h2>◆  로그인ID :</h2>
				</td>
				<td width='850px'>
					<h5><%=loginMemTblDTO.getMem_id() %></h5>
				</td>
			</tr>
			 
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 메일주소를 로그인 id로 입력 해주세요 (예: goreeehelp@gmail.com) (100자이내)</h5>
				</td>
			</tr>

			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td>
					<h2>◆  비밀번호 : </h2>
				</td>
				<td>
					<input type='password' name="passwd" id="passwd" class="input150" value="<%=loginMemTblDTO.getPasswd() %>"/>
					비밀번호확인:<input type='password' name="passwd2" id="passwd2" class="input150" value="<%=loginMemTblDTO.getPasswd() %>"/>
				</td>	
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 로그인때 사용할 비밀번호를 입력해주세요 (4~20자 이내)</h5>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			
			<tr>
				<td>
					<h2>◆  닉네임 :</h2>
				</td>
				<td>
					<%=loginMemTblDTO.getMem_nm() %>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 사이트에 사용될 닉네임을 정해주세요(예: 홍길동아버지2~20자이내)  </h5>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan=2 align='center'>
					<br/><br/><br/>
					<span class="button blue xLarge"><button type="button" onClick='javascript:UpdateMem()'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 하&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </button></span>
					<br/><br/><br/>
				</td>
			</tr>

		</table>		
	</td>
	</tr>
	
</table>
</form>
<!--  내용 종료 -->
<script language="javascript">
	
function UpdateMem()
{
	if (!validateFormData()) return;
	 
	getF().submit();
	
}

function validateFormData()
{
	
	var passwd = $("#passwd");
	var passwd2 = $("#passwd2");
	var retvalue=false;
	
	
	var dataParam = {	"passwd" : passwd.val()
						,"jobKind" : "I"
					};
	
	if (passwd.val() != passwd2.val() )
	{
		alert("비밀번호 값이 같지 않습니다. 비밀번호및 비밀번호 확인값을 다시 입력 해주세요.");
		return false;
	}
		
      $.ajax({
          type: "POST"
          ,async: false
          ,url: "<%=rootUrl%>/member/memberRegValidateProc.action"
          ,dataType: "json"			// 응답의 결과로 반환되는 데이터의 종류를 식별하는 키워드
          ,data: dataParam
          //,contentType: "application/json; charset=utf-8"
          ,success: function(rs) 
          {
        	  if ("0" != rs.result.retCode    )
       		  { 
        		  alert(rs.result.retMsg);
        		  
        		  // 비밀번호 검증오류
        		  if (rs.result.retDetCode == "-1002" )  getF().passwd.focus();

        		  
        		  retvalue = false;
       		  }
        	  else
        		  {retvalue = true;}
              
          }
          ,error: function(data) { alert('서버와의 통신이 실패했습니다.'); retvalue=false; }
          ,complete: function() { }
      });

	return retvalue;
}
</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

