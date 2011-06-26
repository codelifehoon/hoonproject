<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentDtlCommentDTO"%>
<%@page import="socialUp.common.util.ConvertHashMap"%>
<%@ page import="socialUp.common.util.NumUtil"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	List<ContentDtlCommentDTO> contentDtlCommentList = (List<ContentDtlCommentDTO> )request.getAttribute("contentDtlCommentList");
%>

<% for(ContentDtlCommentDTO dtlComment : contentDtlCommentList) {%>
<span class="list_line">
				<span class="list_line_column1"><h5><%=dtlComment.getMemTblDTO().getMem_nm() %>: <%=dtlComment.getComment() %></h5></span>
				<span class="list_line_column2">
				<%if ( authInfo.getMt_no().equals(dtlComment.getCreate_no())) { %>
					<button type="button" onclick="javascript:editCommentPop('U',<%=dtlComment.getCdc_no() %>,'<%=dtlComment.getComment() %>','<%=dtlComment.getCreate_no() %>','<%=dtlComment.getCd_no() %>')">수 정</button>
					<button type="button" onclick="javascript:editCommentPop('D',<%=dtlComment.getCdc_no() %>,'<%=dtlComment.getComment() %>','<%=dtlComment.getCreate_no() %>','<%=dtlComment.getCd_no() %>')">삭 제</button>
				<%} %>
				</span>
</span>
<%} %>
			
