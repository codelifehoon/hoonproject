<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.common.util.ConvertHashMap"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentSourceTblDTO" %>
<%@ page import="java.util.List" %>
<%@page import="socialUp.service.member.dto.MemTblDTO"%>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	List<ContentTitleTblDTO> 	contentTitleList 	= (List<ContentTitleTblDTO>)request.getAttribute("contentTitleList");
	List<ContentSourceTblDTO> 	contentSourceList	= (List<ContentSourceTblDTO>)request.getAttribute("contentSourceList");
	List<ConvertHashMap>  		etcCnt 				= (List<ConvertHashMap>)request.getAttribute("ETC_CNT");
	
	ContentTitleTblDTO 	contentTitleTbl = null;
	ConvertHashMap		data = null;
	
	contentTitleTbl = contentTitleList.get(0);
	data 			= etcCnt.get(0);
	
	
%>


<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>
<!--  내용 시작 -->
 <form name="defForm" method="post" action="<%=rootUrl%>/content/contentUpdateFinish.action">
 <input type="hidden" name="tt_no" value="<%=contentTitleTbl.getTt_no() %>">
 
 
  <br>
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<span class="TL1">고리기본정보 관리</span>
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
					<%=contentTitleTbl.getTitle_name() %>
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
					<input type='input' name="title_desc" class="input400" value="<%=contentTitleTbl.getTitle_desc() %>"/>
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
					<h2>◆ 고리기본주소 :  </h2>
				</td>
				<td>
					http://goreee.com/go/<%=contentTitleTbl.getTitle_short_name() %>
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
					 	<option value="Y" >고리를 공개합니다.</option>
					 	<option value="N" >고리를 비공개합니다.</option>
					 </select>
					    
					 / 고리가져가기 허용여부 
					 <select name="branch_conf_yn" id="branch_conf_yn">
					 	<option value="Y">예</option>
					 	<option value="N">아니오</option>
					 </select>
				</h2>   

				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      * 고리를 공개할경우 저작권이 있는 다른 사용자의 글을 등록시 저작권침해가 됩니다.
				 <br/>다른 사람의글 등록시 신중하게 해주세요</h5>
				</td>
			</tr>
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
					 	<option value="Y" <%= "Y".equals(contentTitleTbl.getOrder_mem_join_yn()) ? "selected" : "" %>>다른 사용장의 참여를 허락합니다.</option>
					 	<option value="N" <%= !"Y".equals(contentTitleTbl.getOrder_mem_join_yn()) ? "selected" : "" %> >다른 사용장의 참여를 거부합니다.</option>
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
								<td width="150px"><INPUT TYPE=radio NAME="order_mem_join_metd" id="order_mem_join_metd" VALUE="01">참여신청시 바로 참여 </td>
								<td></td>
							</tr>
							<tr>
								<td ></td>
								<td ></td>
								<td><INPUT TYPE=radio NAME="order_mem_join_metd" id="order_mem_join_metd" VALUE="02">비밀번호 참여 </td>
								<td>비밀번호: <input type='input' name="order_mem_join_passwd" class="input150" value="<%=contentTitleTbl.getOrder_mem_join_passwd() %>"/></td>
							</tr>
							<tr>
								<td ></td>
								<td ></td>
								<td><INPUT TYPE=radio NAME="order_mem_join_metd" id="order_mem_join_metd" VALUE="03">나의 승인필요</td>
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
				<td colspan=2>
				<span class="TL2">고리컨텐츠 관리</span>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/>
				</td>
			</tr>
			<tr>
				<td width='170px'>
					<h2>◆ 글타래등록/수정 </h2>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 관심있는   rss 또는 트위터를 등록해서 고리로 모아 봅니다 등록방식은 콤마(,)또는 줄바꿈으로<br/>
      				구분해서 등록 해주세요</h5>
      				<br/>▷ RSS 수정<br/>
      				<%for (int i=0;i<contentSourceList.size();i++) 
      				{
      					if ("01".equals(contentSourceList.get(i).getSource_kind()) )
      					{
      				%>
					<input type="hidden" name="cs_no" value="<%=contentSourceList.get(i).getCs_no() %>">      				
      				<input type='input' name="" class="input400" readonly value="<%=contentSourceList.get(i).getRss2_url() %>"></input>
      				<select name="cs_reg_stat">
					 	<option value="<%=contentSourceList.get(i).getReg_stat()%>" ><%=CodeDetailDAOImpl.selectCodeGroupDetail("CS004",contentSourceList.get(i).getReg_stat()).getDetail_nm() %></option>
					 	<option value="04" >등록삭제</option>
					 </select>
      				<br/>
      				<%}} %>
      				
      				<br/>▷ RSS 등록<br/>
					<textarea name="rss_source" cols="78" rows="5"></textarea>
					<br/>예제) http://naver.com/rss1, http://naver.com/rss2 또는 
					<br/>http://naver.com/rss
					<br/>http://naver.com/rss2
					<br/>
					<br/>▷ 트위터 수정<br/>
					<%for (int i=0;i<contentSourceList.size();i++) 
      				{
      					if ("02".equals(contentSourceList.get(i).getSource_kind()) )
      					{
      				%>
      				<input type="hidden" name="cs_no" value="<%=contentSourceList.get(i).getCs_no() %>">      				
      				<input type='input' name="" class="input400" readonly value="<%=contentSourceList.get(i).getRss2_url() %>"></input>
      				<select name="cs_reg_stat">
					 	<option value="<%=contentSourceList.get(i).getReg_stat()%>" ><%=CodeDetailDAOImpl.selectCodeGroupDetail("CS004",contentSourceList.get(i).getReg_stat()).getDetail_nm() %></option>
					 	<option value="04" >등록삭제</option>
					 </select>
      				<br/>
      				<%}} %>
      				
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
				<td colspan=2 align='center'>
				<br/><span class="button blue xLarge"><button type="button" onClick="javascript:UpdateContentTitle()">수 정 하 기</button></span>
				</td>
			</tr>
			
			
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td width='170px'>
					<h2>◆ 고리 등록/수정</h2>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<h5>* 나의고리에   내가 참여시킨 다른 사용자의 고리 또는 다른사용자가 스스로 참여한 고리의 목록을관리 합니디.</h5>
				</td>
			</tr>
			<tr>
				<td  colspan='2'>
					<br/>
					<h3>내가 가져온 고리 :  <%=data.ToString("IN_TYPE1_CNT") %> 건 <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('IN_TYPE1_CNT')">상세정보</button></span></h3>  
					<h3>남이 가져온 고리 :  <%=data.ToString("IN_TYPE2_CNT") %> 건  <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('IN_TYPE2_CNT')">상세정보</button></span>  </h3>
				</td>
				
			</tr>
			
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<span class="TL2">고리참여자 현황/관리</span>
				</td>
				
			</tr>
			<tr>
				<td  colspan='2'>
					<br/>
					<h3>
					◆ 참여자수 :  <%=data.ToString("MEM_JOIN_CNT") %>명  <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('MEM_JOIN_CNT')">상세정보</button></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
					◆ 참여신청자: <%=data.ToString("MEM_REQJOIN_CNT") %>명  <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('MEM_REQJOIN_CNT')">상세정보</button></span>  
					</h3>  
				</td>
				
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<span class="TL2">고리홍보 현황/관리</span>
				</td>
			</tr>
			<tr>
				<td  colspan='2'>
					<br/>
					<h3>
					◆  초대현황 :  -- 명 초대  , -- 명 수락    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('FR_INVITE_DTL')">상세정보</button></span>
					<span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('FR_INVITE')">친구초대하기</button></span>
					</h3>  
				</td>
			</tr>
			<tr>
				<td colspan=2>
				<br/><br/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<span class="TL2">고리 전파 현황</span>
				</td>
			</tr>
			<tr>
				<td  colspan='2'>
					<br/>
					<h3>
					◆ 내가 가져가붙인 고리 :  <%=data.ToString("OUT_TYPE1_CNT") %> 건       <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('OUT_TYPE1_CNT')">상세정보</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					◆ 남이 가져가붙인 고리 :  <%=data.ToString("OUT_TYPE2_CNT") %> 건       <span class="button blue small"><button type="button" onClick="javascript:openDtlPopUp('OUT_TYPE2_CNT')">상세정보</button></span>
					
					
					</h3>  
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

