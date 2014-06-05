<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>

<script language='javascript'>

//$(function() {});
 

function pattenValidBtnClick()
{
			   //button 클릭시
			var Url = "/SiteMng/ajax/validCrawlSeedURL.json";
			var Data  =  $("#dataForm").serialize();
			$('#pattenValidBtn').attr('disabled', true);
			
			//var Data = { 필드명1:"값1"
			//			,필드명2:"값2"
			//			};
		
			jQuery.ajax({	
		    type : "POST" 
		    , url : Url 
		    , dataType : "JSON"   
		    , data : Data
		    , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
		    , timeout:80000
		    , success:function(response, status, request) {
		    	//alert("성공");
	
		     	  
		    	//var obj = $.parseJSON(response);
		     	
		    	var visiteTargetList = response.visiteTargetList;
		     	var webPageInfoList = response.webPageInfoList;
		     	var urlValidList = response.urlValidList;
		     	
		     	
		     	printURLInfo(visiteTargetList,webPageInfoList,urlValidList);
		     	
		     	
		     	//JSON.parse(data);
		     	$('#pattenValidBtn').attr('disabled', false);
		    }
			, error:function(request,status,error){
		        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        alert("연결실패.");
		     	$('#pattenValidBtn').attr('disabled', false);
			}
			});
			

	}


function printURLInfo(visiteTargetList,webPageInfoList,urlValidList)
{
	var htmlStr  = "";
	var cnt = 0;
	
	
	console.log(visiteTargetList);
	console.log(webPageInfoList);
	console.log(urlValidList);

	// URL 기본정보 검증
	htmlStr += "<h4>방문대상URL</h4>";
	htmlStr += "<p>";

	
	if (urlValidList != "")
 	{
 		$.each(urlValidList, function(i, el) 
 		{ 

 			
 			htmlStr += "최초방문경로:" + el.seedStrURL + "<br>";
 			htmlStr += "대상URL여부:" + el.visitSiteYn + "<br>";
 			htmlStr += "대상도메인여부:" + el.visitUrlYn + "<br>";
 		
 		});
     }
	htmlStr += "<p>";
	
	
	// 방문대상 URL 리스트 출력
	htmlStr += "<h4>방문대상URL</h4>";
	htmlStr += "<p>";
	if (visiteTargetList != "")
 	{
		
		
 		$.each($(visiteTargetList).slice(0, 10), function(i, el) 
 		{ 
 			
 			
 			htmlStr += el.url + "<br>";
 			cnt++;
 		});
     }
	
	if (cnt >=10) htmlStr +="<h4>외 " + ($(visiteTargetList).length-10) + "건</h4>";
	cnt = 0;
	
	htmlStr += "</p>";
 	
	
	// 상품URL 출력
	htmlStr += "<h4>상품URL</h4>";
	htmlStr += "<p>";
 	if (webPageInfoList != "")
 	{
 		$.each($(webPageInfoList).slice(0, 10), function(i, el) 
 		{
 			
 			var temp = '"'+ el.goodsUrl + '"';
 			htmlStr += "상품URL(상품번호:" + el.goodsNo + " )<br><a href='#' onClick='javascript:goodsDetailPopup(" + temp + ")'>" + el.goodsUrl + "</a><br>";
 			cnt++;
 		});
 		
     }
 	if (cnt >=10) htmlStr +="<h4>외 " + ($(webPageInfoList).length-10) + "건</h4>";
 	
 	htmlStr += "</p>";
 	
 	$("#crawlerInfo").html(htmlStr) ;
 	
}

function goodsDetailPopup(goodsUrl)
{

	var url = "/SiteMng/RegGoodsInfoPopup.do?";
	
	url += "goodsUrl=" + encodeURIComponent(goodsUrl);
	url += "&" + $("#dataForm").serialize();
	 
	window.open(url, "popupWindow", "width=800, height=700, scrollbars=yes");

}

</script>


