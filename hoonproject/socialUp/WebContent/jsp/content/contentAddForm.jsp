<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentBranchDTO"%>
<%@page import="socialUp.service.content.dao.ContentBranchDAO"%>
<%@page import="java.util.List"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
// 컨텐츠 타이틀을 새롭게 만들면서 기존 다른사람의 컨텐츠 타이틀을 브랜치를 가져올떄 값이 존재한다.
	ContentTitleTblDTO	branchContentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");  
	String ref_tt_no = "";
	
	if (branchContentTitle != null)
	{
		ref_tt_no = branchContentTitle.getTt_no();
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

	// 네이버 일때만 id 입력 가능하도록 설정
	function onSiteChange()
	{
		
		if (getForm().source_dtl_kind.value == "01")
		{
			getForm().source_login_id.value  ="";
			getForm().source_login_id.readOnly  = false;
			getEleById("inviteFriend").style.display = "";
		}
		else
		{
			getForm().source_login_id.value  ="-입력불가-";
			getForm().source_login_id.readOnly  = true;
			getEleById("inviteFriend").style.display = "none";
		}
	}
	
	function onfavoriteGoreBlur()
	{
		//alert("텍스트 에어리어에서포커스 떠났음");
	}
	
	function contentAddFinish()
	{
		var err_code = 0;
		
		if (getForm().rss2_url.value == "")  err_code = -1;


		if (err_code  < 0 )
		{
			switch (err_code)
			{
				case -1 : alert("블로그 또는 RSS 주소가 하나 이상은 필요합니다.");
						  return;
							
			}
			
		}
		
		getForm().submit();
	}
	</script>
	
	
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

			
			<div class="main_content">

<form name="defForm" method="post" action="<%=rootUrl%>/content/contentAddFinish.action">
<input type="hidden" name="source_kind" value="01"/>		<!-- 소스출처 (01:RSS로 일단 기본설정) -->			
<input type="hidden" name="branchTtno" value="<%=ref_tt_no %>"/>

				<div class="sd_main">
					<div class="text_padding">	
						<h2>친구들과 공유연결될  블로그, 관심 RSS를  등록하세요.</h2>
					      <p>나의 블로그 등록</p>
					      <table >
					      	<tr>
					      		<td align='center'>블로그사이트</td>
					      		<td>로그인ID</td>
					      		<td>나의 블로그/RSS 인터넷주소(URL)</td>
					      		<td>공개여부</td>
					      		<td>가지치기</td>
					      		<td>참여여부</td>
					      		<td>참여비번</td>
					      		
					      		
					      	</tr>
					      	<tr>
					      		<td>
					      			<select name="source_dtl_kind"  style="" onChange="javascript:onSiteChange()">
					      			<%=CodeDetailDAOImpl.selectCodeGroupOptionString("CS002","01") %>
					      			</select>
					      		</td>
					      		<td>
					      			<input type="text" name="source_login_id" size='10' />
					      			<div id="inviteFriend">
					      				<a href="javascript:alert('블로그이웃및주소록의친구목록이 나옴')">블로그이웃초대</a>
					      			</div>
					      		</td>
					      		<td><input type="text" name="rss2_url" size='35' /></td>
					      		<td>
					      		<select name="order_mem_open_yn"  style="" onChange="">
										 <option value="Y" selected>공개</option>
										  <option value="N" >비공개</option>
								</select>
								</td>
								<td>
								<select name=branch_conf_yn  style="" onChange="">
										 <option value="Y" selected>공개</option>
										  <option value="N" >비공개</option>
								</select>
								</td>
								<td>
									<select name=order_mem_join_yn  style="" onChange="">
											 <option value="Y" selected>가능</option>
											  <option value="N" >불가능</option>
									</select>
								</td>
								<td>
									<input type="text" name="order_mem_join_passwd" size='6' />
								</td>
								
					      	</tr>
					      </table>
						<p class="date"></p>
						<p>관심 블로그 또는 RSS를 등록하세요.</p>
						<TEXTAREA NAME="favoriteContent" COLS="110" ROWS="6" onblur="javascript:onfavoriteGoreBlur()"></TEXTAREA>	
					
						<br></br>
						
						<h2></h2>
						
						<%if (branchContentTitle != null) {%>
						<h2>기본포함 되는 컨텐츠 목록</h2>
						<h3>컨텐츠제목:<%=branchContentTitle.getTitle_name() %></h3>
						<h3>
							<p class="date">
							<% for (ContentBranchDTO  ie : branchContentTitle.getContentBranchList()) {%>
								<%=ie.getContentTitle().getTitle_name() %>,
							<%} %>
							</p>
						</h3>
						<%} %>
						<h2>친구들과 공유할 Twitter를  등록하세요.</h2>	
 					     <p> 친구들과  같이 나눌 트위터 등록 등록 등록방법은 <a>여기를</a>참조하시오.</p>
 						<p class="date">Posted by Jack <img src="<%=rootUrl%>images/more.gif" alt="" /> <a href="#">Read more</a> <img src="<%=rootUrl%>images/comment.gif" alt="" /> <a href="#">Comments(15)</a> <img src="<%=rootUrl%>images/timeicon.gif" alt="" /> 13.01.</p><br />				
					</div>
					
					<p align='center'><a href='javascript:contentAddFinish()'>******* 등록 *******</a></p>
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

