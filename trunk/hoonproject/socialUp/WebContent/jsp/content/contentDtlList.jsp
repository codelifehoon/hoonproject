<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="socialUp.service.content.dto.ContentDtlImgDTO"%>
<%@page import="socialUp.common.util.ConvertHashMap"%>
<%@ page import="socialUp.common.util.NumUtil"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");
	List<ContentDtlTblDTO> contentDtlList = contentTitle.getContentDtlList();
	
	int choicePageNum	= NumUtil.toInt(CmnUtil.nvl(request.getParameter("choicePageNum"),"1")); // 현재 page 번호
	String ttNo 		= request.getParameter("tt_no");

	System.out.print("authInfo.getMt_no():"+ authInfo.getMt_no());
	
%>


<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>

<!--  내용 시작 -->
 
 <div>
 <br/>
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">
					<tr>
						<td width="700px"> <span class="TL1"><%=contentTitle.getTitle_name() %></span> </td>
						<td width="300px">
						<span class="button blue xLarge"><button type="button" onClick="javascript:">글쓰기</button></span>
						<span class="button blue xLarge"><button type="button" onClick="javascript:">참여하기</button></span>
						<span class="button blue xLarge"><button type="button" onClick="javascript:">가져가기</button></span>
						</td>
					</tr>
					
				</table>
		</td>
	</tr>
	<tr>
		<td>
				<br/>
				<%if (choicePageNum == 1 ){%>
					<%=contentTitle.getTitle_desc() %>
				<%} %>
		</td>
	</tr>
	<tr>
		<td>
			<br/><br/>
		</td>
	</tr>
	
	
	<tr>
	<tr>
	<td>
		<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" height='600px' >
		<%
		String content_title_link = "";
			
		for (int i=0;i<contentDtlList.size();i++) 
			{
				ContentDtlTblDTO contentDtl  =  contentDtlList.get(i);
				List<ContentDtlImgDTO> contentDtlImgList = contentDtl.getContentDtlImgList();
				
				 
				if ("00".equals(contentDtl.getSource_kind()))	
						{	content_title_link = "자체링크 URL";}
				else	{	content_title_link = contentDtl.getContent_title_link();	}
				
				
		 %>	
		<tr><td valign='top'>
		
						
			<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0">
				<tr>
					<td width='600px'>

						<a href="<%=content_title_link %>"><h5><b><%=contentDtl.getContent_title() %> (Hit <%=contentDtl.getHit_cnt() %>건)</b></h5></a>
					</td>
					
					<td>
					<!--로그인 되어 있는 상태에서 글등록자와 로그인 사용자가 같아야 되고 && 자체 생성글 이어야한다. -->
					<%if  ( contentDtl.getMt_no().equals(authInfo.getMt_no())
							&& "00".equals(contentDtl.getSource_kind()) 
							&& !authInfo.getMt_no().equals("-1")) {%>
						<span class="button blue small"><button type="button">수 정</button></span>
						<span class="button blue small"><button type="button">삭 제</button></span>
					<%} %>
					</td>
				</tr>
			</table>
			
<!--click 하기전 나오는 상세내용 시작 -->
<div id="contentDesc_off_<%=contentDtl.getCd_no() %>">
			<h5>
				<%= CmnUtil.subString(CmnUtil.removeFullHtmlTag(contentDtl.getContent_desc()),200) %></br>
			</h5>
			<p>
				<!--  썸네일 이미지 보여주기 -->
				<%for (ContentDtlImgDTO  contentDtlImg : contentDtlImgList) {
					
				%>
					<img src="<%=contentDtlImg.getThumbnail_url() %>"/>
				<%} %>
			</p>
</div>
<!--click 하기전 나오는 상세내용 종료 -->

<!--click 하면 나오는 상세내용 시작 -->
<div id="contentDesc_on_<%=contentDtl.getCd_no() %>" 	style="display:none;">
			<h5>
				<%=  contentDtl.getContent_desc() %></br>
			</h5>
</div>
<!--click 하면 나오는 상세내용 종료 -->
			<div class="box"><a href="#" onclick="javascript:showComment(<%=contentDtl.getCd_no() %>)">댓글수 (<%= contentDtl.getCdc_cnt()%>)</a>,  작성자: <%=contentDtl.getAuthor_nm() %> , 작성일: <%=contentDtl.getOrg_create_dt() %></div>
			
			<div id="">
			<span class="list_line">
				<span class="list_line_column1"><input type='input' name="comment_<%=contentDtl.getCd_no() %>" id="comment_<%=contentDtl.getCd_no() %>" class="input500" value="댓글내용을 입력하세요" onClick="javascript:this.value='';""></span>
				<span class="list_line_column2">
				
				<!--  비회원일때만 비번 입력하는 창이 보이도록한다. -->
				<%if ("-1".equals(authInfo.getMt_no())) {%>
					<input type='input' name="comment_pw_<%=contentDtl.getCd_no() %>" id="comment_pw_<%=contentDtl.getCd_no() %>" class="input150" value="비밀번호"/>
				<%}else{ %>
					<input type='hidden' name="comment_pw_<%=contentDtl.getCd_no() %>" id="comment_pw_<%=contentDtl.getCd_no() %>"/>
				<% } %>
				
					<button onClick="addComment(<%=contentDtl.getCd_no()%>)" type="button"> 등     록 </button>
				</span>
			</span>
			</div>
			

<!--  comment 리스트가 나올 레이어 -->			
			<div id="contentComm_<%=contentDtl.getCd_no() %>"></div>
		</td>
		</tr>
		<tr><td>
		<hr>
		<!-- ____________________________________________________________________________________________________________________________________________________ -->
		</td></tr>
		<%} %>	
		</table>
	</td>
	</tr>

	
	<tr>
	<td>
	
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
						<jsp:param name="allRowNum" 	value="<%=allRowNum %>"/>
						<jsp:param name="pageBarCount" 	value="<%=pageBarCount %>"/>
						<jsp:param name="pageRowCount" 	value="<%=pageRowCount %>"/>
						<jsp:param name="choicePageNum" value="<%=choicePageNum %>"/>
						<jsp:param name="ttNo" 			value="<%=ttNo %>"/>
						<jsp:param name="rootUrl" 		value="<%=rootUrl %>"/>
						<jsp:param name="searchURLType" value="01"/>			
						<jsp:param name="searchStr" 	value="<%=search.getSearchStr() %>"/>
				</jsp:include>
				
				<%} %>
	</p>
			
	</td>
	</tr>
		
