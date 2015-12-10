<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#resultBtn").click(function(){
		if($("#password").val() != $("#realpassword").val()){
			alert("비밀번호가 틀립니다");
			return false;
		}else{
			return true;
		}
	});
	
});
</script>
 
<div class="col-md-8">
	<div class="col-md-12">
		<h3>비밀번호 확인</h3>
		<hr>
	</div>
	<div class="col-md-12">
		<form class="form-horizontal" role="form" action="auth_member_myinfo_view.do">
			<div class="form-group">
				<div class="col-sm-2">
					<label for="inputPassword3" class="control-label">비밀번호 입력</label>
				</div>
				<div class="col-md-5">
					<input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요">
					<input type="hidden" id="realpassword" value="${sessionScope.mvo.password}">
				</div>
				</div>
				<div class="form-group"><div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default" id="resultBtn">확인<br></button>
				</div>
			</div>
		</form>
	</div>
</div>

    