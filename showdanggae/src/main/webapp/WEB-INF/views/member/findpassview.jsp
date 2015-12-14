<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		
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
	});
	
</script>

<div class="col-md-8">
	<h1 class="text-center">Password 찾기</h1><br>

	<form class="form-horizontal" role="form" action="${initParam.root}findPassById.do" method="post">
		
		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputEmail3" class="control-label">ID</label>
			</div>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="member_id" placeholder="아이디">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-2">
				<label for="inputEmail3" class="control-label">Name</label>
			</div>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="member_name" placeholder="이름">
			</div>
		</div>


	<div class="form-group">
			<div class="col-sm-2">
				<label for="inputPassword3" class="control-label">Email</label>
			</div>
			
			<div class="col-sm-2">
				<input type="text" class="form-control" name="email_id" >
			</div>
			
			<div class="col-sm-1">@</div>

			<div class="col-sm-2">
					<input class="form-control" id="email_domain"
						name="email_domain" readonly="readonly" value="" type="text"/>
					<input id="email" name="email" value="" type="hidden" />
			</div>
			
			<div class="col-sm-2">
				<select id="emailSelect" class="form-control" name="emailSelect">
						<option value="" selected="selected">- 이메일 선택 -</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="etc">직접입력</option>
				</select>
			</div>
		</div>
		<br>
		<div class="form-group">
			<div class="col-sm-12" align="center">
				<button type="submit" class="btn btn-default" >패스워드 찾기</button>
			</div>
		</div>
	</form>
</div>
