<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	
%>	

<body class="mainbody">

	<div class="content">
			
			<div class="header">
				<div class="searchf">
					<form method="post" action="<%=rootUrl %>/member/memberMemLogIn.action">
						<p>
						<%if (!authInfo.isAuth()){ %>
						이메일: <input type="text" name="mem_id" class="search" />
						비밀번호: <input type="password" name="passwd" class="search" />
						<input type='submit' name='submit' value="로그인">
						<%} else { %>
							<%=authInfo.getMem_nm() %>님 로그인. <a href="<%=rootUrl %>/member/memberMemLogOut.action" >로그아웃</a>
						<%} %> 
						</p>
					</form>
				</div>
				<div class="leftside">
				<h1>RSS 공유모임</h1>
				<h2>자신의 블로그를 다른사람과 공유하세요.</h2>
				</div>
			</div>
			<div id="lnews">
				<h2>What is sNews?</h2>
				<a href="http://www.solucija.com/home/snews/">
				sNews</a> is a completly free <a href="http://www.php.net/">PHP</a> and <a href="http://www.mysql.com/">MySQL</a> driven tool for publishing and maintaining news articles on a website. Integrating sNews into your existing design is simple, but you could also use sNews on it's own, as a simple Content Management System. Consisting of only one file, <a href="http://www.solucija.com/home/snews/">sNews</a> 
				is extremely lightweight, very easy to install, and easy to use via a simple web interface.
			</div>
				
			<div id="nav">
			  	<ul></ul>
			</div>