<div class="content">
        <h2 class="content-head is-center">미리보기</h2>

        <div class="pure-g">
            <div class="l-box-lrg pure-u-1 pure-u-med-2-5">
                <form id='dataForm' class="pure-form pure-form-stacked" >
                    <fieldset>
					
					<h3 class="content-subhead" id="yui_3_14_1_1_1397782431674_8">
                    	<i class="fa fa-dot-circle-o"></i>수집대상 정보 패턴
                	</h3>
                		<label for="브라우저종류">브라우저종류</label>
		          		<input id="crawlAgent" name="crawlAgent" type="text" placeholder="브라우저종류" value="Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30">
	                   	<label for="사이트명">사이트명</label>
		          		<input id="siteNm" name="siteNm" type="text" placeholder="사이트명" value='11st'>
			            <label for="최초방문경로">최초방문경로</label>
			            <input id="seedURL" name="seedURL"  type="text" placeholder="수집을 위한 최초 URL" value='http://m.11st.co.kr/MW/Product/productBasicInfo.tmall?prdNo=954321212'>
			            <label for="대상URL ">대상URL</label>
			            <input id="visitUrlPatten" name="visitUrlPatten" type="text" placeholder="수집대상이 되는 URL 패턴" value=".*(\.(html|tmall)).*">
			            <label for="대상도메인">대상도메인</label>
			            <input id="visitSitePatten" name="visitSitePatten" type="text" placeholder="수집대상이 되는  domain 패턴" value=".*(11st.co.kr).*">
			            <label for="대상상품URL">대상상품URL(소문자)</label>
			            <input id="goodsUrlPatten" name="goodsUrlPatten" type="text" placeholder="수집대상이 되는  상품URL 패턴" value=".*(productBasicInfo.tmall?).*">
			            <label for="상품번호패턴">상품번호패턴</label>
			            <input id="goodsNoPatten" name="goodsNoPatten" type="text" placeholder="수집된 URL에서 상품번호를 추출 하기 위한 패턴" value="prdNo=\d*">
			        <h3 class="content-subhead" id="yui_3_14_1_1_1397782431674_8">
                   		<i class="fa fa-dot-circle-o"></i>상품정보(element path) 패턴
                	</h3>
				        <label for="상품명경로">상품명경로</label>
	          			<input id="goodsNamePatten" name="goodsNamePatten" type="text" placeholder="상품명패턴" value="#cts section.base div.dtl_heading">
			            <label for="상품판매가경로">판매가경로</label>
			            <input id="goodsPricePatten" name="goodsPricePatten" type="text" placeholder="상품판매가패턴" value="#cts section.base form div.dtl_info div.total div.prc strong">
			            <label for="대표이미지 경로">대표이미지 경로</label>
			            <input id="goodsImgPatten" name="goodsImgPatten" type="text" placeholder="대표이미지 패턴" value="#mwDev_imageWrap">
			            <label for="할인율">할인율</label>
			            <input id="goodsDisc" name="goodsDisc" type="text" placeholder="할인율" value="">
			            <label for="대분류경로">대분류경로</label>
			            <input id="cate1Patten" name="cate1Patten" type="text" placeholder="대분류패턴" value="">
			            <label for="중분류경로">중분류경로</label>
			            <input id="cate2Patten" name="cate2Patten" type="text" placeholder="중분류패턴" value="">
			            <label for="소분류경로">소분류경로</label>
			            <input id="cate3Patten" name="cate3Patten" type="text" placeholder="소분류패턴" value="">
			  			<input id="pattenValidBtn" type="button" onClick="javascript:pattenValidBtnClick();"  value="확인" class="pure-button">
			  			
                    </fieldset>
                </form>
            </div>

            <div class="l-box-lrg pure-u-1 pure-u-med-3-5">
            	<h3 class="content-subhead" id="yui_3_14_1_1_1397782431674_8">
                   		<i class="fa fa-dot-circle-o"></i>상세설명 및 패턴결과
                </h3>
            	<div id='crawlerInfo'>
                <h4>최초방문경로</h4>
                <p> 수집을 시작할 최초 URL
                   	<br>ex) http://www.auction.co.kr/category/category12.html
                </p>
                <h4>대상URL</h4>
                <p> 방문대상이 되는 URL의 정규식 패턴을 표기 합니다. @ 구분자를 이용해서 여러개의 패턴 입력이 가능 합니다.
                	아래의 패턴은  url의 마지막 문자가  html 또는 do 로 끝나는 URL은 방문 대상으로 지정 합니다.
                	<br>ex) .*html@.*do
                </p>
                <h4>대상도메인</h4>
                <p> 방문대상이 되는 도메인의 정규식 패턴을 표기 합니다. @ 구분자를 이용해서 여러개의 패턴 입력이 가능 합니다.
                	아래의 패턴은  도메인이  11st.co.kr 또는  12st.co.kr 일 경우 방문대상으로 지정 합니다.
                	<br>ex) .*(11st.co.kr).*@.*(12st.co.kr).*
                </p>
                <h4>대상상품URL</h4>
                <p> 상품정보를 가지고 있는 URL의 정규식 패턴을 표기 합니다. @ 구분자를 이용해서 여러개의 패턴 입력이 가능 합니다.
                	아래의 패턴은  URL에 sellerproductdetail.tmall 또는  productdetail.tmall 일 경우 방문대상으로 지정 합니다.
                	<br>ex) .*(sellerproductdetail.tmall?).*@.*(productdetail.tmall?).*
                </p>
                
                <h4>상품번호패턴</h4>
                <p> 대상상품URL를 대상으로 상품번호를 추출하기 위한 정규식 패턴을 표기 합니다. @ 구분자를 이용해서 여러개의 패턴 입력이 가능 합니다.
                	아래의 패턴은  URL에  prdno=1234  또는 no=1234 일 경우  상품번호로 추출 합니다.
                	<br>ex) prdno=\d*@no=\d*
                </p>
                
                <h4>상품명경로 </h4>
                <p> 상품상세 html에 상품명을 추출하기 위한 xpath 설정을 합니다. (CSS Selector)
                	<br>chrome plugin 활용(https://chrome.google.com/webstore/detail/selector-assistant/ckdgigcpjmambfmbabgjmmcicnnkjemi)
                </p>
                
                <h4>대표이미지 경로 </h4>
                <p> 상품상세 html에  대표이미지 경로를 추출하기 위한 xpath 설정을 합니다. (CSS Selector)
                </p>
                
                <h4>대분류경로 </h4>
                <p> 상품상세 html에  대분류경로를 추출하기 위한 xpath 설정을 합니다. (CSS Selector)
                </p>
                <h4>중분류경로 </h4>
                <p> 상품상세 html에  중분류경로를 추출하기 위한 xpath 설정을 합니다. (CSS Selector)
                </p>
                <h4>소분류경로 </h4>
                <p> 상품상세 html에  소분류경로를 추출하기 위한 xpath 설정을 합니다. (CSS Selector)
                </p>
               
                
                </div>
            </div>
        </div>

    </div>
    
    

