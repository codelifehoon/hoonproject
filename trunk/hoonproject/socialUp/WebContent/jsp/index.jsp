<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/pageCommon.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=contectRootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=contectRootUrl%>/dwr/interface/DwrAction.js"></script> 
	<script language="javascript" 	src="<%=contectRootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=contectRootUrl%>/dwr/util.js"></script> 
	<script language="javascript">
	
	function getForm()
	{
		return document.memRegForm;
	}
	// 회원가입
	function memRegAction()
	{
		var errMsg = "";
		var memTblDTO = {mem_id:"aaaa", mem_nm:"장재훈"};
		
		if (getForm().memNm.value == "" ) errMsg = "<h3>이름을 입력 해주세요.</h3>";
	
		DwrAction.validateRegMemData(memTblDTO,validateRegMemDataCB);
			
	}
	
	function validateRegMemDataCB(resultList)
	{
	 
		
		var resultStr ="";
		for (var i=0;i<  resultList.length ;i++)
			{
				resultStr += resultList[i].mem_nm + ",";
			}
		
		document.getElementById("memRegMsg").innerHTML = resultStr;
		
	}
	</script>
	
	
</head>

<%@ include file="/jsp/common/gnb_main.jsp" %>
		
			<div class="main_content">
				<div class="sd_right">
					<div class="text_padding">
						<h2>회원가입</h2>
						<form name='memRegForm' action="" method="post" >
							<p class="date">이름:<input type="text"  id="memNm" name="memNm" /></p>
							<p class="date">이메일:<input type="text"  id="memId" name="memId" /></p>
							<p class="date">비밀번호:<input type="text"  id="passWord" name="passWord" />  </p>
							<p class="date">비밀확인:<input type="text"  id="passWord2" name="passWord2" />  </p>
							<p class="date"><a href="javascript:memRegAction();">회원가입</a></p>
							
						</form>
						<!--  회원가입시 오류메세지 출력에 필요한창 -->
						<div id='memRegMsg'></div>

					</div>
				</div>
			
				<div class="sd_left">
					<div class="text_padding">	
						<h2>About</h2>
      <p> This is Orange web 2.0, a free, fully standards-compliant CSS template designed by <a href="http://www.free-css-templates.com/">Free CSS Templates</a>.

This free template is released under a Creative Commons Attributions 2.5 license, so you're pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :) 
 </p>


							<p class="date">Posted by David <img src="<%=contectRootUrl%>images/more.gif" alt="" /> <a href="#">Read more</a> <img src="<%=contectRootUrl%>images/comment.gif" alt="" /> <a href="#">Comments(2)</a> <img src="<%=contectRootUrl%>images/timeicon.gif" alt="" /> 21.02.</p><br />
						<h2>Free Template</h2>	
      <p> This is Wonderful Green, a free, fully standards-compliant CSS template designed by <a href="http://www.free-css-templates.com/">Free CSS Templates</a>.</p>
 <p>
This free template is released under a Creative Commons Attributions 2.5 license, so you're pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :) </p>
							<p class="date">Posted by Jack <img src="<%=contectRootUrl%>images/more.gif" alt="" /> <a href="#">Read more</a> <img src="<%=contectRootUrl%>images/comment.gif" alt="" /> <a href="#">Comments(15)</a> <img src="<%=contectRootUrl%>images/timeicon.gif" alt="" /> 13.01.</p><br />				
				
					</div>
				</div>
				
				</div>
				<div id="bottomb">
					<div class="padding">
						<div class="rights">
							<h2>Partners</h2>
								<ul>
									<li> <a href="http://www.solucija.com">solucija.com</a></li>
									<li> <a href="http://www.free-css-templates.com">free-css-templates.com</a></li>
									<li> <a href="http://snews.vietbee.net/">snews.vietbee.net</a></li>
									<li> <a href="http://p-ahlqvist.com/">p-ahlqvist.com</a></li>
									<li> <a href="http://www.brauck.nl/">www.brauck.nl</a></li>
									<li> <a href="http://www.design4.ltd.pl/">www.design4.ltd.pl</a></li>
								</ul>
						</div>
						<div class="bottomd">					
							<h2>Past Articles</h2>
								<ul>
									<li><a href="http://www.iollo.com/home/welcome_to_iollos_all_stuff_reviews/">Welcome to iollo's review highway</a></li>
									<li><a href="http://www.iollo.com/home/open_source_web_designs_and_webmaster_forum/">Open Source Web Designs and Webmaster Forum</a></li>
									<li><a href="http://www.solucija.com/home/css-toplist/">CSS Toplist</a></li>
									<li><a href="http://www.solucija.com/home/new-template-internet-music/">New template: Internet Music</a></li>
									<li><a href="http://www.cssheaven.com/">CSS Heaven Gallery</a></li>
									
									
									
									
								</ul>
						</div>
						<div class="bottomc">					
							<h2>Top Articles</h2>
								<ul>
									<li><a href="http://www.solucija.com/home/new-template-internet-music/">New template: Internet Music</a></li>
									<li><a href="http://www.cssheaven.com/">CSS Heaven Gallery</a></li>
									<li><a href="http://www.solucija.com/home/css-toplist/">CSS Toplist</a></li>
									<li><a href="http://www.iollo.com/home/open_source_web_designs_and_webmaster_forum/">Open Source Web Designs and Webmaster Forum</a></li>
									<li><a href="http://www.iollo.com/home/welcome_to_iollos_all_stuff_reviews/">Welcome to iollo's review highway</a></li>
								</ul>
						</div>
						
						
						
						
					</div>
				</div>
				
<%@ include file="/jsp/common/footer.jsp" %>
