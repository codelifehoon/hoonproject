<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body class="subbody">
	<div class="content">
			<div class="header">
				<div class="loginf">
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
				
				<div class="searchf">
				<form method="post" action="<%=rootUrl %>/search/searchResultList.action">
					<input type="text" name="searchStr" class="search" style="width:307px;"/> <input type='submit' name='submit' value="검   색">
				</form>  
				
				</div>
				
				<div class="leftside">
				<h1>RSS 공유모임</h1>
				<h2>자신의 블로그를 다른사람과 공유하세요.</h2>
				</div>
			</div>