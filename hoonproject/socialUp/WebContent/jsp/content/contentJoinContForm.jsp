<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="socialUp.service.content.dto.ContentBranchDTO"%>
<%@page import="socialUp.service.content.dto.ContentJoinMemDTO"%>
<%@page import="socialUp.common.util.NumUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="socialUp.service.common.dao.CodeDetailDAOImpl" %>
<%@page import="socialUp.service.content.dto.ContentDtlTblDTO"%>
<%@page import="socialUp.service.content.dto.ContentTitleTblDTO"%>
<%@ page import="java.util.List" %>
<%@ include file="/jsp/common/pageCommon.jsp" %>
<% 
	
	 

// 내가 여기 컨텐츠에 소속될 권한이 있는지 check(참여는 참여를 원하는 컨텐츠에 join mem 여야만 가능함.
	List<ContentJoinMemDTO> 	contentJoinMemList 	=  (List<ContentJoinMemDTO>)request.getAttribute("contentJoinMemList");
	ContentJoinMemDTO			contentJoinMem = null;
	if (contentJoinMemList.size() >0 ) contentJoinMem = contentJoinMemList.get(0);
	
	
// 내가 가지고 있는 컨텐츠 타이틀 목록
	List<ContentTitleTblDTO> 	contentTitleList 	=  (List<ContentTitleTblDTO>)request.getAttribute("contentTitleList");

// 내가 참여를 원하는 컨텐츠타이틀에 이미 소속 되어 있는 나의 컨텐츠 타이틀 목록
	List<ContentBranchDTO> 		contentBranchList 	=  (List<ContentBranchDTO>)request.getAttribute("contentBranchList");

