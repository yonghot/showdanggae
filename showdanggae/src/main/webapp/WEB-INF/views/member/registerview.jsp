
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">



$(document).ready(function() {
	
	$("#emailSelect").change(function(){
		var checkval1=$("#email_id").val();
		var checkval=	$("#emailSelect option:selected").val();
	
		if(checkval=="etc"){
			$('#email_domain').attr('readonly', false);		
			$("#email_domain").val("");
			$("#email_domain").focus();
		}else{
			$("#email_domain").val($("#emailSelect option:selected").val());
			$('#email_domain').attr('readonly', true);
		}	
			
	});
	
	$("#canBtn").click(function(){
		location.href="updatecancel.do";
	});

	$("#subBtn").click(function() {
	var idComp = $(":input[name=member_Id]").val();
	var passwordComp = $(":input[name=password]").val();
	var repasswordComp = $(":input[name=repassword]").val();
	var nameComp = $(":input[name=member_name]").val();
	var emailIdComp = $(":input[name=email_id]").val();
	var emaildomainComp = $(":input[name=email_domain]").val();
	var birthdayComp = $(":input[name=birthday]").val();
	var email=emailIdComp+"@"+emaildomainComp;
	
	
	$("#email").val(email);
	

	
	if (idComp == "") {
		alert("아이디를 입력해주세요");
		return false;
	}		

		if($("#checkResult").text() !="사용가능"){
			alert("아이디 인증을 받으세요");
			return false;
		}
		if (passwordComp == "") {
			alert("비밀번호를 입력해주세요");
			return false;
		}
		//repassResult
		if($("#repassResult").text() !="인증성공"){
			alert("비밀번호 인증을 받으세요");
			return false;
		}

		if (nameComp == "") {
			alert("이름을 입력해주세요");
			return false;
		}
		if (emailIdComp == ""||emailaddressComp=="") {
			alert("이메일을 입력해주세요");
			return false;
		}
		if (birthdayComp== "") {
			alert("생년월일을 입력해주세요");
			return false;
		}
		if (birthdayComp.length>6||birthdayComp.length<6) {
			alert("생년월일 주민등록번호 앞 6자리로 입력해주세요");
			return false;
		}
		if(isNaN(birthdayComp)==true){
					alert("생년월일은 숫자만 가능합니다");
			return false;
		}

		//alert($("#registerForm").serialize());
		$("#registerForm").serialize();
		
		
	});
});
</script>
<script type="text/javascript">
$(document).ready(function() {
			$("#id").keyup(function() {
			var id = $(":input[name=member_Id]").val();
			var idComp = $(":input[name=member_Id]").val().trim();
		
							if (idComp.length<6||idComp.length>12) {
							$("#checkResult").html("6자이상 12자이하만 가능!").css(
									"background", "pink");
							//alert($("#id").serialize());
							return;
						}			
						$.ajax({
							type:"get",
							url:"idCheck.do",
							data: $("#id").serialize(),
							dataType:"json",
							 success:function(data){   
						            if(data.vo==null){
						            	$("#checkResult").html("사용가능").css("background","green");
						            }else{
						            	$("#checkResult").html("아이디가 중복됩니다!").css("background", "red");
						            }
						        }
						  
						});
					});
///////////////////////
			$("#password").keyup(function() {
				var passwordComp = $(":input[name=password]").val();
				var repasswordComp = $(":input[name=repassword]").val();
				
							if(passwordComp.length<6||passwordComp.length>12){
								$("#passResult").html("6자이상 12자이하만 가능!").css(
										"background", "pink");
								
								return;
							}else{
								$("#passResult").html("사용가능").css(
										"background", "pink");
							}
							
						});
						/////////
			$("#repassword").keyup(function() {
				var passwordComp = $(":input[name=password]").val();
				var repasswordComp = $(":input[name=repassword]").val();
				
							if(passwordComp!=repasswordComp){
								$("#repassResult").html("비밀번호가 다릅니다!").css(
										"background", "pink");
								//alert($("#id").serialize());
								return;
							}else{
								$("#repassResult").html("인증성공").css(
										"background", "blue");
							}				
						});
						
						

			});
	
</script>


<body>
	<div class="col-md-7">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1 class="text-left">회원가입</h1>
					</div>
				</div>
			</div>
		</div>
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">
							<form name="registerForm" class="form-horizontal text-left"
								role="form" id="registerForm"
								action="${initParam.root}auth_register.do">

								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputEmail3" class="control-label">아이디</label>
									</div>
									<div class="col-sm-4 hidden-sm hidden-xs text-left">
										<input type="text" class="form-control" name="member_Id"
											id="id" placeholder="영문과 숫자로만 입력해주세요 (한글, 특수문자 불가)">
										<span id="checkResult"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputPassword3" class="control-label">비밀번호</label>
									</div>
									<div class="col-sm-4">
										<input type="password" class="form-control" name="password"
											id="password" placeholder="6자 이상 12자 이하로 입력해주세요"> <span
											id="passResult"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputPassword3" class="control-label">비밀번호
											확인</label>
									</div>
									<div class="col-sm-4">
										<input type="password" class="form-control" name="repassword"
											id="repassword" placeholder="위와 똑같이 한번만 더 입력해주세요"> <span
											id="repassResult"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputPassword3" class="control-label">이름</label>
									</div>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="member_name"
											placeholder="이름">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputPassword3" class="control-label">이메일</label>
									</div>

									<div class="col-sm-2">
										<input class="form-control" id="email_id" name="email_id"
											value="" type="text" placeholder="이메일 ID" />
									</div>
									
									<div class="col-sm-1">@</div>
									<div class="col-sm-2">
										<input class="form-control" id="email_domain"
											name="email_domain" readonly="readonly" value="" type="text"
											placeholder="이메일 Domain" /> <input id="email" name="email"
											value="" type="hidden" />
									</div>
									<div class="col-sm-2">
										<select id="emailSelect" class="form-control"
											name="emailSelect">
											<option value="" selected="selected">- 이메일 선택 -</option>
											<option value="naver.com">naver.com</option>
											<option value="daum.net">daum.net</option>
											<option value="nate.com">nate.com</option>
											<option value="gmail.com">gmail.com</option>
											<option value="etc">직접입력</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
										<label for="inputPassword3" class="control-label">생년월일</label>
									</div>
									<div class="col-sm-4">
										<input type="text" class="form-control" name="birthday"
											placeholder="주민등록번호 앞 6자리로 입력해주세요 (ex. 880307)">
									</div>
								</div>
								<br>
								<br>
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-2 text-left">
										<button type="submit" id="subBtn" class="btn btn-info btn-lg">회원가입</button>
										<button type="button" id="canBtn"
											class="btn btn-default btn-lg">회원가입취소</button>
									</div>
								</div>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>