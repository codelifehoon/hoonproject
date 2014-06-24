<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.zebra.business.craw.domain.WebPageInfoBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%

	List<WebPageInfoBO> webPageInfoList = (List<WebPageInfoBO>)request.getAttribute("webPageInfoList");
	HashMap<String,Object> paramMap		= (HashMap<String,Object>)request.getAttribute("paramMap");
	
%>
<script language='javascript'>

//$(function() {});
 </script>
      <aside>
                
                    <fieldset>
					<%for (WebPageInfoBO bo : webPageInfoList ) { %>
			        <h3 class="content-subhead" id="yui_3_14_1_1_1397782431674_8">
                   		<i class="fa fa-dot-circle-o"></i>상품정보<%=bo.getGoodsNo() %>
                	</h3>
				        <label for="상품명">상품명</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getGoodsNm() %></p>
	          			<label for="상품판매가">상품판매가</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getGoodsPrice() %></p>
			            <label for="대표이미지">대표이미지</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getGoodsImg() %></p>
			            <label for="할인율">할인율</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getGoodsDisc() %></p>
			            <label for="대분류">대분류</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getCate1() %></p>
			            <label for="중분류">중분류</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getCate2() %></p>
			            <label for="소분류">소분류</label><p id="yui_3_14_1_1_1398665339210_17"><%=bo.getCate3() %></p>
			        <%} %>
                    </fieldset>
                
                </aside>
        
 
