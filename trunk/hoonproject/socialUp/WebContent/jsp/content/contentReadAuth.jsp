<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script language="javascript">
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>
			<div class="main_content"> 

				<div class="sd_main">
<% if(authInfo.isAuth()) {%>
					<h2>선택한글에 대한 읽기 권한이 없습니다. <br> 해당 글을 읽고 싶으시면 아래의 방법중 하나를 선택 해주세요.</h2>
					
					<%
					// 브랜치가 가능한 타이블일 경우
					if ("Y".equals(contentTitle.getBranch_conf_yn())) { %>
					<h4><%=contentTitle.getTitle_name() %> 고리를 가지치기 하세요. 가지치기를 원하시면 <a href='<%=rootUrl%>/content/contentBranchTitleForm.action?tt_no=<%=contentTitle.getTt_no() %>'>[여기를]</a> 클릭하세요</h4>
					<h3>
					 <b>고리 가지치기란</b> 해당 게시물의 모음을 자신을 자신의 것으로 가져가는것으로 </br>
					  즐겨찾기 모음을 복사한다고 생각하시면됩니다.</br>
					  가지치기로 가져간 게시물은 원게시물의 변화가 등록,수정이 발생할때 같이 변경이 되며</br>   
					   가지치기 이후본인이 직접 생성한 추가 컨텐츠(신규글,블로그글,트위터등)에 대해서는  변동이 일어나지 않습니다.</br> 
					</h3>
					<%}
					//
					if ("Y".equals(contentTitle.getOrder_mem_join_yn())) { %>
					<h4><%=contentTitle.getTitle_name() %> 고리에 참여하세요. 참여를 원하시면 <a href='<%=rootUrl%>/content/contentJoinMemForm.action?tt_no=<%=contentTitle.getTt_no() %>'>[여기를]</a> 클릭하세요</h4>
					<h3>
					<b>고리 참여란</b> 해당 게시물의  모음에 자신이 자신이 참여하는것으로  이  고리를 읽는 권한을 얻으면서</br> 
					본인이 작성한 컨텐츠에 대해서 해당 고리를 보는 모든이들에게 공유가 됩니다.</br>
					본 고리가 가지치가가 가능하다면  가지치가로 가져가는  사용자의 목록에도 자동으로 등록이 됩니다.</br>
					</h3>
					
					<%} %>
<%}
// 로그인전이거나 , 회원가입 전이라면
else{ %>
				<h2>선택한글에 대한 읽기 권한이 없습니다.</h2>
				로그인 또는 회원 가입 후 읽어주세요.
				[여기에..로그인  또는 회원 가입폼 나오기]  
<%} %>

				</div>
			</div>
</form>
				
<%@ include file="/jsp/common/footer.jsp" %>


