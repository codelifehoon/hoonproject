<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%

//	List<WebPageInfoBO> webPageInfoList = (List<WebPageInfoBO>)request.getAttribute("webPageInfoList");
	
%>
<script language='javascript'>

 </script>
      <aside>
                    <fieldset>

                    <c:forEach var="webPageInfo" items= "${webPageInfoList}" varStatus="x">
                    
                    <h3 class="content-subhead" id="yui_3_14_1_1_1397782431674_8">
                   		<i class="fa fa-dot-circle-o"></i>상품정보 ${webPageInfo.goodsNo}
                	</h3>
				        <label for="상품명">상품명</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.goodsNm}</p>
	          			<label for="상품판매가">상품판매가</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.goodsPrice}</p>
			            <label for="대표이미지">대표이미지</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.goodsImg}</p>
			            <label for="할인율">할인율</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.goodsDisc}</p>
			            <label for="대분류">대분류</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.cate1}</p>
			            <label for="중분류">중분류</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.cate2}</p>
			            <label for="소분류">소분류</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.cate3}</p>
			            <label for="소분류">구매버튼</label><p id="yui_3_14_1_1_1398665339210_17">${webPageInfo.goodsIsbuyPatten}</p>
                    </c:forEach>
					
                    </fieldset>
                
                </aside>
        
 
