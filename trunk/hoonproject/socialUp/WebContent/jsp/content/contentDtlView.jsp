<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");
	ContentDtlTblDTO 	contentDtl	= contentTitle.getContentDtlList().get(0);
	
	int choicePageNum	= NumUtil.toInt(CmnUtil.nvl(request.getParameter("choicePageNum"),"1")); // 현재 page 번호
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
	
	function ContentDtlEdit(newFlag,cd_no)
	{
		// 수정일때
		if (newFlag == "U")
		{
			var url = "<%=rootUrl%>/content/contentDtlEditForm.action?cd_no="+ cd_no;
			window.location.href  = url;
			
		}
		// 삭제일때
		else if(newFlag == "D")
		{
			if (!confirm("삭제하시겠습니까?")) return ;
				
			var url = "<%=rootUrl%>/content/contentDtlEditFinish.action?cd_no=" + cd_no
									+"&newFlag=D";
			window.location.href  = url;
		}
		
	}
	
	// 댓글갱신
	function reloadComment(flag)
	{
		 
		 var contentDtlComment = "";

		 // 댓글 전체를 다시 갱신할때
		 if (flag == "ALL")
		{
			 getForm().cdc_no_gt.value = "0";
			 getEleById('commentList').innerHTML = "";
		}
			 
		 contentDtlComment = {cd_no:'<%=contentDtl.getCd_no() %>'
				 			,cdc_no_gt: getForm().cdc_no_gt.value
							};

		 DwrContentAction.selectContentDtlCommentList(contentDtlComment,selectContentDtlCommentListCallBack);
 		
	}
	
	// 댓글달기
	function insertCommentAction()
	{
		var errMsg = "";
		var contentDtlComment = "";
		var error_code = 0;
		
		if (getForm().comment.value == "" )	error_code =-1;
		
		if (error_code  < 0 )
		{
			switch (error_code)
			{
				case -1 : alert("댓글을 입력 해주세요.");
						  getForm().comment.focus();
						  return;
			}
		}
		
		
		//if ( errMsg != "" )
		//{
		//	document.getElementById("memRegMsg").innerHTML = errMsg;
		//	return;
		//}
			
		
		// 댓글을 수정하기 위해서 선택한것을 다시 등록할떄
		if (getForm().newFlag.value == "U")
		{
			
			contentDtlComment = {cd_no:'<%=contentDtl.getCd_no() %>'
								, cdc_no  : getForm().cdc_no.value
								, comment : getForm().comment.value
								, newFlag : getForm().newFlag.value
				};
			
			// 수정에 필요해서 설정된값 삭제
			getForm().cdc_no.value = "";
			getForm().comment.value = "";
			getForm().newFlag.value = "";
			
			DwrContentAction.updateContentDtlComment(contentDtlComment,updateContentDtlCommentCallBack);
			
		}
		else	// 댓글 신규 등록일때
		{
			contentDtlComment = {cd_no:'<%=contentDtl.getCd_no() %>'
								, comment:getForm().comment.value
								};

			DwrContentAction.insertContentDtlComment(contentDtlComment,insertContentDtlCommentCallBack);
		}
		
			
	}
	
	// 신규댓글등록 CallBack
	function insertContentDtlCommentCallBack(resultList)
	{
	 
		if ( resultList.result_code == "00") 
		{
			getForm().comment.value = "";
			reloadComment();
		}
		else
		{
			alert(resultList.result_msg); 
		}
		
	}
	
	// 댓글조회 callBack
	function selectContentDtlCommentListCallBack(resultList)
	{
	 
		if ( resultList.result_code == "00") 
		{
			// 변경된 댓글이 존재하는 확인하고 갱신
			
			
			
			var resultStr = "" ;
			var cdc_no = "0";
		
			for (var i=0;i< resultList.retValList.commentList.length ;i++)
				{
					var dtlComment = resultList.retValList.commentList[i];
					
					resultStr +=  "<div style='float:left;width:10%'>"+ dtlComment.memTblDTO.mem_nm  + "</div>"
									+ "<div style='float:left;width:80%' id='comm_" + dtlComment.cdc_no +  "'>" +  dtlComment.comment + "</div>";

					
					if ("<%=authInfo.getMt_no()%>"  ==  dtlComment.mt_no )
					{
						resultStr += "<div style='float:left;width:10%'>" 
									+"<a href=\"javascript:commentEdit('U',"+dtlComment.cdc_no+")\">[수정]</a>"
									+"<a href=\"javascript:commentEdit('D',"+dtlComment.cdc_no+")\">[삭제]</a>"
									+"</div>";  
					}
					else
					{
						resultStr += "<div style='float:left;width:10%'></div>";
					}
				
					
					resultStr += "<div style='clear:both;''></div>";
					
					if (parseInt(cdc_no) < parseInt(dtlComment.cdc_no) ) cdc_no = dtlComment.cdc_no; 
				}
			
			// 조회건중 가장 큰 댓글번호를 별도로 기억한다.(새로운 큰값이 있다면 cdc_no값이 0이 아니다. )
			if (cdc_no !=  "0") getForm().cdc_no_gt.value = cdc_no;

			getEleById('commentList').innerHTML = resultStr + getEleById('commentList').innerHTML; 
			
			/*
			var resultStr = "" ;
			var retValList = eval(resultList.retValList);		// json 표현식 배열로 생성
			
			for (var i=0;i< retValList.length ;i++)
				{
					var retVal = retValList[i];
					resultStr += retVal.cdc_no + ",";
				}
			alert(resultStr);
			*/
		}
		else
		{ 
			alert(resultList.result_msg); 
		}
		
	}
	
	function commentEdit(newFlag,edit_cdc_no)
	{
		if (newFlag == "D")
		{
			contentDtlComment = {cd_no:'<%=contentDtl.getCd_no() %>'
								, cdc_no: edit_cdc_no
								, newFlag: "D"
				};

			DwrContentAction.updateContentDtlComment(contentDtlComment,updateContentDtlCommentCallBack);
		}
		
		if (newFlag == "U")
		{
			// 댓글입력창에서 수정할수 있도록 환경설정
			var commentObj = getEleById("comm_"+ edit_cdc_no)
			
			getForm().comment.value = commentObj.innerHTML;
			getForm().cdc_no.value 	= edit_cdc_no;
			getForm().newFlag.value = "U";
		}
		
	}
	
	function updateContentDtlCommentCallBack(resultList)
	{
		if ( resultList.result_code == "00") 
		{
			reloadComment("ALL");
			
		}
		else
		{	alert("댓글수정오류"); }
		
	}
	
	
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

