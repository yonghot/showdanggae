<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#subBtn").click(function() {
			var idComp = $(":input[name=member_id]").val();
			var passwordComp = $(":input[name=password]").val();
			if (idComp == "") {
				alert("아이디를 입력해주세요");
				return false;
			}
			if (passwordComp == "") {
				alert("비밀번호를 입력해주세요");
				return false;
			}
		});
	});
</script>

<div class="col-md-8">
	<h1 class="text-center">로그인</h1>

	<form class="form-horizontal" role="form" action="login.do"
		method="post">

		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputEmail3" class="control-label">ID</label>
			</div>
			<div class="col-sm-10 hidden-sm hidden-xs text-left">

				<input type="text" class="form-control" name="member_id" placeholder="아이디" value="java">

			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputPassword3" class="control-label">Password</label>
			</div>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="password"
					placeholder="비밀번호">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
						<input type="checkbox"> 로그인 유지 
					</label>
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-12" align="center">
				<button type="submit" class="btn btn-default" id="subBtn">로그인</button>
			</div>
		</div>
	</form>

	<hr>
	<div align="center">
		<a href="${initParam.root}findidview.do">아이디찾기 |</a> <a
			href="${initParam.root}findpassview.do">비밀번호찾기 | </a> <a
			href="${initParam.root}registerview.do">회원가입</a>
	</div>
</div>
