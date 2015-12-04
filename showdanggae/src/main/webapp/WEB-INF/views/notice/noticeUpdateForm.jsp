<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	   <script type="text/javascript">
	   $(document).ready(function() {
		   $("#updateBtn").click(function(){		   
			   if (confirm("정말 수정하시겠습니까???") == true){    //확인
				    document.form.submit();
				}else{   //취소
				    return;
				}
		   });
	   });
	   
	   </script>
	   	<div class="col-md-7">
<form method=post action="update.do?no=${requestScope.NoticeVO.no}">
<table class="table">
	<tr>
		<td>글번호: ${requestScope.NoticeVO.no } | 타이틀:
		<input type=text name=title value=${requestScope.NoticeVO.title }></input>
		</td>
	</tr>
	<tr>
		<td><font size="2">작성자: ${sessionScope.managerlogin.member_name} |
				작성일시:${requestScope.NoticeVO.time_post} </font></td>
	</tr>
	<tr>
		<td><textarea rows="15" cols="75" name="content">
		${requestScope.NoticeVO.content}
		</textarea></td>
	</tr>
	
	<tr>
	
		<td valign="middle"><input type="submit" value="수정하기"  id="updateBtn" class="btn btn-default"></input></td>
	</tr>
</table>
</form>
</div>