<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var userInputId = getCookie("userInputId");
		$("input[name='member_id']").val(userInputId);

		if ($("input[name='member_id']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
			$("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}
		$("#idSaveCheck").change(function() { // 체크박스에 변화가 있다면,
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기 체크했을 때,
				var userInputId = $("input[name='member_id']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			} else { // ID 저장하기 체크 해제 시,
				deleteCookie("userInputId");
			}
		});

		// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		$("input[name='member_id']").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
				var userInputId = $("input[name='member_id']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}
		});
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

	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
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
				<input type="text" class="form-control" name="member_id"
					placeholder="아이디">
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
			<div class="col-sm-offset-2 col-sm-12">
				<div class="checkbox">
					<label class="checkbox-inline"> 
					<input type="checkbox" id="idSaveCheck" >ID 저장
					</label> 
					<label class="checkbox-inline">
					<input type="checkbox" id="autoLogin" >자동로그인
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
