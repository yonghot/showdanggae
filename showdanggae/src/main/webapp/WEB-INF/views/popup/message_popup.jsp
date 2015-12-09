<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		$("#sendBtn").submit(function(){

		$.ajax({
			type:"get",
			url:"sendMessage.do",
			data: $("#sendForm").serialize(),
			dataType:"json",
			 success:function(data){   
				 }
	        })
		});
 	});
</script>

    
<h3>메세지 보내기</h3>

<form class="form-inline" action="sendMessage.do" id="sendForm">
<input type="text" class="form-control"  size="30" placeholder="제목" style="height:30px;" name="title" ><br>
보내는이:<input type="text" value="${sessionScope.managerlogin.member_name}" 
size="10"  readonly="readonly"  name="spand_name" ><br><br>
${sessionScope.managerlogin.member_name}

<input type="hidden" value="${requestScope.member_id}" name="member_id">
<textarea class="form-control"  rows="10" cols="30"   placeholder="보낼 메세지를 입력해주세요" name="message"></textarea><br>
<input type="submit"  id="sendBtn"  value="보내기"> 
<input type=button value=취소 onclick='javascript:self.close()'>


</form>