function  UpdateContentTitle()
{
	getF().submit();
}

function openDtlPopUp(popUpKind)
{
	alert(popUpKind + "준비중.");
	if (popUpKind == "IN_TYPE1_CNT")
	{}
	else if (popUpKind == "IN_TYPE2_CNT")
	{}
	else if (popUpKind == "MEM_JOIN_CNT")
	{}
	else if (popUpKind == "MEM_REQJOIN_CNT")
	{}
	else if (popUpKind == "OUT_TYPE1_CNT")
	{}
	else if (popUpKind == "OUT_TYPE1_CNT2")
	{}
	
}
function initValues()
{
	<% if ( "Y".equals(contentTitleTbl.getOrder_mem_open_yn())) {%>
		getF().order_mem_open_yn.value = "Y";
	<%}else {%>
		getF().order_mem_open_yn.value = "N";
	<%}%>
	
	<% if ( "Y".equals(contentTitleTbl.getBranch_conf_yn())) {%>
		getF().branch_conf_yn.value = "Y";
	<%}else {%>
		getF().branch_conf_yn.value = "N";
	<%}%>

	

	
	<% if ( "Y".equals(contentTitleTbl.getOrder_mem_join_yn())) {%>
		getF().order_mem_join_yn.value = "Y";
	<%}else {%>
		getF().order_mem_join_yn.value = "N";
	<%}%>
	

	<% if (contentTitleTbl.getOrder_mem_join_metd() != null && !"".equals(contentTitleTbl.getOrder_mem_join_metd())){%>
	
		choiceRadio(getF().order_mem_join_metd, <%=contentTitleTbl.getOrder_mem_join_metd()%>)
		
	<%}%>
	
	JoinMethodChange();
	
	
}

initValues();

</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

