<%@page import="socialUp.service.content.dto.ContentJoinMemDTO"%>
<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	
	
	ContentTitleTblDTO contentTitle =  (ContentTitleTblDTO)request.getAttribute("contentTitle");
		

	

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>
	
	<script language="javascript">
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

			<div class="main_content">
			
			<div class="sd_main">
			<h4>
			<%if ("01".equals(contentTitle.getOrder_mem_join_metd())) {%>
				
					<%=contentTitle.getTitle_name() %> 에 가입이 완료 되었습니다. 해당 컨텐츠 항목에 자유롭게 이용가능합니다.<br/>
					<a href="<%=rootUrl %>/content/contentDtlList.action?tt_no=<%=contentTitle.getTt_no() %>">[여기]</a> 를 클릭하면 컨텐츠로 이동
				
			<%} else if ("02".equals(contentTitle.getOrder_mem_join_metd())) {%>
				<%if ( "0".equals(contentTitle.getRetCode())) { %>
					<%=contentTitle.getTitle_name() %> 에 가입이 완료 되었습니다. 해당 컨텐츠 항목에 자유롭게 이용가능합니다.<br/>
					<a href="<%=rootUrl %>/content/contentDtlList.action?tt_no=<%=contentTitle.getTt_no() %>">[여기]</a> 를 클릭하면 컨텐츠로 이동
				<%} else { %>
					<%=contentTitle.getTitle_name() %>의 가입 비밀번호가 틀렸습니다.  다시 입력 해주세요.<br/>
					<a href="javascript:history.back(0)">[뒤로 돌아가기]</a>
				<%} %>
			<%} else if ("03".equals(contentTitle.getOrder_mem_join_metd())) {%>
					<%=contentTitle.getTitle_name() %> 에 가입신청이 완료 되었습니다. 해당 컨텐츠  관리자가 승인을 해주면  참여 가능합니다.
			<%} %>
			</h4>
			</div>
				
			</div>


				
<%@ include file="/jsp/common/footer.jsp" %>