// 내가 참여를 원하는 컨텐츠 타이틀의 정보
	ContentTitleTblDTO 			contentTitle		=  (ContentTitleTblDTO)request.getAttribute("contentTitle");

	System.out.println("authInfo.getMt_no():" + authInfo.getMt_no());

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=rootUrl%>/images/style.css" type="text/css" />
	<script language="javascript" 	src="<%=rootUrl%>/js/common.js"></script>
	<script language="javascript" 	src="<%=rootUrl%>/dwr/interface/DwrContentAction.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/engine.js"></script> 
	<script language="javascript" 	src="<%=rootUrl%>/dwr/util.js"></script>
	
	<script language="javascript">
	
	// 자신의 컨텐츠를 추가한다.
	function moveAdd()
	{
        var to_remove_counter=0; //number of options that were removed (num selected options) 
        var checkCnt = 0; 		 // selected options to right select box (to) 
        for (var i=0;i<getF().MyContList.options.length;i++) 
        { 
                if (getF().MyContList.options[i].selected==true) 
                { 
                        var addtext		=	getF().MyContList.options[i].text; 
                        var addvalue	=	getF().MyContList.options[i].value;
            
                        //아이디값 비교후 이미 선택한 장치이면 경고창을 띄운다. 
                        //for(var j=0; j<getF().BranchList.options.length;j++)
                        //{
                        //    tovalue = getF().BranchList.options[j].value; 
                        //    if(addvalue==tovalue) { 
                        //        checkCnt++; 
                        //    } 
                        //} 
                        //if(checkCnt>0) { 
                        //    alert("이미 선택한 장치입니다."); 
                        //    return; 
                        //} 
                        getF().BranchList.options[getF().BranchList.options.length]=new Option(addtext,addvalue); 
                        getF().MyContList.options[i].selected=false; 
                        ++to_remove_counter; 
                } 
                else 
                { 
                        getF().MyContList.options[i-to_remove_counter].selected=false; 
                        getF().MyContList.options[i-to_remove_counter].text=getF().MyContList.options[i].text; 
                        getF().MyContList.options[i-to_remove_counter].value=getF().MyContList.options[i].value; 
                } 
        }
        //선택된 좌측의 데이터를 삭제 
        var numToLeave=getF().MyContList.options.length-to_remove_counter; 
        for (i=getF().MyContList.options.length-1;i>=numToLeave;i--) 
        { 
                getF().MyContList.options[i]=null; 
        } 
    } 
	
	// 자신의 컨텐츠를 제거한다.
	function moveDel()
	{

        var to_remove_counter=0; //number of options that were removed (num selected options) 
        var checkCnt = 0; 		 // selected options to right select box (to) 
        for (var i=0;i<getF().BranchList.options.length;i++) 
        { 
                if (getF().BranchList.options[i].selected==true && getF().BranchList.options[i].value.substr(0,8) != "dontMove") 
                { 
                        var addtext		=	getF().BranchList.options[i].text; 
                        var addvalue	=	getF().BranchList.options[i].value;
            
                        //아이디값 비교후 이미 선택한 장치이면 경고창을 띄운다. 
                        //for(var j=0; j<getF().MyContList.options.length;j++)
                        //{
                        //    tovalue = getF().MyContList.options[j].value; 
                        //    if(addvalue==tovalue) { 
                        //        checkCnt++; 
                        //    } 
                        //} 
                        //if(checkCnt>0) { 
                        //    alert("이미 선택한 장치입니다."); 
                        //    return; 
                        //} 
                        getF().MyContList.options[getF().MyContList.options.length]=new Option(addtext,addvalue); 
                        getF().BranchList.options[i].selected=false; 
                        ++to_remove_counter; 
                } 
                else 
                { 
                        getF().BranchList.options[i-to_remove_counter].selected=false; 
                        getF().BranchList.options[i-to_remove_counter].text=getF().BranchList.options[i].text; 
                        getF().BranchList.options[i-to_remove_counter].value=getF().BranchList.options[i].value; 
                } 
        }
        //선택된 좌측의 데이터를 삭제 
        var numToLeave=getF().BranchList.options.length-to_remove_counter; 
        for (i=getF().BranchList.options.length-1;i>=numToLeave;i--) 
        { 
                getF().BranchList.options[i]=null; 
        } 
        
	}
	 
	function updateJoinBtn()
	{
		var MyContStr ="";
		var BranchStr ="";
		
		// 참여목록을 , 구분자로 문자열로 생성
		for (var i=0;i<getF().BranchList.options.length;i++) 
        { 
            
			if (i==0 ) 	{BranchStr += getF().BranchList.options[i].value;}
			else 		{BranchStr += "," + getF().BranchList.options[i].value; }
        }
		
		// 미참여목록을 , 구분자로 문자열로 생성
		for (var i=0;i<getF().MyContList.options.length;i++) 
        { 
            
			if (i==0 ) 	{MyContStr += getF().MyContList.options[i].value;}
			else 		{MyContStr += "," + getF().MyContList.options[i].value; }
        }
		
		getF().BranchStr.value = BranchStr;
		getF().MyContStr.value = MyContStr;
		getF().submit();
	}
	
	</script>
</head>


