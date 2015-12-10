<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
	//noticeWrBtn	
	$(document).ready(function() {
		
		$("#noticeWrBtn").click(function(){
			location.href="noticeWritecancel.do";
		});
	
	});
</script>

<div class="col-md-8">
	<form action="${initParam.root}write.do"  method="post" name="write_form">  
		<h3>공지사항 쓰기</h3>
		<hr>
		<table class="table">
			<tbody>
				<tr>
					<td>제목</td>
					<td colspan="3">
						<input type="text" name="title" size="48">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="writer" size="8" value="${sessionScope.managerlogin.member_name}" readonly="readonly"></td>  
				</tr>
				<tr>
					<td colspan="4" align="left">
						<textarea cols="105" rows="10" name="content"></textarea>
						<br>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="글쓰기" id="subBtn" class="btn btn-info btn-md">
						<input type="button" value="취소" id="noticeWrBtn" class="btn btn-info btn-md">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>