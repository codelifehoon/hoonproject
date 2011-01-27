<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<META http-equiv="page-enter" content="revealtrans (duration=3, transition=23)"> 
	<META http-equiv="page-exit" content="revealtrans (duration=3, transition=23)">
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script> 
	<script language="javascript">
	var req;
	
	function getForm()
	{
		return document.fileForm;
	}
	function fileUpalodStatFunction()
	{
		
		try
		{
		mapParam = {fileName:getForm().txtFile.value};
		
		DwrContentAction.fileUplaodStst(mapParam,processStateChangeCallBack);

		}
		catch(e){ alert(e.message); }

		
		
	}

	function processStateChangeCallBack(resultList)
	{

	 
		
		var resultStr ="";
		//for (var i=0;i<  resultList.length ;i++)
		//	{
		//		resultStr += resultList[i].mem_nm + ",";
		//	}
		
		if ( resultList.result_code == "00") 
		{

			var bytesRead 			= resultList.bytesRead;
			var contentLength 		= resultList.contentLength;
			var finished 			= resultList.finished;
			var percent_complete 	= resultList.percent_complete;
			
			
			var isNotFinished	= resultList.finished;
			var myBytesRead 	= parseInt(resultList.bytesRead);
			var myContentLength = parseInt(resultList.contentLength);
			var myPercent 		= parseInt(resultList.percent_complete);

			// 아직 업로드가 시작이 안되었을때
			if ((isNotFinished == "no") && (myPercent == 0))
			{
				document.getElementById("progressView").innerHTML = "올리기 준비중.";
				window.setTimeout("fileUpalodStatFunction()", 500);
				
			}
			else 
			{
				document.getElementById("progressView").innerHTML =  bytesRead + "/" + contentLength;
				
				fileUpalodStatFunction();
				
				if ( parseInt(bytesRead) != parseInt(contentLength)) window.setTimeout("fileUpalodStatFunction()", 500);
				else	document.getElementById("progressView").innerHTML = "올리기 완료."; 
				
				
			}
		}
		else{
				alert("파일 업로드 상태를 알수 없습니다."); 
			}

	}
	
	</script>
</head>
 
<body>
	<iframe name="fileFormFrame" height="0" width="0" frameborder="0" scrolling="yes"></iframe>              
	<form name="fileForm" enctype="multipart/form-data" method="post" target="fileFormFrame" action="/servlet/FileUploadServlet" onsubmit="fileUpalodStatFunction()">
	    <input type="file" name="txtFile" id="txtFile" /><br />
	    <input type="submit" id="submitID" name="submit" value="Upload" />
	</form>
	
	<!-- Add hidden DIVs for updating and the progress bar (just a table) below the form -->
	<div id="progressView" >
		파일올리기전
	</div>
	
</body>
