<%@page import="com.zebra.common.BaseConstants"%>
<%@page import="com.zebra.common.CommonCodeHandler"%>
<%@page import="com.zebra.business.craw.domain.CrawConfigBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.zebra.common.util.CmnUtil"%>
<%@page import="com.zebra.common.util.DateTime"%>
<%@page import="com.zebra.business.analysis.domain.GoodsPriceTrendBO"%>
<%@page import="com.zebra.business.craw.domain.WebPageInfoBO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>

<%
	String priceCode = "";
	String priceCodeVal = "";
	HashMap<String,Object> goodsDetailMap  = (HashMap<String,Object>)request.getAttribute("goodsDetailMap");
	CmnUtil cmnutil = new CmnUtil();

	WebPageInfoBO 			webPageInfoBO 		= (WebPageInfoBO)goodsDetailMap.get("webPageInfoBO");
	CrawConfigBO			crawConfigBO		= (CrawConfigBO)goodsDetailMap.get("crawConfigBO");
	List<WebPageInfoBO> 	priceChangeList 	= (List<WebPageInfoBO>)goodsDetailMap.get("priceChangeList");
	List<GoodsPriceTrendBO> 	priceTrendList 		= (List<GoodsPriceTrendBO>)goodsDetailMap.get("priceTrendList");
%>
<script language='javascript'>

//$(function() {})
  

</script>

<div id="main">

<div class="content maxWidth1024">

<div class="pure-g">
    <div class="l-box pure-u-2-5" align='center'>
	<!-- 이미지 -->

 		<!-- ##쇼킹딜## 상품상태값 노출(매진임박>오늘마감>오늘오픈 ) 2013.12.19 이태강  --> 
		<!-- // ##쇼킹딜## --> 
		
		 <img src="${goodsDetailMap.webPageInfoBO.goodsImg}" alt=""  style="width: auto; height: auto;"/>
		 
	</div>  
    <div class="l-box pure-u-2-5">
		<div class="pure-1">
		<c:if test="${goodsDetailMap.webPageInfoBO.cate1 != '' }">
			<h3> ${goodsDetailMap.webPageInfoBO.cate1} <b>&gt;</b>  ${goodsDetailMap.webPageInfoBO.cate2} <b>&gt;</b> ${goodsDetailMap.webPageInfoBO.cate3} </h3> 
		</c:if>
		
		</div>
		<div class="pure-1">
			<img class="pure-img" src="${goodsDetailMap.crawConfigBO.logoURL}" height="30"><b><span id='priceTrendValue'></span></b><br>
			 <h3> ${goodsDetailMap.webPageInfoBO.goodsNm}</h3>
		</div>
		<div class="pure-1"></div>
		
	</div>
    <div class="l-box pure-u-1-5" >
			<a class="pure-button" href="${goodsDetailMap.webPageInfoBO.goodsUrl} " target='_new'><i class="fa fa-shopping-cart fa-lg"></i> 구매하기 </a>
	</p>
	</div>
</div>
<div class="pure-g">

</div>

<div class="pure-g" >
    <div class="pure-u-12-24">
    <p>가격동향 </p>
    <table class="pure-table pure-table-horizontal">
	    <thead>
	        <tr>
	            <th class="highlight">구분</th>
	            <th class="highlight">가격</th>
	            <th class="highlight">일자</th>
	        </tr>
	    </thead>
	
	    <tbody>
	    	<tr>
	            <td>현재가</td>
	            <td><%=cmnutil.setComma(webPageInfoBO.getGoodsPrice())%></td>
	            <td><%=DateTime.format(webPageInfoBO.getUpdateDt())%></td>
	        </tr>
	    	<%
	    		for (GoodsPriceTrendBO goodsPriceTrend :priceTrendList) {
	    		          
	    		          if ("".equals(priceCode) && webPageInfoBO.getGoodsPrice().equals(goodsPriceTrend.getGoodsPrice()) )
	    		          {
	    		          	priceCode = goodsPriceTrend.getPriceCode(); 
	    		          }
	    		          
	    		        if ( "02".equals(goodsPriceTrend.getPriceCode())) {
	    	%>
	           	<tr>
		            <td>최저가</td>
		            <td><%=	cmnutil.setComma(goodsPriceTrend.getGoodsPrice()) %></td>
		            <td><%= DateTime.format(goodsPriceTrend.getCreateDt()) %></td>
	            </tr>
	         <%} %>	    
	         <%if ( "03".equals(goodsPriceTrend.getPriceCode())) {%>
	           	<tr>
		            <td>최고가</td>
		            <td><%=	cmnutil.setComma(goodsPriceTrend.getGoodsPrice()) %></td>
		            <td><%= DateTime.format(goodsPriceTrend.getCreateDt()) %></td>
	            </tr>
	         <%} %>	 
	         <%if ( "04".equals(goodsPriceTrend.getPriceCode())) {%>
	           	<tr>
		            <td>평균가</td>
		            <td><%=	cmnutil.setComma(goodsPriceTrend.getGoodsPrice()) %></td>
		            <td> - </td>
	            </tr>
	         <%} %>	     
	        	
			<%} %>
	    </tbody>
	</table>

    </div>
    <div class="pure-u-12-24">
    <p>판매가 변경(최근 5건)</p>
    <table class="pure-table pure-table-horizontal">
	    <thead>
	        <tr>
	            <th>상품명</th>
	            <th>변경일</th>
	            <th>가격</th>
	        </tr>
	    </thead>
	
	    <tbody>
	    		<%	for(WebPageInfoBO pageInfo : priceChangeList ) {%>
	    		
	    		<tr>
	    	
		            <td><%= pageInfo.getGoodsNm() %></td>
		            <td><%= DateTime.format(pageInfo.getCreateDt()) %></td>
		            <td><%= cmnutil.setComma(pageInfo.getGoodsPrice()) %></td>
		        </tr>
	     		<%} %>
	        
	
	    </tbody>
	</table>
	
	
    </div>
</div>

<div class="pure-g" >
    <div class="pure-u-1"><p>~detail good~scost flow~</p></div>

</div>


</div>
</div>
 <%
	if (!"".equals(priceCode))
	 {
		priceCodeVal = CommonCodeHandler.selectCommonCode(BaseConstants.PRICE_CODE,  priceCode).getVal1();
	 }
 %>

<script language="javascript">

	$("#priceTrendValue").html("<%=priceCodeVal%>") ;

</script>


