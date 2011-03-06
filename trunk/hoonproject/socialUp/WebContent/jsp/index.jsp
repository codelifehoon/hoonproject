<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.content.dto.ContentTitleTblDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%	

/*
	MemTblDTO  loginMemTblDTO = (MemTblDTO)request.getAttribute("loginMemTblDTO");
	
	CookieUtil cookieUtil = new CookieUtil(request,response);
	
	
	logger.debug("쿠키정보:" + cookieUtil.getCookie(CookieUtil.TP));
	logger.debug("쿠키정보:" + cookieUtil.getSubCookie(CookieUtil.TP,"mem_id"));
	logger.debug("쿠키정보2:" + cookieUtil.getSubCookie(CookieUtil.TP,"mem_nm"));
	
	
	if (loginMemTblDTO == null)
	{
		logger.debug("회원로그인 안되었음");
	}
	if (loginMemTblDTO != null)
	{
		logger.debug("mem_id:" + loginMemTblDTO.getMem_id());
		logger.debug("mem_nm:" + loginMemTblDTO.getMem_nm());
	}
*/
	List<ContentTitleTblDTO>  regContentTitleList = (List<ContentTitleTblDTO>)request.getAttribute("regContentTitleList");
	
%>
<jsp:include page="/jsp/common/gnb_main.jsp"></jsp:include>

 <!--  내용 시작 -->
 </br></br>
 <center >
 	<input type='input' name="" class="input150" style="width:400px;height:15px;"></input>
 	<span class="button blue"><button type="button"> 검   색 </button></span>
 </center>
 
 <div>
 <table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
				<br><br><h0>실시간 업데이트 고리</h0><br><br>
		</td>
	</tr>
	<tr>

	<tr>
	<td>
		<table style="width:1000px; margin:0 auto;" cellpadding="0" cellspacing="0" height='600px' >
		<tr><td valign='top'>
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			<h4_under><b>고리명 블라블라블라(생성자 ,등록글수)</b></h4_under>
			<h5><a href=''>글제목 블라블라블라  - </a><b>HIT xxx건,작성일: 년월일시분</b></h5>
			<h5>
				상세내용상세내용세내용상세내용~~~~~~~~~~~~상세내용상세내용~~~~~~~~~~~~~~~~~~~~</br>
				상세내용상세내용~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</br>
				상세내용~~~~~~~~~~~~~~</br>
			</h5>
			<p>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
				<img src="http://c1img.nate.com/img/mall/items2/2011/02/her_c.gif"/>
			</p>
			<br>
			
		</td>
		</tr>
		</table>
	</td>
	</tr>
	
</table>
</div>
<!--  내용 종료 -->

				
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

