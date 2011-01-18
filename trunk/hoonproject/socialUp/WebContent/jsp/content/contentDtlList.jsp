<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.common.util.NumUtil"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");
	List<ContentDtlTblDTO> contentDtlList = contentTitle.getContentDtlList();
	
	int choicePageNum	= NumUtil.toInt(CmnUtil.nvl(request.getParameter("choicePageNum"),"1")); // 현재 page 번호
	String ttNo 		= request.getParameter("tt_no");
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

					      					 		

				<div class="sd_main">
		
						<h2><%=contentTitle.getTitle_name() %></h2>
						
						<h3 align='right'>
							<!-- 로그인 회원과 같지 않다면 (로그인이 안된상태도 표시된다) -->
							<%if ( !authInfo.getMt_no().equals(contentTitle.getMt_no())) {%>
								<a href='<%=rootUrl%>/content/contentJoinContFrom.action?tt_no=<%=ttNo %>'>[여기에 나의 컨텐츠타이틀 참여&제외]</a>
							<%} %>
								<a href='<%=rootUrl%>/content/contentDtlEditForm.action?tt_no=<%=ttNo %>'>[새글쓰기]</a> 
						</h3>
						<%for (int i=0;i<contentDtlList.size();i++) 
							{
							ContentDtlTblDTO contentDtl  =  contentDtlList.get(i);
						%>	
	 					     <p>
	 					     	<a href="<%=rootUrl %>/content/contentDtlView.action?cd_no=<%=contentDtl.getCd_no()%>&tt_no=<%=ttNo %>&choicePageNum=<%=choicePageNum %>"><%=contentDtl.getContent_title() %></a>
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
						<jsp:param name="ttNo" value="<%=ttNo %>"/>
						<jsp:param name="rootUrl" value="<%=rootUrl %>"/>
						<jsp:param name="searchURLType" value="01"/>
				</jsp:include>
				
				<%} %>
			</p>
					
			</div>
			
			
			
			


<!-- 
				<div class="sd_left"><h2>list</h1></div>
				<ul class="list_line">
						<li class="colum1">colum1</li>
						<li class="colum2">colum2</li>
						<li class="colum3">colum3</li>
						<li class="colum4">colum4</li>
						<li class="colum5">colum5</li>
				</ul>
				</div>
 -->			
				
<%@ include file="/jsp/common/footer.jsp" %>