</table>
</div>
<!--  내용 종료 -->

<script language="javascript">

function showComment(cd_no)
{

	var retvalue=false;
	
	
	var dataParam = {"cd_no" : cd_no };
	var contentComm  =  getEleById("contentComm_" + cd_no);
		
      jQuery.ajax({
          type: "POST"
          ,async: false
          ,url: "<%=rootUrl%>/content/contentDtlCommentListAjax.action"
          ,dataType: "html"			// 응답의 결과로 반환되는 데이터의 종류를 식별하는 키워드
          ,data: dataParam
          //,contentType: "application/json; charset=utf-8"
          ,success: function(rs) 
          {
        	  contentComm.innerHTML = rs;
//        	  alert(rs);
/*
				
        	  alert(rs.retValList.length);
        	  for (var i=0; i < rs.retValList.length; i++ )
        	  {
        		  // comment 값이 들어 있는 obj
        		  var obj = rs.retValList[i];
        		  alert(obj.comment);
        	  }
*/        	  
              
          }
          ,error: function(data) { alert('서버와의 통신이 실패했습니다.'); retvalue=false; }
          ,complete: function() { }
      });

	return retvalue;

}

/*
 *	editType : A 추가 
 */
 
function addComment(cd_no)
{
	
	var retvalue=false;
	var commmentObj   = getEleById("comment_" +  cd_no);
	var commentPwObj   = getEleById("comment_pw_" +  cd_no);
	
	
	
	var dataParam = {"cd_no" : cd_no
					,"comment" : commmentObj.value
					,"passwd"  : commentPwObj.value
					};
		
      jQuery.ajax({
          type: "POST"
          ,async: false
          ,url: "<%=rootUrl%>/content/contentInsertComment.action"
          ,dataType: "json"			// 응답의 결과로 반환되는 데이터의 종류를 식별하는 키워드
          ,data: dataParam
          //,contentType: "application/json; charset=utf-8"
          ,success: function(rs) 
          {
        	  showComment(cd_no);
              
          }
          ,error: function(data) { alert('서버와의 통신이 실패했습니다.'); retvalue=false; }
          ,complete: function() { }
      });

	return retvalue;
	 
}

/*comment 수정 popUp 호출 또는 삭제처리*/
function editCommentPop(editType,cdc_no,comment,mt_no,cd_no)
{
	// comment 삭제요청인데 회원이라면  팝업 호출 필요없다.
	if (editType == "D" && mt_no != "-1") 
	{
		editComment(editType,cdc_no,"","",cd_no);
		return;
	}
	
	var param = "?editType=" + editType
				+"&cdc_no=" + cdc_no
				+"&mt_no=" + mt_no
				+"&comment=" + comment
				+"&cd_no=" + cd_no
				
				;
	
	var winObj = launchCenter("/jsp/content/commentEditPop.jsp" + param, "commentEditPop", 600,120);
}

/*comment 수정 */
function editComment(editType,cdc_no,comment,passwd,cd_no)
{

	var retvalue=false;
	
	var dataParam = {"cdc_no" : cdc_no
					,"comment" : comment
					,"passwd"  : passwd
					,"editType" : editType
					,"cd_no" : cd_no
					};
	
      jQuery.ajax({
          type: "POST"
          ,async: false
          ,url: "<%=rootUrl%>/content/contentEditComment.action"
          ,dataType: "json"			// 응답의 결과로 반환되는 데이터의 종류를 식별하는 키워드
          ,data: dataParam
          //,contentType: "application/json; charset=utf-8"
          ,success: function(rs) 
          {
        	  if (rs.update_cnt != "1" && editType == "E") alert("수정 불가능  합니다.\n입력한 비밀번호 또는 자신의 글인지 확인해주세요.");
        	  if (rs.update_cnt != "1" && editType == "D") alert("삭제 불가능 합니다.\n입력한 비밀번호 또는 자신의 글인지 확인해주세요.");
        	  
        	  showComment(cd_no);
        	  
              
          }
          ,error: function(data) { alert('서버와의 통신이 실패했습니다.'); retvalue=false; }
          ,complete: function() { }
      });

	return retvalue;
	 

}


</script>


				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>


