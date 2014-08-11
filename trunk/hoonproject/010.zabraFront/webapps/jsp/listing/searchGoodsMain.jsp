<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.zebra.common.util.CmnUtil"%>

<%@ include file="/jsp/common/pageCommon.jsp" %>

<%
	CmnUtil cmnutil = new CmnUtil();


%>


<script language='javascript'>

//$(function() {})
  

</script>

<div id="main">

<div class="content maxWidth1024">

	<div class="pure-g">
	    <div class="l-box pure-u-1-5" align='right'>
			<select id="cate1" name="cate1">
		            <option>카테고리</option>
		            <option>A</option>
		            <option>B</option>
		    </select>
		        
		</div>  
	    <div class="l-box pure-u-3-5">
	    	<input id="searchWord" name='searchWord' type='text' style='width:100%' onkeypress="if(event.keyCode==13) {doSearch(); return false;}">
		</div>
	    <div class="l-box pure-u-1-5" align='left'>
			<button type="button" class="pure-button pure-button-primary" onClick='javascript:doSearch()'>Search</button>	
		</div>
	</div>
	<div class="l-box"></div>
	
	<div class="pure-g" id="searchResulBox" >
		<div id="searchResulEnd" ></div>
	</div>

</div>
</div>




<script language='javascript'>


var gIdx = 1;
var gPage = 1;
var gStartSeq = 0;
var gLodingFlag = false;
var gNextFlag = true;
var prevKeyWord = "";

jQuery(document).ready(function () 
{
	jQuery(window).scroll(function() 
	{
		if (jQuery(window).scrollTop() == jQuery(document).height() - jQuery(window).height()) 
		{
			if (!gNextFlag) return;
			
			setTimeout(function()
			{
				appendSearchList();
			}, 1000);
		}
	});
});


function loadGoodsSearch(divID,localStartSeq,rowCnt,searchWord,page)
{
	
	var searchURL ="http://localhost/listing/ajax/searchGoods.do?rowCnt=" + rowCnt
						+ "&startSeq=" +localStartSeq
						+ "&searchWord=" +searchWord
						+ "&page=" + page;

	console.log(searchURL);
	$(divID).load(searchURL).fadeIn(1000).delay(5000);
	
}

function changeGlobalVar(localPage,localStartSeq,nextFlag,localLodingFlag)
{
	console.log("localPage:" + localPage + ",localStartSeq:" + localStartSeq);
	gPage = localPage;
	gStartSeq = localStartSeq;
	gNextFlag  = nextFlag;
	gLodingFlag = localLodingFlag;
}


function doSearch()
{
	gIdx = 1;
	gPage = 1;
	gStartSeq = 0;
	
	if (prevKeyWord != $("#searchWord").val())
	{
		$('#searchResulBox').html('<div id="searchResulEnd" ></div>');
		appendSearchList();
		prevKeyWord = $("#searchWord").val();
	}
	else
	{
		console.log("중복 keyword.");
	}
	
}

function appendSearchList()
{
	if (!gLodingFlag)
	{
		var newDiv=document.createElement('div'); 
		var morediv = 'searchResul'+gIdx;
		var rowCnt = 50;
		
		newDiv.setAttribute('id',morediv); 
		newDiv.innerHTML= '!!!!!!!!!!!!!!!!!!!!!로딩중입니다...';
		
		jQuery(newDiv).insertBefore('#searchResulEnd'); 
		loadGoodsSearch('#' + morediv,gStartSeq ,rowCnt,encodeURIComponent($("#searchWord").val()),gPage);
		
		gIdx++;
		gLodingFlag = true;
	}
}

</script>




