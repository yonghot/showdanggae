<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#replyBtn").click(function(){
		if($("#title").val()==""){
			alert("제목을 입력하세요!");
			return;
		}else if($("#writer").val()==""){
			alert("작성자 이름을 입력하세요!");
			return;
		}else if($("#password").val()==""){
			alert("패스워드를 입력하세요!");
			return;
		}else if($("#content").val()==""){
			alert("본문 내용을 입력하세요!");
			return;
		}
		$("#writeForm").submit();
	});
});

</script>
	   
	   
	   	<div class="col-md-7">
<form method=post action="reply.do?no=${requestScope.qvo.no}" id="writeForm">
<table class="table">
	<tr>
		<td>글번호: ${requestScope.qvo.no } | 글제목:
		<input type=text name=title value="RE:${requestScope.qvo.title }" 
		id="title" readonly="readonly"></input>
		</td>
	</tr>
	<tr>
		<td><font size="2">작성자: ${requestScope.qvo.writer} |
				작성일시:${requestScope.qvo.writeDate} </font></td>
	</tr>
	<tr>
<td><textarea rows="15" cols="75" name="content">
본문내용 : 
${requestScope.qvo.content}

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

답글내용 :
</textarea></td>
	</tr>
	
	<tr>
		<td valign="middle"><input type="submit" value="답글달기"  
		id="replyBtn" class="btn btn-default"></input></td>
	</tr>
</table>
  <input type="hidden" name="ref" value="${requestScope.qvo.ref }">
 <input type="hidden" name="restep" value="${requestScope.qvo.restep }">
 <input type="hidden" name="relevel" value="${requestScope.qvo.relevel }">
 <input type="hidden" name="no" value="${requestScope.qvo.no }">

  <input type="hidden" name="member_id" value="${sessionScope.mvo.member_id }"> 
  <input type="hidden" name="writer" value="${sessionScope.mvo.member_name}"> 
</form>
</div>