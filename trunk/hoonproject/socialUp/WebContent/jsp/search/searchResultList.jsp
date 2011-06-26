<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.common.util.NumUtil"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.SearchDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	List<ContentDtlTblDTO> 	contentDtlList 	= (List<ContentDtlTblDTO>)request.getAttribute("contentDtlList");
	SearchDTO 				search			= (SearchDTO)request.getAttribute("search");

	
	int choicePageNum	= NumUtil.toInt(CmnUtil.nvl(request.getParameter("choicePageNum"),"1")); // 현재 page 번호
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

	</script>
</head>

<%@ include file="/jsp/common/gnb_sub.jsp" %>

			
			<div class="main_content">

			<% if (contentDtlList.size() > 0)  
			{ 
			%>					      					 		

				<div class="sd_main">
						<h2><%=search.getSearchStr() %> 검색결과</h2>
						<%for (int i=0;i<contentDtlList.size();i++) 
							{
							ContentDtlTblDTO contentDtl  =  contentDtlList.get(i);
						%>	
	 					     <p>
	 					     	<a href="<%=rootUrl %>/content/contentDtlView.action?cd_no=<%=contentDtl.getCd_no()%>&tt_no=<%=contentDtl.getTt_no() %>&choicePageNum=<%=choicePageNum %>"><%=contentDtl.getContent_title() %></a>
	 					     </p>
	 					     <p>
		 					     <h5>
		 					     	<%=contentDtl.getContent_desc() %>...
		 					     </h5>
	 					     </p>
	 						<p class="date"> posted by <%=contentDtl.getAuthor_nm() %> | read[<%=contentDtl.getHit_cnt()%>] | comment[<%=contentDtl.getCdc_cnt()%>]  </p>
 						<%} %>				
				</div>
				
				
				<p class="datecenter">
				<%
				if (contentDtlList.size()>0)
				{
					ContentDtlTblDTO contentDtl  =  contentDtlList.get(0);
					int allRowNum 		=  contentDtl.getAllRowNum();		// 검색된 전체 row수
					int pageBarCount 	=  contentDtl.getPageBarCount();	// page bar 표시수
					int startRowNum 	= contentDtl.getStartRowNum();		// 조회된 건의 시작 row번호
					int pageRowCount	= contentDtl.getPageRowCount();		// 페이지 하나당 row 수
					// choicePageNum		 현재 page 번호
				%>
					
				<jsp:include page="/jsp/common/incPageBarCommon.jsp">
						<jsp:param name="allRowNum" value="<%=allRowNum %>"/>
						<jsp:param name="pageBarCount" value="<%=pageBarCount %>"/>
						<jsp:param name="pageRowCount" value="<%=pageRowCount %>"/>
						<jsp:param name="choicePageNum" value="<%=choicePageNum %>"/>
						<jsp:param name="ttNo" value=""/>
						<jsp:param name="rootUrl" value="<%=rootUrl %>"/>
						<jsp:param name="searchURLType" value="02"/>
						<jsp:param name="searchStr" value="<%=search.getSearchStr() %>"/>
				</jsp:include>
				
				<%} %>
			</p>
			<%} else { %>
			<div class="sd_main">
						<h2>검색결과 없음</h2>
			</div>	
			<%} %>	
			</div>
		
				
<%@ include file="/jsp/common/footer.jsp" %>
