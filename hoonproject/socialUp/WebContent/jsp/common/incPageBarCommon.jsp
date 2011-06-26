<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.common.util.NumUtil"%>
<%

	int allRowNum 		=  	NumUtil.toInt(request.getParameter("allRowNum"));		// 검색된 전체 row수
	int pageBarCount 	=  	NumUtil.toInt(request.getParameter("pageBarCount"));	// page bar 표시수
	int pageRowCount	= 	NumUtil.toInt(request.getParameter("pageRowCount"));	// 페이지 하나당 row 수
	int choicePageNum	= 	NumUtil.toInt(request.getParameter("choicePageNum"));
	String ttNo			= 	request.getParameter("ttNo");
	String rootUrl		= 	request.getParameter("rootUrl");
	String searchURLType= 	request.getParameter("searchURLType");		// 01 : 컨텐츠리스트에대한  페이지처리 , 02 : 통합검색에대한 페이지 처리
	String searchStr	= 	request.getParameter("searchStr");
	
	int startPageBar	=  (choicePageNum/(pageBarCount+1) ) * pageBarCount + 1;
	int endPageBar 		=  (choicePageNum/(pageBarCount+1) + 1) *pageBarCount;
	int maxPageBar		=  allRowNum/pageRowCount;
	if ( maxPageBar< endPageBar  ) endPageBar = maxPageBar;
	
	String prevStr= "";
	String nextStr= "";
			
	if ( startPageBar-1 > 0)
	{
		if ("01".equals(searchURLType))
		{
			prevStr  = "<a href='" + rootUrl + "/content/contentDtlList.action?tt_no=" +  ttNo
									+ "&choicePageNum=" + String.valueOf(startPageBar-1) + "'>[이전]</a>";
		}
		else if ("02".equals(searchURLType))
		{
			prevStr  = "<a href='" + rootUrl + "/search/searchResultList.action?searchStr=" +  searchStr
									+ "&choicePageNum=" + String.valueOf(startPageBar-1) + "'>[이전]</a>";
		}
		
	}
	else  prevStr  = "[이전]";
	
	if (endPageBar + 1 <= maxPageBar ) 
	{
		if ("01".equals(searchURLType))
		{
			nextStr  = "<a href='" + rootUrl + "/content/contentDtlList.action?tt_no=" +  ttNo
									+ "&choicePageNum=" + String.valueOf(endPageBar + 1) + "'>[이후]</a>";
		}
		else if ("02".equals(searchURLType))
		{
			prevStr  = "<a href='" + rootUrl + "/search/searchResultList.action?searchStr=" +  searchStr
									+ "&choicePageNum=" + String.valueOf(endPageBar + 1) + "'>[이후]</a>";
		 }
		
	}
	else  nextStr  = "[이후]";
	
	System.out.println("startPageBar:" + startPageBar);
	System.out.println("endPageBar:" + endPageBar);
	System.out.println("maxPageBar:" + maxPageBar);
	System.out.println("allRowNum:" + allRowNum);
	System.out.println("searchURLType:" + searchURLType);
	System.out.println("searchStr:" + searchStr);
	
%>
	
	<%=prevStr %>
<%
	for(int i=startPageBar;i <=endPageBar;i++)
	{
	
%>
<!--  페이지별 , 처리 -->
	<%if (i!=startPageBar){ %>,<%}%>
<!--  현재 페이지가 아니면 링크를 걸어준다 -->					
	<%if ( i == choicePageNum ) 
		{%>
			<%=i %> 
		<%}
	else
		{ 
			if ("01".equals(searchURLType))
			{%>
				<a href="<%=rootUrl %>/content/contentDtlList.action?tt_no=<%=ttNo %>&choicePageNum=<%=i %>"><%=i %></a>
		<%	}if ("02".equals(searchURLType))
			{%>
				<a href="<%=rootUrl %>/search/SearchResultList.action?searchStr=<%=searchStr %>&choicePageNum=<%=i %>"><%=i %></a>
		<%	} 
		}
	}
%>
<%=nextStr %>
