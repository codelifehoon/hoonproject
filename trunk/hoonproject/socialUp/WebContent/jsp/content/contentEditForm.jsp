<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentSourceTblDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	List<ContentTitleTblDTO> contentTitleList 	= (List<ContentTitleTblDTO>)request.getAttribute("contentTitleList");
	List<ContentSourceTblDTO> contentSourceList = (List<ContentSourceTblDTO>)request.getAttribute("contentSourceList");
	ContentSourceTblDTO contentSource01 = null;
	String cs_no_arr  = "";
	int    csFavoriteCount =0;		// 관심컨텐츠 등록숫자
	ContentTitleTblDTO contentTitle = contentTitleList.get(0);

	// 자신의 컨텐츠 소스정보는  별도로 표시해 주시
	for (int i=0;i<contentSourceList.size();i++)
	{
		cs_no_arr += contentSourceList.get(i).getCs_no() + ",";
		if ("01".equals(contentSourceList.get(i).getSource_owner_kind()))
		{
			contentSource01 = contentSourceList.get(i);
		}
		else
		{
			csFavoriteCount++;
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script language="javascript">
	function getForm()
	{
		return document.defForm;
		
	}

	// 컨텐츠 삭제여부 버튼
	function contentDelYn(cs_no)
	{
		var del_cs_btn 	= eval("getForm().del_cs_btn" + cs_no);
		var use_yn 		= eval("getForm().use_yn" + cs_no);

		if (use_yn.value == "Y")
		{
			use_yn.value = "N";
			del_cs_btn.value = "복구";
			
		}
		else
		{
			use_yn.value = "Y";
			del_cs_btn.value = "삭제]";
		}
	}
	function contentEditProc()
	{
		getForm().submit();
		
	}
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

			
			<div class="main_content">

<form name="defForm" method="post" action="<%=rootUrl%>/content/contentEditFinish.action">
<input type="hidden" name="tt_no" 	value="<%=contentTitle.getTt_no() %>"/>	<!-- 타이틀번호 -->
<input type="hidden" name="cs_no_arr" 	value="<%=cs_no_arr %>"/>				<!-- 해당 타이틀에 소속된 컨텐츠 소스 목록 -->
<input type="hidden" name="validatecontentTitle" value="<%=contentTitle.getTitle_name() 
														+ contentTitle.getOrder_mem_open_yn()
														+ contentTitle.getBranch_conf_yn()
														+ contentTitle.getOrder_mem_join_yn()
														+ contentTitle.getOrder_mem_join_passwd()
														 %>"/>
					      					 		

				<div class="sd_main">
					<div class="text_padding">	
						<h2>친구들과 공유연결될  블로그, 관심 RSS를  등록하세요.</h2>
					      <p class="dateleft">나의 블로그 등록정보</p>
					      <table >
					      	<tr>
					      		<td>타이틀명</td>
					      		<td>공개여부</td>
					      		<td>가지치기</td>
					      		<td>참여여부</td>
					      		<td>참여비번</td>
					      		</tr>
					      	<tr>
					      		<td><input type="text" name="title_name" size='35' value="<%=contentTitle.getTitle_name() %>"/></td>
					      		<td>
					      		
					      		<select name="order_mem_open_yn"  style="" onChange="">
										 <option value="Y" <%= "Y".equals(contentTitle.getOrder_mem_open_yn())?"selected":"" %>>공개</option>
										  <option value="N" <%= "N".equals(contentTitle.getOrder_mem_open_yn())?"selected":"" %> >비공개</option>
								</select>
								</td>
								<td>
								<select name=branch_conf_yn  style="" onChange="">
										 <option value="Y" <%= "Y".equals(contentTitle.getBranch_conf_yn())?"selected":"" %>>공개</option>
										  <option value="N" <%= "N".equals(contentTitle.getBranch_conf_yn())?"selected":"" %>>비공개</option>
								</select>
								</td>
								<td>
									<select name=order_mem_join_yn  style="" onChange="">
											 <option value="Y" <%= "Y".equals(contentTitle.getOrder_mem_join_yn())?"selected":"" %>>가능</option>
											  <option value="N" <%= "N".equals(contentTitle.getOrder_mem_join_yn())?"selected":"" %>>불가능</option>
									</select>
								</td>
								<td>
									<input type="text" name="order_mem_join_passwd" size='6' value="<%= contentTitle.getOrder_mem_join_passwd() %>"/>
								</td>
								
					      	</tr>
					      </table>
						<p class="dateleft">나의 관심블로그 등록정보</p>
<!-- 자신의 컨텐츠 정보 시작 -->
						<%
							if (contentSource01 != null)
							{
								
						%>		
										
						<table >
					      	<tr>
					      		<td align='center'>블로그사이트</td>
					      		<td>로그인ID</td>
					      		<td>나의 블로그/RSS 인터넷주소(URL)</td>
					      		<td>등록상태</td>
					      		<td>인증상태</td>
					      		<td>삭제여부</td>
					      	</tr>
					      	<tr>
					      		<td>
					      		<select name="source_dtl_kind<%=contentSource01.getCs_no() %>"  style="" onChange="javascript:onSiteChange()">
					      			<%=CodeDetailDAOImpl.selectCodeGroupOptionString("CS002",contentSource01.getSource_dtl_kind()) %>
					      		</select>
					      		</td>
					      		<td><input type="text" name="source_login_id<%=contentSource01.getCs_no() %>" size='10' value="<%= contentSource01.getSource_login_id()%>"/></td>
					      		<td><input type="text" name="rss2_url<%=contentSource01.getCs_no() %>" size='35' value="<%= contentSource01.getRss2_url()%>"/></td>
					      		<td><%= CodeDetailDAOImpl.selectCodeGroupDetail("CS004",contentSource01.getReg_stat()).getDetail_nm() %></td>
					      		<td>인증상태추가</td>
					      		<td>
					      			<!--  hidden 정보 -->
					      			<input type="button" name="del_cs_btn<%= contentSource01.getCs_no() %>" value="삭제" onClick="javascript:contentDelYn('<%=contentSource01.getCs_no() %>')"/>
					      			<input type="hidden" name="use_yn<%= contentSource01.getCs_no() %>" value="Y"/>
					      			<input type="hidden" name="source_owner_kind<%= contentSource01.getCs_no() %>" value="<%= contentSource01.getSource_owner_kind() %>"/>
					      			
					      			<!-- 데이터 변경여부 검증을 위해 최초 수정가능한정보 수정전  보관 -->
					      			<input type="hidden" name="validateEditDate<%= contentSource01.getCs_no() %>" 
					      					 value="<%= contentSource01.getSource_dtl_kind()
					      					 			+ contentSource01.getSource_login_id() 
					      					 			+ contentSource01.getRss2_url()
					      					 			+ "Y"
					      					 		%>"/>
					      	</tr>
					    </table>
					    <%} %>	
<!-- 자신의 컨텐츠 정보 끝-->							

<!-- 관심 컨텐츠 정보 시작 -->
					<% if (csFavoriteCount > 0 ) { %> <p class="dateleft"></p> <%} %>					
						<% 
						for (int i=0;i<contentSourceList.size();i++)
							{
								ContentSourceTblDTO contentSourceTbl = contentSourceList.get(i);
								// 자신의 컨텐츠를 제외하고 효시한다.
								if (contentSourceTbl.getCs_no() != contentSource01.getCs_no())
								{
						%>		
						<table >
					      	<tr>
					      		
					      		<td>나의 블로그/RSS 인터넷주소(URL)</td>
					      		<td>등록상태</td>
					      		<td>인증상태</td>
					      		<td>삭제여부</td>
					      	</tr>
					      	<tr>
					      		<td><input type="text" name="rss2_url<%=contentSourceTbl.getCs_no() %>" size='71' value="<%= contentSourceTbl.getRss2_url()%>"/></td>
					      		<td><%= CodeDetailDAOImpl.selectCodeGroupDetail("CS004",contentSourceTbl.getReg_stat()).getDetail_nm() %></td>
					      		<td>인증상태추가</td>
					      		<td>
					      			
					      			<input type="button" name="del_cs_btn<%= contentSourceTbl.getCs_no() %>" value="삭제" onClick="javascript:contentDelYn('<%=contentSourceTbl.getCs_no() %>')"/>

					      			<!--  hidden 정보 -->
									<input type="hidden" name="source_dtl_kind<%= contentSourceTbl.getCs_no() %>" value="<%= contentSourceTbl.getSource_dtl_kind() %>"/>
									<input type="hidden" name="source_login_id<%= contentSourceTbl.getCs_no() %>" value="<%= contentSourceTbl.getSource_login_id() %>"/>
					      			<input type="hidden" name="use_yn<%= contentSourceTbl.getCs_no() %>" value="Y"/>
					      			<input type="hidden" name="source_owner_kind<%= contentSourceTbl.getCs_no() %>" value="<%= contentSourceTbl.getSource_owner_kind() %>"/>
					      			<!-- 데이터 변경여부 검증을 위해 최초 수정가능한정보 수정전  보관 -->
					      			<input type="hidden" name="validateEditDate<%= contentSourceTbl.getCs_no() %>" 
					      					 value="<%= contentSourceTbl.getSource_dtl_kind()
				      					 			+ contentSourceTbl.getSource_login_id() 
				      					 			+ contentSourceTbl.getRss2_url()
				      					 			+ "Y"
				      					 			%>"/>
					      		</td>
					      	</tr>
					    </table>
						    <%}
						} %>	
<!-- 관심 컨텐츠 정보 끝-->							


						
						<br></br>
						
						<h2>친구들과 공유할 Twitter를  등록하세요.</h2>	
 					     <p> 친구들과  같이 나눌 트위터 등록 등록 등록방법은 <a>여기를</a>참조하시오.</p>
 						<p class="date">Posted by Jack <img src="<%=rootUrl%>images/more.gif" alt="" /> <a href="#">Read more</a> <img src="<%=rootUrl%>images/comment.gif" alt="" /> <a href="#">Comments(15)</a> <img src="<%=rootUrl%>images/timeicon.gif" alt="" /> 13.01.</p><br />				
					</div>
					
					<p align='center'><a href='javascript:contentEditProc()'>******* 수정 *******</a></p>
				</div>
</form>
				
				<div class="sd_left"><h2>list</h1></div>
				<ul class="list_line">
						<li class="colum1">colum1</li>
						<li class="colum2">colum2</li>
						<li class="colum3">colum3</li>
						<li class="colum4">colum4</li>
						<li class="colum5">colum5</li>
				</ul>
				</div>
				
				
<%@ include file="/jsp/common/footer.jsp" %>

