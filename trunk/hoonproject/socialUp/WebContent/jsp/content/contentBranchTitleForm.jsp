<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
//branch 를 원하는 content title의 정보
	ContentTitleTblDTO contentTitle 			= (ContentTitleTblDTO)request.getAttribute("contentTitle");

// 잔신이 소유한 content title의 정보
	List<ContentTitleTblDTO> myContentTitleList	= (List<ContentTitleTblDTO>)request.getAttribute("contentTitleList");
 

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
	function addBranchTitle()
	{
		getForm().submit();
	}
	</script>
</head> 
<%@ include file="/jsp/common/gnb_sub.jsp" %>
			<div class="main_content"> 

				<div class="sd_main">
					<h2>가지치기 방법을 선택 해주세요.</h2>

<!--  기존 자신의 content title 목록이 존재할때 -->
<%if (myContentTitleList.size()> 0) { %>
					<h4>* 기존 나의 고리중 하나에 등록 합니다.</h4>
					<h3>
<form name='defForm' action="/content/contentBranchTitleFinish.action" method="post">
<input type="hidden" name='branchType' value='I'/>		<!--  나의 기존 컨텐츠 타이틀에  선택한 컨텐츠 브랜치로 추가 할떄 -->
<input type="hidden" name='refTtNo' value='<%=contentTitle.getTt_no() %> '/>


					<select name='tt_no' >
						<%for (ContentTitleTblDTO ie : myContentTitleList) 
						{%>
							
						<option value='<%= ie.getTt_no()%>'><%=ie.getTitle_name() %></option>
						<%} %>
						
					</select>
					<input type='button' onclick ="javascript:addBranchTitle();" value='등록'> 
</form>
<%} %>

<!--  신규 컨텐츠 등록하면으로 이동한다. -->
					<h4>* 새로운 고리를 생성하면서 등록 합니다.</h4>
					<h3>
					<input type='button' name='<%=rootUrl %>/content/contentAddForm.action?refTtNo=<%=contentTitle.getTt_no() %>' value='신규생성'/>
					</h3>
					
				</div>
			</div>
</form>
				
<%@ include file="/jsp/common/footer.jsp" %>
<%
	if (myContentTitleList.size() == 0)
	{
%>
<script language='javascript'>

	// 자신의 기존 고리 모록이 없다면 그냥 신규 등록으로 리다이렉트 한다.
	 window.location.href ="<%=rootUrl %>/content/contentAddForm.action?ref_tt_no=<%=contentTitle.getTt_no() %>";
</script>
<% 	}%>



