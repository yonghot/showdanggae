<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

$(document).ready(function() {

		$("#withdrawBtn").click(function(){
			
			var result = confirm('탈퇴하시겠습니까?');
	        if(result) {
	        	location.href="auth_withdrawForm.do";
	        } else {
	        	location.href="updatecancel.do";
	        }
		});
		
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
			alert("홈으로 이동합니다");
			location.href="auth_updatecancel.do";
		});
	
		
		$("#password").keyup(function() {
			var passwordComp = $(":input[name=password]").val();
			var repasswordComp = $(":input[name=repassword]").val();
	   		 var reg_pwd = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[!,@,#,$,%,^,&,*,?,_,~])(?=.*[a-zA-Z]).*$/;
			if(!reg_pwd.test(passwordComp)){
				$("#passResult").html("영문,숫자,특수문자 혼합하여 8자리~20자리 이내만 가능");
				  return false;
				 }else{
							$("#passResult").html("사용가능");
						}

		
			});
				
		$("#repassword").keyup(function() {
	
			var passwordComp = $(":input[name=password]").val();
			var repasswordComp = $(":input[name=repassword]").val();
			
			if($("#passResult").text()!="사용가능"){
				$("#repassResult").html("비밀번호 인증을 받으세요");
			}else if(passwordComp!=repasswordComp){
				$("#repassResult").html("비밀번호가 다릅니다!");
			}else{
				$("#repassResult").html("인증성공");
			}			
		});
		
		
		
		$("#subBtn").click(function() {
			var idComp = $(":input[name=member_id]").val();
			var passwordComp = $(":input[name=password]").val();
			var repasswordComp = $(":input[name=repassword]").val();
			var nameComp = $(":input[name=member_name]").val();
			var email_idComp = $(":input[name=email_id]").val();
			var email_domainComp = $(":input[name=email_domain]").val();
			var birthdayComp = $(":input[name=birthday]").val();
			var email=email_idComp+"@"+email_domainComp;
			var RegexName = /^[가-힣]{2,4}$/; //이름 유효성 검사 2~4자 사이
			$("#email").val(email); //조합한 이메일
	
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
			
	 		if ( !RegexName.test($.trim($("#name").val())) )
			{
			alert("이름 오류입니다 한글로 입력해주세요");
			$("#name").focus();
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
				alert("생년월일 예시와 같이 입력해주세요");
				return false;
			}
			if(isNaN(birthdayComp)==true){
						alert("생년월일은 숫자만 가능합니다");
				return false;
			}
			$("#registerForm").serialize();
	
		});
	});
	
		
</script>
<div class="col-md-8">
       <div class="col-md-12">
            <h3 class="text-left">회원정보수정</h3>
            <hr>
       </div>
       <div class="col-md-12">
           <form class="form-horizontal text-left" role="form" action="auth_updateMember.do">
               <div class="form-group">
                   <div class="col-sm-2">
                       <label for="inputEmail3" class="control-label">아이디</label>
                   </div>
                   <div class="col-sm-6 hidden-sm hidden-xs text-left">
                       <input type="text" class="form-control" value="${sessionScope.mvo.member_id }" 
                       name="member_id"  id="id"readonly="readonly">            
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2">
                       <label for="inputPassword3" class="control-label" >비밀번호</label>
                   </div>
                   <div class="col-sm-6">
                       <input type="password" class="form-control" name="password" id="password">
                  	 <span id="passResult"></span><br>
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2">
                       <label for="inputPassword3" class="control-label">비밀번호 확인</label>
                   </div>
                   <div class="col-sm-6">
                       <input type="password" class="form-control" name="repassword" 
                       id="repassword" placeholder="위와 똑같이 한번만 더 입력해주세요">
                       <span id="repassResult"></span>
                   </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-2">
                       <label for="inputPassword3" class="control-label">이름</label>
                   </div>
                   <div class="col-sm-6">
                       <input type="text" class="form-control"  id="name" name="member_name" 
                       value="${sessionScope.mvo.member_name }"  placeholder="이름">
                         </div>
                     </div>
				<div class="form-group">
                  <div class="col-sm-2">
                      <label for="inputPassword3" class="control-label">이메일</label>
                  </div>
                  <div class="col-sm-2">
                      	<input class="form-control" id="email_id" name="email_id"  
                      	value="${sessionScope.mvo.email_id }" type="text" />
                    </div>  
                   <div class="col-sm-1" align="center">
                   @
                   </div>
				<div class="col-sm-3">
					<input class="form-control" id="email_domain"  name="email_domain" readonly="readonly" 
					value="${sessionScope.mvo.email_domain }" type="text" placeholder="이메일 Domain"  />				 
					<input id="email" name="email" value="" type="hidden"  />
				</div>
				<div class="col-sm-3">
						 <select id="emailSelect" class="form-control"  name="emailSelect">
						<option value="" selected="selected">- 이메일 선택 -</option>
						<option value="naver.com" >naver.com</option>
						<option value="daum.net" >daum.net</option>
						<option value="nate.com" >nate.com</option>
						<option value="gmail.com" >gmail.com</option>
						<option value="etc">직접입력</option>
						</select>
                    </div>
                 </div>
                      
                 <div class="form-group">
                     <div class="col-sm-2">
                         <label for="inputPassword3" class="control-label">생년월일</label>
                     </div>
                     <div class="col-sm-4">
                         <input type="text"class="form-control"  name="birthday" value="${sessionScope.mvo.birthday}">
             		 </div>
               </div>
               <div class="form-group">
                   <div class="col-sm-12" align="center">
                       <button type="submit" class="btn btn-info btn-md" id="subBtn">수정</button>
                       <button type="button" class="btn btn-default btn-md" id="canBtn">취소</button>
                       <button type="button" class="btn btn-default btn-md" id="withdrawBtn">탈퇴</button>
             		</div>
               </div>
           </form>
       </div>
</div>
