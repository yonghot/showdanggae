<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		
		$("#findbyid").click(function(){
			var nameComp = $(":input[name=member_name]").val();
			var emailidComp = $(":input[name=email_id]").val();
			var emaildomainComp = $(":input[name=email_domain]").val();

			$.ajax({
				type:"get",
				url:"findIdByEmail.do?member_name=" + nameComp + "&email_id=" + emailidComp + "&email_domain=" + emaildomainComp,
				//dataType:"json", 쓰면 안됨 ㅠ
				success:function(data){	
					if(data!=""){
						  $('.modal').modal({          
		                   remote :   $("#result").html("입력하신 정보 검색 결과 :  " +data),
		                   remote :   $("#footer").html("<button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button><button type='button' class='btn btn-primary'  onclick=location.href='member_login.do'>로그인 이동</button>" 
		      	  )                  	                      
		                });
					
					}
					if(data==""){
						 $('.modal').modal({ 
		                       remote :   $("#result").html("입력하신 정보 검색 결과가 없습니다"),
		                       remote :   $("#footer").html("<button type='button' class='btn btn-default' data-dismiss='modal'>닫기" 
		                     )
		                });
					}
				}
			});
			
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
				<label for="inputEmail3" class="control-label">Name</label>
			</div>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="member_name" id="name" placeholder="이름">
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
		
		<div class="form-group">
			<div class="col-sm-12" align="center">
		<button type="button" class="btn btn-default" id="findbyid">아이디찾기</button>
			</div>
		</div>

	</form>
</div>

<!-- modal -->
<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">아이디 검색 결과</h4>
      </div>
      <div class="modal-body" id="result">
      </div>
      <div class="modal-footer" id="footer">
<!--         <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary">로그인 이동</button> -->
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
