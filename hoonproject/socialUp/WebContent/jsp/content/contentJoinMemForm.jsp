<%@page import="socialUp.service.content.dto.ContentJoinMemDTO"%>
<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	ContentTitleTblDTO 		contentTitle 		= (ContentTitleTblDTO)request.getAttribute("contentTitle");
	List<ContentJoinMemDTO> contentJoinMemList	= (List<ContentJoinMemDTO>)request.getAttribute("contentJoinMemList");
	ContentJoinMemDTO		contentJoinMem = null;
	
	// 기존에 가입되어 있어 있고 정상적인 회원으로 있다면 그냥 정상가입되었다는 메세지 출력
	if (contentJoinMemList.size() == 1) contentJoinMem =  contentJoinMemList.get(0);
	

 
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
	function getForm()
	{
		return document.defForm;
		
	}
	
	function joinMem()
	{
	
		getForm().submit();
	}
	
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

<form name="defForm"  action="<%=rootUrl %>/content/contentJoinMemFinish.action" method="post">
<input type="hidden" name="tt_no" value="<%=contentTitle.getTt_no() %>"/>

			<div class="main_content">

				
			<div class="sd_main">
				<!--  가입 정보가 있다면 -->
				<%if (contentJoinMem != null) {%>
				<!--  가입완료 상태라면 -->
						<% if("01".equals(contentJoinMem.getStat())) {%>
											<h4>
												<%=contentTitle.getTitle_name() %> 글모임에 이미 가입되어 있습니다. 
												<a href="<%=rootUrl %>/content/contentDtlList.action?tt_no=<%=contentTitle.getTt_no() %>">[글모음]으로 이동</a>
											</h4>
						<%}
						// 가입정보가 있지만  상태가 가입완료 상태가 아니라면
						else{ %>
										<h4>해당 글모임 가입신청 했으나 아직 <%=CodeDetailDAOImpl.selectCodeGroupDetail("CS007",contentJoinMem.getStat()).getDetail_nm() %> 상태입니다.</h4>
						<%} %>
				<!--  가입 정보가 없다면 -->
				<%} else { %>
						<!--  해당 컨텐츠 타이틀이 다른 사용자가 join 가능하도록 설정되어 있다면 -->
						<%if ( "Y".equals(contentTitle.getOrder_mem_join_yn())) { %>
							<h2>
								글모음에 참여를 원하시면 아래의 내용을 따라 주세요.
							</h2>
							</br>
							</br>
							
							<!-- 참여자유 -->
							 <%if ( "01".equals(contentTitle.getOrder_mem_join_metd())) { %>
							 	<%=contentTitle.getTitle_name() %> 글모임은 자유 참여가 가능합니다. 참여신청후 바로 조회및 등록이 가능하며<br/>
							 	 참여를 원하실경우  <a href="javascript:joinMem('<%=contentTitle.getOrder_mem_join_metd() %>')");">[참여신청]</a> 해주세요.
							 <%} %>
							 <!-- 비밀번호방식 -->
							 <%if ( "02".equals(contentTitle.getOrder_mem_join_metd())) { %>
							 	<%=contentTitle.getTitle_name() %> 글모임은 참여를 위한 비밀번호를 입력 해주세요.<br/>
							 	비밀번호 :<input type="text"  id="join_passwd" name="join_passwd" />
							 	 <a href="javascript:joinMem('<%=contentTitle.getOrder_mem_join_metd() %>')");">[참여신청]</a>
							 <%} %>
							 <!-- 참여신청확인 -->
							 <%if ( "03".equals(contentTitle.getOrder_mem_join_metd())) { %>
							 	<%=contentTitle.getTitle_name() %> 글모임은 참여신청을 하신구 관리자의 승인이 필요합니다. 관리자의 승인후 조회및 등록이 가능혀며 <br/>
							 	참여를 원하실경우 <a href="javascript:joinMem('<%=contentTitle.getOrder_mem_join_metd() %>')");">[참여신청]</a> 해주세요.
							 <%} %>
						<%} else { %>
							<h4>
								<%=contentTitle.getTitle_name() %>글 모임은 다른 사용자가 참여 불가능합니다. 
							</h4>
						<%} %>
				<%} %>
			</div>
				
			</div>
</form>

				
<%@ include file="/jsp/common/footer.jsp" %>


