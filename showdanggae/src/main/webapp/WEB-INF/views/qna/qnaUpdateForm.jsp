<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

</script>
	   
	   
	   	<div class="col-md-7">
<form method=post action="qnaUpdate.do?no=${requestScope.qvo.no}">
<table class="table">
	<tr>
		<td>글번호: ${requestScope.qvo.no } | 글제목:
		<input type=text name=title value=${requestScope.qvo.title }></input>
		</td>
	</tr>
	<tr>
		<td><font size="2">작성자: ${sessionScope.mvo.member_name} |
				작성일시:${requestScope.qvo.writeDate} </font></td>
	</tr>
	<tr>
		<td><textarea rows="15" cols="75" name="content">
		${requestScope.qvo.content}
		</textarea></td>
	</tr>
	
	<tr>
	
		<td valign="middle"><input type="submit" value="수정하기"  id="updateBtn" class="btn btn-default"></input></td>
	</tr>
</table>
</form>
</div>