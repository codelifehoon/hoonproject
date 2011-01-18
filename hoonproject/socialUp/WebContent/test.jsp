<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@page import="socialUp.common.ServiceFactory"%>
<%@page import="socialUp.service.content.ContentService"%>
<%@page import="socialUp.service.content.dto.ContentSourceTblDTO"%>
<%@page import="socialUp.common.DaoFactory"%>
<%@ page import="socialUp.service.common.dao.ReadContentDAO" %>
<%@ page import="socialUp.service.common.dao.ReadRssContentDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentDtlTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentCollectDTO" %>
<%@ page import="socialUp.service.content.ContentService" %>
<%@ page import="socialUp.service.content.ContentServiceImpl" %>
<%@ page import="socialUp.common.util.DateTime" %>

<%@ page import="socialUp.common.util.DebugUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%

		ContentService contentService = (ContentService)ServiceFactory.createService(ContentServiceImpl.class); 
		ReadContentDAO readContentDAO =(ReadContentDAO)DaoFactory.createDAO(ReadRssContentDAOImpl.class);

		ContentSourceTblDTO contentSource = new ContentSourceTblDTO();
		ContentCollectDTO contentCollect = new ContentCollectDTO();
		String sCurrentDate = DateTime.getFormatString("yyyyMMddHHmmss"); // 현재날짜

		/*
			contentSource.getTt_no()
			contentSource.getCs_no()
			contentSource.getMt_no()
			contentSource.getRead_fail_count();
			contentSource.getCreate_dt()
			contentSource.getSource_kind()
			contentSource.getRss2_url()
			contentSource.getContentCollect().getColl_no()
		*/
		
		contentCollect.setColl_no("2");
		
		contentSource.setTt_no("8");
		contentSource.setCs_no("15");
		contentSource.setMt_no("5");
		contentSource.setSource_kind("01");
		contentSource.setRead_fail_count("0");
		contentSource.setCreate_dt(sCurrentDate);
		//contentSource.setRss2_url("http://관심3_수정.com");
		contentSource.setRss2_url("http://iphoneblog.co.kr/rss");
		contentSource.setContentCollect(contentCollect);
		
		contentService.addContentCollect(contentSource);
				
		%>
