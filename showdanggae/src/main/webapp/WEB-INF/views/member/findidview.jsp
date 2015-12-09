<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#subBtn").click(function() {
			var birthComp = $(":input[name=birthday]").val();
			var emailComp = $(":input[name=email_id]").val();
			if (birthComp == "") {
				alert("생년월일을 입력해주세요");
				return false;
			}
			if (emailComp == "") {
				alert("이메일을 입력해주세요");
				return false;
			}
		});
	});
</script>

<div class="col-md-8">
	<h1 class="text-center">ID 찾기</h1>
	<br>

	<form class="form-horizontal" role="form"
		action="${initParam.root}findIdByBirth.do" method="post">

		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputEmail3" class="control-label">ID</label>
			</div>
			<div class="col-sm-10 hidden-sm hidden-xs text-left">
				<input type="text" class="form-control" name="member_id" placeholder="아이디">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputEmail3" class="control-label">Name</label>
			</div>
			<div class="col-sm-10 hidden-sm hidden-xs text-left">
				<input type="text" class="form-control" name="member_name" placeholder="이름">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputPassword3" class="control-label">Email-ID</label>
			</div>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="email_id" placeholder="이메일 아이디">
			</div>
		</div>

		<div class="col-sm-offset-2 col-sm-10">
			<select id="email_domain" class="form-control" name="email_domain">
				<option value="nate.com">nate.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="naver.com">naver.com</option>
				<option value="hotmail.com">hotmail.com</option>
				<option value="gmail.com">gmail.com</option>
			</select><br>
		</div>
		<div class="form-group">
			<div class="col-sm-12" align="center">
				<button type="submit" class="btn btn-default" id="subBtn">아이디
					찾기</button>

			</div>
		</div>

	</form>
</div>