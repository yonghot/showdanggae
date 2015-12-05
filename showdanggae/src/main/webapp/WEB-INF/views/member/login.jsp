<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 	$("#subBtn").click(function(){
 		var idComp = $(":input[name=member_id]").val();
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

 <div class="col-md-7">
    <h1 class="text-center">로그인</h1>
    
    <form class="form-horizontal" role="form" action="login.do" method="post">
        <div class="form-group">
            <div class="col-sm-2">
                <label for="inputEmail3" class="control-label" >ID</label>
            </div>
            <div class="col-sm-10 hidden-sm hidden-xs text-left">
                <input type="text" class="form-control"  name="member_id" placeholder="ID">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2">
                <label for="inputPassword3" class="control-label">Password</label>
            </div>
            <div class="col-sm-10">
                <input type="password" class="form-control"  name="password" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default" id="subBtn">로그인</button>
            </div>
        </div>
    </form>
    
<hr>
<a href="">아이디찾기</a> 
<a href="">비밀번호찾기 </a>
<a href="${initParam.root}registerview.do">회원가입</a>
</div>