<%@ include file="/jsp/common/gnb_sub.jsp" %>

			<div class="main_content">
			
			<div class="sd_main">
			<h4>
			<%if (contentJoinMem != null && "01".equals(contentJoinMem.getStat()))
			{ %>

				<form name='defForm' method="get" action="<%=rootUrl %>/content/contentJoinContFinish.action" >
				<input type="hidden" name="joinWantTtNo" value="<%=contentTitle.getTt_no() %>"/>
				<input type="hidden" name="MyContStr" value=""/>
				<input type="hidden" name="BranchStr" value=""/>
				
				<table>
					<tr>
						<td>
							<select multiple="multiple"  name="MyContList"   style="width:300px;height:200px;">
								<%for (ContentTitleTblDTO ct : contentTitleList ) 
								{
								
									// 기존에 브랜치에 참여중인것이 있다면 그중에서 매칭되는 title정보를 별도로 보관한다.
									boolean isExists = false;
									for (ContentBranchDTO cb : contentBranchList)
									{
										// 이미 참여된 컨텐츠 타이틀이 존재하는지 확인
										if (ct.getTt_no().equals(cb.getBelong_tt_no()))
										{
											isExists = true;
										}
									}
									
									// 이미 참여한 컨텐츠에 존재하지않아야 대상 목록에 추가할수 있다.
									if (!isExists)
									{
								%>
									<option value="<%=ct.getTt_no() %>"><%=ct.getTitle_name() %></option>
								<%
									}
								}%>
    
							</select>
						</td>
						<td> 
							<a href="javascript:moveAdd();" >추가</a><br/>
							<a href="javascript:moveDel();">삭제</a> 
						</td>
						<td>
							<select multiple="multiple" name="BranchList" style="width:300px;height:200px;">
							
							
							<%
								// 추가할려는 사용자의 컨텐츠 타이틀 이지만  삭제불가능한 목록을 찾아서  미리 보여준다.
								for (ContentTitleTblDTO ct : contentTitleList ) 
								{
									// 기존에 브랜치에 참여중인것이 있다면 그중에서 매칭되는 title정보를 별도로 보관한다.
									ContentTitleTblDTO matchCt = null;
									boolean isExists = false;
								
									for (ContentBranchDTO cb : contentBranchList)
									{
										// 1. 참여한 브랜치 목록에  추가할려는 사용자의 목록이존재
										// 2. 해당 목록이 참여자가 추가한것이 아닌것은 변경불가능
										if (ct.getTt_no().equals(cb.getBelong_tt_no()) 
												&& !cb.getCreate_no().equals(authInfo.getMt_no())
											)
										{
											matchCt = ct;
											isExists = true;
										}
									}
								
									// 나의 컨텐츠타이틀이지만 삭제는 불가능한 목록에추가
									if (isExists)
									{
								%>
									<option value="dontMove:<%=matchCt.getTt_no() %>"><%=matchCt.getTitle_name() %>[이동불가]</option>
								<%	}
								} 
								%>
							
							
								
								<%
								// 추가할련느 사용자의 컨텐츠 타이틀중 변경 가능한 목록을 찾아서보여준다.
								for (ContentTitleTblDTO ct : contentTitleList ) 
								{
									// 기존에 브랜치에 참여중인것이 있다면 그중에서 매칭되는 title정보를 별도로 보관한다.
									ContentTitleTblDTO matchCt = null;
									boolean isExists = false;
								
									for (ContentBranchDTO cb : contentBranchList)
									{
										// 1. 참여한 브랜치 목록에  추가할려는 사용자의 목록이존재
										// 2. 해당 목록이 참여자가 추가한것은 수정가능
										if (ct.getTt_no().equals(cb.getBelong_tt_no()) 
												&& cb.getCreate_no().equals(authInfo.getMt_no())
											)
										{
											matchCt = ct;
											isExists = true;
										}
									}
									
									if (isExists)
									{
								%>
									<option value="<%=matchCt.getTt_no() %>"><%=matchCt.getTitle_name() %></option>
								<%	}
								} %>
								
							</select>
						</td>
					</tr>
					<tr>
						<td align='center'  colspan='3'>
							<input type="button" onClick='javascript:updateJoinBtn();' name="updateBtn" value="수정 하기"></input>
						</td>
					</tr>
				</table>
				

			</dd>
				
			</form>
				
			<%} else {%>
				<!-- 컨텐츠 브랜치에 참여할수 있는 권한이 없을경우 -->
				해당 컨텐츠 타이틀에 참여가능한 권한이 없습니다.  <br/>
				참여를 원하시시면 해당 컨텐츠의 참여회원으로등록이 되어야 합니다.<br/>
				참여회원 등록을 원하시면 <a href="<%=rootUrl %>/content/contentJoinMemForm.action?tt_no=<%=contentTitle.getTt_no() %>">[여기]</a> 를 눌러서 주세요
				
				 
			<%} %>
			</h4>
			</div>
				
			</div>

				
<%@ include file="/jsp/common/footer.jsp" %>


