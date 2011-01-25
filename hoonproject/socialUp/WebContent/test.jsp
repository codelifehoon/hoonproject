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
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrMemAction.js"></script> 
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
		
		mapParam = {fileName:getForm.fileName.value};
		
		DwrMemAction.fileUplaodStst(mapParam,processStateChangeCallBack);
		
		alert("상태변경");
		
		
	}

	function processStateChangeCallBack()
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
				document.getElementById("initializing").style.visibility = "visible";

				// Sleep then call the function again
				window.setTimeout("fileUpalodStatFunction();", 100);
			}
			else 
			{
				document.getElementById("initializing").style.visibility = "hidden";
				document.getElementById("progressBarTable").style.visibility = "visible";
				document.getElementById("percentCompleteTable").style.visibility = "visible";
				document.getElementById("bytesRead").style.visibility = "visible";

				myBytesRead = myBytesRead.firstChild.data;
				myContentLength = myContentLength.firstChild.data;

				if (myPercent != 0) 	// 업로드가 시작 되었을때
				{
					myPercent = myPercent.firstChild.data;
		
					document.getElementById("progressBar").style.width = myPercent + "%";
					document.getElementById("bytesRead").innerHTML = myBytesRead + " of " + 
						myContentLength + " bytes read";
					document.getElementById("percentComplete").innerHTML = myPercent + "%";
	
					// Sleep then call the function again
					window.setTimeout("fileUpalodStatFunction();", 100);
				}
				else
				{
					document.getElementById("bytesRead").style.visibility = "hidden";
					document.getElementById("progressBar").style.width = "100%";
					document.getElementById("percentComplete").innerHTML = "Done!";
				}
			}
		}
		else{
				alert("파일 업로드 상태를 알수 없습니다."); 
			}

	}
	
	</script>
</head>
 
<body>
	<iframe id="fileFormFrame" name="uploadFrame" height="0" width="0" frameborder="0" scrolling="yes"></iframe>              
	<form id="fileForm" enctype="multipart/form-data" method="post" target="fileFormFrame" action="/servlet/FileUploadServlet" onsubmit="fileUpalodStatFunction()">
	    <input type="file" name="txtFile" id="txtFile" /><br />
	    <input type="submit" id="submitID" name="submit" value="Upload" />
	</form>
	
	<!-- Add hidden DIVs for updating and the progress bar (just a table) below the form -->
	<div id="initializing" style="visibility: hidden; position: absolute; top: 100px;">
		<table width="100%" style="border: 1px; background-color: black;">
			<tr>
				<td>
					<table width="100%" style="border: 1px; background-color: black; color: white;">
						<tr>
							<td align="center">
								<b>Initializing Upload...</b>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="progressBarTable" style="visibility: hidden; position: absolute; top: 100px;">
		<table width="100%" style="border: 1px; background-color: black; color: white;">
			<tr>
				<td>
					<table id="progressBar" width="0px" 
						style="border: 1px; width: 0px; background-color: blue;">
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" style="background-color: white; color: black;">
			<tr>
				<td align="center" nowrap="nowrap">
					<span id="bytesRead" style="font-weight: bold;">&nbsp;</span>
				</td>
			</tr>
		</table>		
	</div>

	<div id="percentCompleteTable" align="center"
		style="visibility: hidden; position: absolute; top: 100px;">
		<table width="100%" style="border: 1px;">
			<tr>
				<td>
					<table width="100%" style="border: 1px;">
						<tr>
							<td align="center" nowrap="nowrap">
 								<span id="percentComplete" style="color: white; font-weight: bold;">&nbsp;</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
   	</div>
</body>
