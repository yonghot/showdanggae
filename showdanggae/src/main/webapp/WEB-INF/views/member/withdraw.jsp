<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#cancelBtn").click(function(){
		location.href="home.do";
	})

	$("#withdrawBtn").click(function(){

		
		if($(":input[name=password]").val() == ""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		if($(":input[name=password]").val() != $(":input[name=repassword]").val()){
			alert("비밀번호가 틀립니다");
			return false;
		}
		
		var reason = $("#reason").val(); 
		if(reason==""){
			alert("탈퇴사유를 입력해주세요")
			return false;
		}
	  
	    if (confirm("탈퇴하시겠습니까?") == true) {
	    	return true;
	    } else {
	    	location.href="home.do";
	    	return false;
	    }
	
	
	});	

	
});



</script>
	<div class="col-md-7">
    <div class="section"><div class="container"><div class="row"><div class="col-md-12"><h1>회원탈퇴 페이지<br></h1>
    </div></div></div></div>
	
	<form action="withdraw.do">
	
	비밀번호확인
	<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력해주세요"><br>
	<input type="hidden" name="repassword" value="${sessionScope.mvo.password }">
	<input type="hidden" name="member_id" value="${sessionScope.mvo.member_id }">
	
	<br>
	<br>
	
	탈퇴사유를 적어주세요
	<textarea cols="60" rows="10" name="reason" id="reason" class="form-control" placeholder="탈퇴사유를 입력해주세요"></textarea><br>
	
	<input type="button" value="취소" id="cancelBtn" class="btn btn-default">
	<input type="submit" value="탈퇴하기" id="withdrawBtn" class="btn btn-default">
	
	</form>
</div>