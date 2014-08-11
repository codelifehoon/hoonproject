<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.zebra.common.util.NumUtil"%>
<%@page import="com.zebra.common.BaseConstants"%>
<%@page import="com.zebra.business.analysis.domain.SearchCombBO"%>
<%@page import="com.zebra.common.util.CmnUtil"%>
<%@page import="java.util.List"%>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%
	CmnUtil cmnutil = new CmnUtil();
	List<SearchCombBO>  searchResultList  	= (List<SearchCombBO>)request.getAttribute("searchResultList");
	HashMap<String, Object> paramMap		= (HashMap<String, Object>)request.getAttribute("paramMap");
	 
	String 	pageStr		= (String)paramMap.get("page");
	long maxSeq			= NumUtil.toLong((String)paramMap.get("startSeq"));
	int	pageNo			= NumUtil.toInt(pageStr);
	
	if (searchResultList.size() > 0)
	{
		pageNo++;
%>

		<div id='list' class="pure-u-1"><%=pageNo-1 %></div>
		<%for (SearchCombBO searchResult : searchResultList ) {
		
			if ( NumUtil.toLong(searchResult.getWebPageInfoBO().getPageInfoListSeq()) > maxSeq ) {
					maxSeq = NumUtil.toLong(searchResult.getWebPageInfoBO().getPageInfoListSeq());
				}
			
 		%>
		<div id='list' class="pure-u-1">
			
			<div class="pure-u-2-24" style="width: 100px; height: 100px; overflow: hidden">
								<a href="<%=searchResult.getWebPageInfoBO().getGoodsUrl() %>">
										<img src='<%=searchResult.getWebPageInfoBO().getGoodsImg() %>' style="width: auto; height: 100px;">
									</a>
			</div>
			<div class="pure-u-14-24">
				
					<img src='<%=searchResult.getCrawConfigBO().getLogoURL() %>' style="width: 60px; height: auto; ">
				
				<a href='/goods/goodsDetail.do?pageInfoListSeq=<%=searchResult.getWebPageInfoBO().getPageInfoListSeq()%>' target="_new"> <%=searchResult.getWebPageInfoBO().getGoodsNm() %></a>
				<%= searchResult.getWebPageInfoBO().getGoodsUrl() %>
			</div>
			<div class="pure-u-2-24"><%=cmnutil.setComma(searchResult.getWebPageInfoBO().getGoodsPrice()) %></div>
			<div class="pure-u-2-24"><%=cmnutil.setComma(searchResult.getGoodsPriceTrendArrBO().getGoodsPriceTrendByPriceCode(BaseConstants.PRICE_CODE_LOW).getGoodsPrice()) %></div>
			<div class="pure-u-2-24"><%=cmnutil.setComma(searchResult.getGoodsPriceTrendArrBO().getGoodsPriceTrendByPriceCode(BaseConstants.PRICE_CODE_MAX).getGoodsPrice()) %></div>
		</div>	
		<%} %>			
		
<script language='javascript'>
	changeGlobalVar(<%=pageNo%>,<%=maxSeq%>,true);
</script>
<% } else { %>

		<div id='list' class="pure-u-1">더이상 자료가 없습니다.</div>
	<script language='javascript'>
		changeGlobalVar(<%=pageNo%>,<%=maxSeq%>,false,false);
	</script>

<%}%>