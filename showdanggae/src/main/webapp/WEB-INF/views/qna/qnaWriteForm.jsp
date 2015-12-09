<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<script type="text/javascript">
$(document).ready(function() {
	
	$("#qnawrcBtn").click(function(){
		location.href="qnaWritecancel.do";
	});
	
	$("#subBtn").click(function(){

	var titleComp=$(":input[name=title]").val();
	var contentComp=$(":input[name=content]").val();
	if (titleComp=="") {
			alert("제목을 입력해주세요");
			return false;
		}		
	if (contentComp=="") {
			alert("내용을 입력해주세요");
			return false;
		}		
	});
	
	

});

</script>


<div class="col-md-8">
	<form action="${initParam.root}qnawrite.do"  method="post" name="write_form">  
		<h3>Q&A 쓰기</h3>
		<hr>
		<table class="table">
			<tbody>
				<tr>
					<td>제목</td>
					<td colspan="3">
						<input type="text" name="title"  id="title" size="48">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="writer" size="8" value="${sessionScope.mvo.member_name}" readonly="readonly"></td>  
				</tr>
				<tr>
					<td colspan="4" align="left">
						<textarea cols="105" rows="10" name="content" id="content"></textarea>
						<br>
					<input type="hidden" name="member_id" value="${sessionScope.mvo.member_id}">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<input type="submit" value="글쓰기" id="subBtn" class="btn btn-info btn-md">
						<input type="button" value="취소" id="qnawrcBtn" class="btn btn-info btn-md">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>