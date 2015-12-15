<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		$("#sendBtn").click(function(){

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
<%-- <form class="form-inline" action="sendMessage.do" id="sendForm">
<input type="text" class="form-control"  size="30" placeholder="제목" style="height:30px;" name="title" ><br>
보내는이:<input type="text" value="${sessionScope.mvo.member_id}" size="10"  readonly="readonly"  name="sender" ><br><br>
<input type="hidden" value="${requestScope.member_id}" name="member_id">
<textarea class="form-control"  rows="10" cols="30"   placeholder="보낼 메세지를 입력해주세요" name="message"></textarea><br>
<input type="submit"  id="sendBtn"  value="보내기"> 
<input type=button value=취소 onclick='javascript:self.close()'>
</form>
 --%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
        <form id="sendForm" action="sendMessage.do">
          <div class="form-group">
            <label for="recipient-name" class="control-label">title: <span id="massage_title"></span></label>
             	<div class="modal-title" >           	
    		 	 </div>
          </div>
                    <div class="form-group">
            <label for="recipient-name" class="control-label">보낸사람:<span id="massage_sender"></span></label>
             	<div class="modal-sender">
    		 	 </div>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">내용:</label>
            <div class="modal-message"></div>
            <textarea class="form-control" id="message-text" readonly="readonly"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>











