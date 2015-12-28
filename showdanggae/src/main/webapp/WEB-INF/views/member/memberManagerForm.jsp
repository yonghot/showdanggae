<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       
 <script type="text/javascript">
 	$(document).ready(function(){
 			
 		$("#memberManagingTable :input[name=deleteBtn]").click(function(){	
 		
			var id=$(this).parent().parent().children().eq(0).text();
 			if(confirm(id + '회원을 삭제하시겠습니까?')==true){			
 				location.href="memberDelete.do?member_id=" + id;
 			}else{
 				return false;
 			}
 		
 	 	});
 		
 		$('#exampleModal').on('shown.bs.modal', function (event) {
 			  var button = $(event.relatedTarget) 
 			  var recipient = button.data('whatever')
				
 			  var modal = $(this)
 			  modal.find('.modal-title').text('New message to ' + recipient)
 			  modal.find('.modal-body input').val(recipient)
 			})

  		$("#sendMessage").click(function(){
 			
 			if(confirm('메세지를 보내시겠습니까?')==true){			
 				$("#sendForm").submit();
 			}else{
 				return false;
 			}
 			
 		}); 
 		
 	});
 
 </script>
  
 <div class="col-md-8">
 <h3>회원관리</h3>
 <form id="memberManagingTable">

 <br>
  <table class="table" id="memberManagerForm">
  		<tr>
			<td>id</td>  	
			<td>name</td>  
			<td>email</td>  				
			<td>birthday</td>  	
			<td>report</td>  	
			<td>delete</td>			
			<td>send</td>			
  		</tr>
			<c:forEach var="mvo" items="${requestScope.memberList.list}">				
			<tr>
			
			    <td>${mvo.member_id}</td> 	
				<td>${mvo.member_name }</td>
				<td>${mvo.email}</td>
				<td>${mvo.birthday }</td>
				<td>${mvo.report }</td>
				<td><input type="button" value="삭제" class="btn btn-default" id="deleteBtn" name="deleteBtn"></td>
				<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" 
				data-whatever="${mvo.member_id}">메세지보내기</button></td>
				
			</tr>	
			</c:forEach>
</table>

	<c:if test="${requestScope.memberList.mpagingBean.isPreviousPageGroup()}">
			<a href="${initParam.root}memberManagerForm.do?pageNo=${requestScope.memberList.mpagingBean.startPageOfPageGroup-1}">◀ </a>
		</c:if>
		
		
<c:forEach begin="${requestScope.memberList.mpagingBean.startPageOfPageGroup }" end="${requestScope.memberList.mpagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.memberList.mpagingBean.nowPage==i}">
		${i}
	</c:when>
	<c:otherwise>
			<a href="${initParam.root}memberManagerForm.do?pageNo=${i}">${i}</a>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.memberList.mpagingBean.isNextPageGroup()}">
	<a href="${initParam.root}memberManagerForm.do?pageNo=${requestScope.memberList.mpagingBean.endPageOfPageGroup+1}">▶ </a>
	</c:if>  

	
</form>
	
	</div>
	
<div class="modal fade" id="exampleModal"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
        <form action="sendMessage.do" id="sendForm">
          <div class="form-group">
            <label for="recipient-name" class="control-label">받는이:</label>
            <input type="text" class="form-control" id="recipient-name" name="member_id">
          </div>
         <div class="form-group">
            <label for="message-title" class="control-label">제목:</label> 
            <input type="text" class="form-control" id="message-text"  placeholder="제목" name="title" >
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">메세지:</label>
            <textarea class="form-control" id="message-text" placeholder="보낼내용" name="message"></textarea>
          </div>
			<input type="hidden" value="${sessionScope.mvo.member_name}" name="sender">
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button type="button"class="btn btn-primary" id="sendMessage">보내기</button>
      </div>
    </div>
  </div>
</div>
	