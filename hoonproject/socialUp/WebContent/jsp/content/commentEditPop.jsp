<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/pageCommon.jsp"%>
<%
	String editType		= request.getParameter("editType");
	String cdc_no		= request.getParameter("cdc_no");
	String mt_no		= request.getParameter("mt_no");
	String comment		= request.getParameter("comment");
	String cd_no		= request.getParameter("cd_no");
	
%>
<%@ include file="/jsp/common/scriptCommon.jsp"%>
<!-- 댓글삭제인데 비밀번호를 입력 받아야 할때 -->
<%if ( "D".equals(editType) && "-1".equals(mt_no)) { %>

댓글삭제를  위해서 비밀번호를 입력하세요.<br><br>
<input type='input' name="passwd" id="passwd" class="input150" />
<input type="button" onClick="editCommentAsk('D')" value="삭제하기"/>
 
<%} %>



<!-- 댓글 수정일때 -->
<%if ( "U".equals(editType)) { %>

댓글수정을 수정해주세요.<br><br>
<input type='input' name="comment" id="comment" class="input400" value="<%=comment %>"/>

	<%if ( "-1".equals(mt_no)) { %>
		<input type='input' name="passwd" id="passwd" class="input150" value="비밀번호입력"/>
	<%}else { %>
		<input type='hidden' name="passwd" id="passwd"  />
	<%} %>
<input type="button" onClick="editCommentAsk('E')" value="수정하기"/> 

<%} %>
 
<script language="javascript">
	
function editCommentAsk(editType)
{
	if (editType  == "E")
	{
		if (confirm("선택한 댓글을 수정  하겠습니까?")) 
		{
			opener.editComment(editType,<%=cdc_no%>,getEleById("comment").value,getEleById("passwd").value,<%=cd_no%>);
			window.close();
		}
	}
	
	if (editType  == "D")
	{
		if (confirm("선택한 댓글을 삭제 하겠습니까?"))
			{
				opener.editComment(editType,<%=cdc_no%>,"",getEleById("passwd").value,<%=cd_no%>);
				window.close();
			}
	}
	
	
}

</script>


