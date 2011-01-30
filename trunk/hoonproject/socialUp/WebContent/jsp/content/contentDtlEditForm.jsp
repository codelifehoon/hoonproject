<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="socialUp.service.content.dto.ContentSourceTblDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
		ContentTitleTblDTO contentTitle = (ContentTitleTblDTO)request.getAttribute("contentTitle");
		ContentDtlTblDTO   contentDtl	= null;
		String tt_no 					= CmnUtil.nvl(request.getParameter("tt_no"));		
		String newFlag					= "N";			// 신규인지 수정인지   N: 신규  U: 업데이트 
		
		// 글 수정일때 null 이 아니다.
		if ( contentTitle != null)
		{
			newFlag = "U";
			contentDtl	= contentTitle.getContentDtlList().get(0);
		}
		
		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script type="text/javascript"  src="<%=rootUrl%>/js/se/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>

<script language='javascript'>
	
function getForm()
{
	return document.defForm;
	
}

function contentDtlEditProc()
{
		oEditors[0].exec("UPDATE_IR_FIELD", []);
	
		// 에디터의 내용에 대한 값 검증은 이곳에서 textarea 필드인 ir1의 값을 이용해서 처리하면 됩니다.

	
		// 이 라인은 현재 사용 중인 폼에 따라 달라질수 있습니다.

		try{
		var error_code =  0;

		if (getForm().content_title.value 	== "") 				error_code = -1;
		if (getForm().ir1.value 			== "") 				error_code = -2;
		if (getForm().categories.value 		== "") 				error_code = -3;
		
		if (error_code  < 0 )
		{
			switch (error_code)
			{
				case -1 : alert("글 제목을 입력 해주세요.");
						  getForm().content_title.focus();
						  return;
				
				case -2 : alert("글 내용을 입력 해주세요.");
						  getForm().content_title.focus();
						  return;
				  
				
				case -3 : alert("관련 테그를 입력 해주세요.");
				  		  getForm().categories.focus();
				  return;
			}
			
		}
		
		getForm().submit();
	}catch(e){ alert(e.message); }

}
	function insertImg(imgUrl)
	{
		
		try
		{
			imgUrl = "<img src='" + imgUrl + "'>";
			oEditors.getById["ir1"].exec("PASTE_HTML", [imgUrl]);
			
		} catch(e){ alert(e.message); }
		
	}
	function imageListRealod(startNum)
	{

		try
		{

		// 이미지 페이징 처리시 시작 번호
		mapParam = {startRowNum:startNum};
		
		DwrContentAction.selectImgList(mapParam,imageListRealodCallBack);

		}
		catch(e){ alert(e.message); }
		
	}

	// 이미지 리스트 조회결과 출력
	function imageListRealodCallBack(resultList)
	{

		var resultStr ="";
		var domain = "http://goreee.com";
		
	
		
		try
		{
			if ( resultList.result_code == "00") 
			{
				
				
				if ( parseInt(resultList.allRowNum) > 0 )
				{
					var startRowNum = parseInt(resultList.startRowNum);
					var allRowNum 	= parseInt(resultList.allRowNum);
					
					// 사진 조회 시작번호가 0보다 크면 이전 사진이 존재한다는것임
					if (startRowNum > 0 )
					{
						var rownum = 0;
						if (startRowNum - 10 < 0 ) 	rownum = 0;
						else						rownum = startRowNum - 10;
						
						resultStr += "<input type='button' onClick='javascript:imageListRealod( " + rownum + " )' value='최근10개'>";
					}
					
					for (var i=0;i< resultList.retValList.uploadFilesList.length ;i++)
					{
						var uploadFiles = resultList.retValList.uploadFilesList[i];
						
						resultStr += "<a href=\"javascript:insertImg('" + domain +  uploadFiles.file_path +"/" + uploadFiles.file_name + "')\">";
						resultStr += "<img src='" + uploadFiles.file_path +"/thumb/" + uploadFiles.file_name + "' >&nbsp;";
						resultStr += "</a>";
		
					}
					
					// 사진 조회 시작번호 + 10보다 전체 사진 수량이 크다면 이전껄
					if (startRowNum + 10 <  allRowNum )
					{
						var rownum = 0;
						rownum = startRowNum + 10 ;
						
						resultStr += "<input type='button' onClick='javascript:imageListRealod( " + rownum + " )' value='이전10개'>";
					}
					
					
				}
				
				
				getEleById("imgListArea").innerHTML = resultStr;
				
			}
			else{
					alert("이미지 목록 조회시 오류발생."); 
				}
		} catch (e)
		{
			alert(e.message);
		}
	
	}
</script>

</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>
			
			<div class="main_content">
			
			
			<form name="defForm" method="post" action="<%=rootUrl%>/content/contentDtlEditFinish.action">
			<input type="hidden" name="tt_no" value="<%=tt_no %>"/>
			<input type="hidden" name="newFlag" value="<%=newFlag %>"/>
			
<%if ("N".equals(newFlag)) 
{%>
			<p>제목:<input type='text' name='content_title' size='115' /></p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<textarea name="ir1" id="ir1" style="width:1000px; height:300px"></textarea></p>
			<p>TAG :<input type='text' name='categories' size='80' /></p>
			<p>TAG값이 여러개 있을경우 , 로 분리 해주세요(예: 꽃,장비)</p>
			
	
<%} else if ("U".equals(newFlag)) {%>
			<input type="hidden" name="cd_no" value="<%=contentDtl.getCd_no()%>"/>
			
			제목:<input type='text' name='content_title' value='<%=contentDtl.getContent_title() %>'  size='115' /><br></br>
			<textarea name="ir1" id="ir1" style="width:1000px; height:300px"><%=contentDtl.getContent_desc() %></textarea>
			TAG :<input type='text' name='categories' value='<%=contentDtl.getCategories() %>'/></br>
			<p>TAG값이 여러개 있을경우 , 로 분리 해주세요(예: 꽃,장비)</p>
	
			
<%} %>
</form>
		 
		<div id="imgListArea">
		</div>
		<iframe name="fileUploadFrame" height="100px" width="1000px" frameborder="0" scrolling="yes" src="<%=rootUrl%>/jsp/content/fileUploadForm.jsp"></iframe>

<%if ("N".equals(newFlag)) {%>
<p align='center'><a href='javascript:contentDtlEditProc()'>******* 등록 *******</a></p>
<%} else if ("U".equals(newFlag)) {%>
<p align='center'><a href='javascript:contentDtlEditProc()'>******* 수정 *******</a></p>
<%} %>


			
			 
			</div>

				
<%@ include file="/jsp/common/footer.jsp" %>

<script language='javascript'>
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "<%=rootUrl%>/js/se/SEditorSkin.html",
	fCreator: "createSEditorInIFrame"
		,		bUseBlocker : "false" 
});

// 이미지 리스트 초기화
imageListRealod("0");
//,		bUseBlocker : "false"		
</script>
