<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentSourceTblDTO" %>
<%@ page import="java.util.List" %>
<%@page import="socialUp.service.member.dto.MemTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	// goreeType 01 : 공개고리 만들기
	// goreeType 02 : 비밀고리 만들기
	// goreeType 03 : 자유롭게 고리 만들기
	String goreeType = (String)request.getParameter("goreeType");
%>
<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>

<!--  내용 시작 -->
 <br>
 <form name="defForm" method="post" action="<%=rootUrl%>/content/contentAddFinish.action">
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<span class="TL1"> 신규 고리 동록하기 2단계(2단계중  2단계)</span>
		</td>
	</tr>
	<tr><td><br/><br/><br/></td></tr>
	<tr>
	<td>
		<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">
			<tr>
				<td width='150px'>
					<h2>◆  고리명  : </h2>
				</td>
				<td>
					<input type='input' name="title_name" class="input400"/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 고리를 대표할 고리명을 200자 이하 로입력 해주세요</h5>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td width='150px'>
					<h2>◆ 고리설명 :  </h2>
				</td>
				<td>
					<input type='input' name="title_desc" class="input400"/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 고리를 설명한 2000자 이하 내용을 입력해주세요.</h5>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td width='150px'>
					<h2>◆ 고리주소 :  </h2>
				</td>
				<td>
					http://goreee.com/go/<input type='input' name="title_short_name" class="input250"/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 20자 이하로 입력해주세요.</h5>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td width='150px'>
					<h2>◆ 공개여부[?] </h2>
				</td>
				<td>
				<h2>
					 <select name="order_mem_open_yn" id="order_mem_open_yn">
					 	<option value="Y">고리를 공개합니다.</option>
					 	<option value="N">고리를 비공개합니다.</option>
					 </select>
					    
					 / 고리가져가기 허용여부 
					 <select name="branch_conf_yn" id="branch_conf_yn">
					 	<option value="Y">예</option>
					 	<option value="N">아니오</option>
					 </select>
				</h2>   

				</td>
			</tr>
			<!--
			<tr>
				<td colspan=2>
				 
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      * 고리를 공개할경우 저작권이 있는 다른 사용자의 글을 등록시 저작권침해가 됩니다.
				 <br/>다른 사람의글 등록시 신중하게 해주세요</h5>
				  
				</td>
			</tr>
			-->
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td width='150px'>
					<h2>◆ 고리참여  </h2>
				</td>
				<td>
				<h2>
					<select name="order_mem_join_yn" id="order_mem_join_yn" onchange="javascript:JoinMethodChange();">
					 	<option value="Y">다른 사용장의 참여를 허락합니다.</option>
					 	<option value="N">다른 사용장의 참여를 거부합니다.</option>
					 </select>
					    
				</h2>   
				</td>
			</tr>
			<tr>
				<td colspan=2>
				
					<div id='divJoinMeth' style="display:;">
						<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">
							<tr>
								<td width="100px"></td>
								<td width="100px">* 참여방법 선택 </td>
								<td width="150px"><INPUT TYPE=RADIO NAME="order_mem_join_metd" VALUE="01" checked>참여신청시 바로 참여 </td>
								<td></td>
							</tr>
							<tr>
								<td ></td>
								<td ></td>
								<td><INPUT TYPE=RADIO NAME="order_mem_join_metd" VALUE="02">비밀번호 참여 </td>
								<td>비밀번호: <input type='input' name="order_mem_join_passwd" class="input150"></input></td>
							</tr>
							<tr>
								<td ></td>
								<td ></td>
								<td><INPUT TYPE=RADIO NAME="order_mem_join_metd" VALUE="03">나의 승인필요</td>
								<td></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			
			<tr>
				<td width='150px'>
					<h2>◆ 글타래등록 </h2>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 관심있는   rss 또는 트위터를 등록해서 고리로 모아 봅니다 등록방식은 콤마(,)또는 줄바꿈으로<br/>
      				구분해서 등록 해주세요</h5>
      				
      				<br/>▷ RSS 등록<br/>
					<textarea name="rss_source" cols="78" rows="5"></textarea>
<br/>예제) http://naver.com/rss1, http://naver.com/rss2 또는 
<br/>http://naver.com/rss
<br/>http://naver.com/rss2
<br/><br/>
      				<br/>▷ 트위터 등록
<br/>
<textarea name="tw_source" cols="78" rows="5"></textarea>
<br/>예제) http://naver.com/rss1, http://naver.com/rss2 또는 
<br/>http://naver.com/rss
<br/>http://naver.com/rss2
<br/><br/>

				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>

			<tr>
				<td colspan=2 align='center'>
				<br/><span class="button blue xLarge"><button type="button" onClick="javascript:RegContentTitle()">등   록 하 기</button>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/>
				</td>
			</tr>
			
		</table>		
	</td>
	</tr>
	
</table>
</form>
<!--  내용 종료 -->



<script language="javascript">
function JoinMethodChange()
{
	if (getEleById("order_mem_join_yn").value == "Y")
	{
		getEleById("divJoinMeth").style.display = "";
	}
	else
	{
		getEleById("divJoinMeth").style.display = "none";
	}
}

function  RegContentTitle()
{
	getF().submit();
}

function initValues()
{
	<%if ("01".equals(goreeType)){%>
	<%}else if ("02".equals(goreeType)){%>
		getEleById("order_mem_open_yn").value = "N";
		getEleById("branch_conf_yn").value = "N";
		getEleById("order_mem_join_yn").value = "N";
		JoinMethodChange();
	<%}else if ("03".equals(goreeType)){%>
	<%}%>
	
}

initValues();

</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

