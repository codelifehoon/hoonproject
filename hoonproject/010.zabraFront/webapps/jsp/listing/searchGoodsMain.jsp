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
	    	<input id="goodsNm" name='goodsNm' type='text' style='width:100%'>
		</div>
	    <div class="l-box pure-u-1-5" align='left'>
			<button type="button" class="pure-button pure-button-primary">Search</button>	
		</div>
	</div>
	<div class="l-box"></div>
	
	<div class="pure-g" id="searchResult">
		
	</div>

</div>
</div>




<script language='javascript'>

function loadGoodsSearch(var divID,var startSeq,var RowCnt,var searchWord)
{
	var searchURL ="/listing/searchGoodsAjax.do?RowCnt=" + RowCnt
						"&startSeq=" +startSeq
						"&searchWord=" +searchWord
						;
	

	jQuery(divID).load(searchURL).fadeIn(100).delay(500);
}

loadGoodsSearch("searchResult","0" ,"50","카테고리");
</script>