<form name='defForm'>
	<input type='hidden' name='cdc_no_gt' value='0'/>	<!-- 해당 cdc_no보다큰값을 조회 해야됨 -->
	<input type='hidden' name='cd_no' value='<%=contentDtl.getCd_no() %>'/>
	<input type='hidden' name='newFlag' value=''/>		<!--  댓글 수정시 사용 -->
	<input type='hidden' name='cdc_no' value=''/>		<!--  댓글 수정시 사용 -->

			
			<div class="main_content">

				<div class="sd_main">

					<h2><%=contentTitle.getTitle_name() %></h2>
						<h3 align='right'>
						<%if (	"00".equals(contentDtl.getSource_kind())
								&& contentDtl.getMt_no().equals(authInfo.getMt_no()) 
							 ) // 자체생성 컨텐츠 이어야 하고. 자신의글 이어야 수정가능한다.
								{
						%>
										<a href='javascript:ContentDtlEdit("U",<%=contentDtl.getCd_no() %>);'>[수정]</a>
										  | <a href='javascript:ContentDtlEdit("D",<%=contentDtl.getCd_no() %>);'>[삭제]</a>
						<%} %>
						</h3>
	 					     <p><%=contentDtl.getContent_title() %><a href='<%=contentDtl.getContent_title_link() %>'>[원문보기]</a></p>
	 					     <p><%=contentDtl.getContent_desc() %></p>
	 						<p class="date"> posted by <%=contentDtl.getAuthor_nm() %> | read[<%=contentDtl.getHit_cnt()%>] | comment[<%=contentDtl.getCdc_cnt()%>]   </p>
	 						<br />
 					

						<table width='100%' >
							<tr >
								<td width=10%>댓글</td>
								<td><textarea name="comment" id="comment" style="width:600px; height:30px"></textarea></td>
								<td width=20%>
								<!--  회원만 댓글등록 가능 -->
								<%if (authInfo.isAuth()){ %> 	
									<a href="javascript:insertCommentAction();">[댓글등록]</a> 
								<% } %>
								</td>
							</tr>
						</table>
				 		<div id="commentList">
							<div style="float:left;width:10%"></div>
							<div style="float:left;width:80%"></div>
							<div style="float:left;width:10%"></div>
							<div style="clear:both;"></div>
						</div>
 					

 					<p class="date">
 						<a href="<%=rootUrl%>/content/contentDtlList.action?tt_no=<%=contentTitle.getTt_no() %>&choicePageNum=<%=choicePageNum %>">[목록으로]</a>
 					</p>
				</div>
			</div>
</form>
				
<%@ include file="/jsp/common/footer.jsp" %>
<script language='javascript'>
reloadComment("");
//setTimeout("reloadComment()", 3000);		특정시간후 1회실행
// 주기적으로 실행 (차후 주석해제)
//setInterval("reloadComment()", 3000);
</script>

