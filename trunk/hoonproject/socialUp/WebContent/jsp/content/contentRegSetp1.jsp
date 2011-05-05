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
  
 <br>

 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >

	<tr>
		<td>
				<span class="TL1"> 신규 고리 동록하기 1단계(2단계중  1단계)</span>
		</td>

	</tr>

	<tr><td><br/><br/><br/></td></tr>

	<tr>

	<td>

		<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">

			<tr>

				<td height='60px'>

					<h3><b>◆ 다른 사용자와 생성된 고리를  자유롭게 공유하기를 원한다면  아래 공개 고리 만들기를 선택해주세요.</b></h3> 

					<h5>* 공개고리는 저작권이 있는글을 등록시  등록한 사용자가 해당 저작권에 대한 책임을 지게 됩니다.  <br/>

						공개고리를 사용할때에는 저작권 있는 글이 등록되지 않도록 해주세요.</h5>

				</td>

			</tr>

			<tr>

				<td  height='60px' align='center'>
 
					<br/><span class="button blue xLarge"><a type="button" href='<%=rootUrl%>/content/contentRegSetp2.action?goreeType=01'>공개고리 만들기</a><br/>

				</td>

			</tr>

			<tr>

				<td  height='60px'>

					<h3><b>◆ 친한 친구끼리 고리 공유를 원한다면  아래의 비밀고리 만들기를 선택 해주세요.</b></h3> 

				</td>

			</tr>

			<tr>

				<td  height='60px' align='center'>

					<br/><span class="button blue xLarge"><a type="button" href='<%=rootUrl%>/content/contentRegSetp2.action?goreeType=02'>비밀고리 만들기</a><br/>

				</td>

			</tr>

			<tr>

				<td  height='60px' >

					<h3><b>◆ 고리 공개/비공개,회원참여,고리공유하기등 세부설정을 원하시면 아래의  자유롭게   고리 만들기를 선택 해주세요.</b></h3> 

				</td>

			</tr>

			<tr>

				<td  height='60px' align='center'>

					<br/><span class="button blue xLarge"><a type="button" href='<%=rootUrl%>/content/contentRegSetp2.action?goreeType=03'>자유롭게 고리 만들기</a><br/>

				</td>

			</tr>

			<tr>

				<td style="background-color:#EEE;" height='60px'>

					<h6>

					<b>◆  고리는 모아 보기를 워하는 RSS,트위터등을 모아서 볼수있는 방법을 제공합니다.<br/>  

				     등록된 고리는 다른 고리들과 조합해서 새로운 고리를 생성할수도 있습니다.

				     </b>

				   </h6> 





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

