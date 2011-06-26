<%@page import="socialUp.service.common.dao.CodeDetailDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.common.util.ConvertHashMap"%>
<%@page import="java.util.List"%>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	List<ConvertHashMap>	myGoreeList = (List<ConvertHashMap>)request.getAttribute("MYGOREE");
	List<ConvertHashMap>	MyInList 	= (List<ConvertHashMap>)request.getAttribute("MYIN");

%>


<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>
<!--  내용 시작 -->
 
 <br>
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<span class="TL1">내고리현황</span>
		</td>
	</tr>
	<tr><td><br/><br/><br/></td></tr>
	<tr>
	<td>
		<table style="width:800px; margin:0 auto;" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan=5><span class="TL2">◆  내가 만든 고리</span><br/><br/></td>
			</tr>
			<tr style="background-color:#006699;color: #FFFFFF;" height="30px">
				<td width='400px'> 
					<b>고리이름</b>
				</td>
				<td> 
					<b>조회수</b>
				</td>
				<td> 
					<b>가져간고리</b>
				</td>
				<td> 
					<b>가져온고리</b>
				</td>
				<td> 
					<b>참여자수</b>
				</td>
				<td>
					<b>초대승낙</b>
				</td>
			</tr>
			<%for (ConvertHashMap data: myGoreeList) { %>
				<tr style="background-color:#ccffff">
					<td> 
						<a href="<%=rootUrl%>/content/contentTitleMngForm.action?tt_no=<%=data.ToString("TT_NO")%>">[수정]</a>
						<a href="<%=rootUrl%>/content/contentDtlList.action?tt_no=<%=data.ToString("TT_NO")%>"><%= data.ToString("TITLE_NAME")%></a>
					</td>
					<td> 
						<%= data.ToString("ALL_CNT")%>
					</td>
					<td> 
						<%= data.ToString("OUT_GOREE_CNT")%>
					</td>
					<td> 
						<%= data.ToString("IN_GOREE_CNT")%>
					</td>
					<td> 
						<%= data.ToString("INVITE_CNT")%>
					</td>
					<td>
						0
					</td>
				</tr>
			<%} %>
		</table>
		<br/><br/>
		<table style="width:800px; margin:0 auto;" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan=5><span class="TL2">◆  내가 참여한고리</span><br/><br/></td>
			</tr>
			<tr style="background-color:#006699;color: #FFFFFF;" height="30px">
				<td width='400px'> 
					<b>고리이름</b>
				</td>
				<td> 
					<b>소유자정보</b>
				</td>
				<td>  
					<b>상태</b>
				</td>
				
			</tr> 
			<%for (ConvertHashMap data: MyInList) { %>
			
			<tr style="background-color:#ccffff">
				<td> 
					<a href="<%=rootUrl%>/content/contentDtlList.action?tt_no=<%=data.ToString("TT_NO")%>"><%= data.ToString("TITLE_NAME")%></a>
				</td>
				<td> 
					<%= data.ToString("MEM_NM")%>(<%= data.ToString("MEM_ID")%>)
				</td>
				<td> 
					<%= CodeDetailDAOImpl.selectCodeGroupDetail("CS007",data.ToString("STAT")).getDetail_nm()%>
					<!-- 가입완료된 경우 -->
					<%if ("01".equals(data.ToString("STAT"))) {%>
						<br/>[탈퇴하기]
					<!-- 가입신청된 경우 -->
					<% } else if ("00".equals(data.ToString("STAT"))) { %>
						<br/>[탈퇴하기]
					<%} %>
				</td>
			</tr>
			<tr style="background-color:#ccffff">
				<td colspan=3> 
					<%= data.ToString("TITLE_DESC")%>
				</td>
			</tr>
			
			<%} %>
		</table>	
				
	</td>
	</tr>
	<tr>
		<td>
			<br/><br/>
		</td>
	</tr>
</table>

<!--  내용 종료 -->


<script language="javascript">
	
</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

