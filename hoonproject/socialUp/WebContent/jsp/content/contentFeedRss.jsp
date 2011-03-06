<%@page import="com.sun.syndication.io.SyndFeedOutput"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%@page import="com.sun.syndication.feed.synd.SyndFeed"%>
<%
	SyndFeed syndFeed = (SyndFeed)request.getAttribute("syndFeed");

	SyndFeedOutput syndFeedOutput = new SyndFeedOutput();
	
	response.setContentType("application/xml; charset=UTF-8");
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires",0);
	
	syndFeedOutput.output(syndFeed, response.getWriter());
%>


