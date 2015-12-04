<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
(로그인 페이지)<br>
    <script type="text/javascript">
    $(document).ready(function(){
    	$("#subBtn").click(function(){
    		var idComp = $(":input[name=member_Id]").val();
    		var passwordComp = $(":input[name=password]").val();
    		if (idComp=="") {
    			alert("아이디를 입력해주세요");
    			return false;
    		}		
    		if (passwordComp=="") {
    			alert("비밀번호를 입력해주세요");
    			return false;
    		}		

    	});
    });
    </script>

<form action="login.do" method="post">
아이디<input type="text" name="member_Id" ><br>
비밀번호<input type="password" name="password"  ><br>
<input type="submit" value="로그인" id="subBtn"><br>
</form>

<hr>
<a href="">아이디찾기</a> 
<a href="">비밀번호찾기 </a>
<a href="${initParam.root}registerview.do">회원가입</a>
