<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

     $(document).ready(function(){
     	$("tr").click(function(){
 		
     		$('.modal').on('show.bs.modal', function (e) {
     			  // do something...
     			})
     		
 			$.ajax({
				type:"get",
				url:"auth_messageBoxContent.do?mno=" +$(this).children().eq(2).children().eq(1).val() + "&member_id=" +$(":input[name=member_id]").val(),
				dataType:"json",
				success:function(data){
					
					  if(data!=""){	
					  
						  
						  $('#exampleModal').modal({
		                       remote :   $("#massage_title").html(data.title),
		                       remote :   $("#massage_sender").html(data.sender),
		                       remote :   $("#message-text").html(data.message)
		                });
						 
						  
			            }
				}
			});

 
     	});
     	 
    });
         
         
</script>
        
    <style type="text/css">
th,td { font-size: 10pt; line-height: 160%; }
.shorttitle { width: 220; height: 18; display: block; overflow: hidden; }
</style>
    <div class="col-md-8">
    <h3>나의 메세지함</h3>
   
    <hr>
    <form id="messageForm">
    <table class="table">
    	<tr>
    		<td>보낸이</td>
    		<td>제목</td>
    		<td>내용</td>
    		<td>날짜</td>
    		<td>읽음여부</td>
    	</tr>
    	<c:forEach var="msvo" items="${requestScope.mlist.list}">	
    	<tr>
    		<td> ${msvo.sender }</td>
    		<td>${msvo.title }</td>
    		<td> 
			<span class=shorttitle>${msvo.message}</span><input type="hidden"  name="mno" value="${msvo.mno}">
    		</td>     	

    	
    		
    		<td> ${msvo.send_date }</td>
    		<td>
  				<c:choose>
					<c:when test="${msvo.read>0}">
						O
					</c:when>
					<c:otherwise>
						X
					</c:otherwise>
				</c:choose>
			</td>
    	<tr>
    	</c:forEach>		
    </table>
    
<input type="hidden" name="member_id" value="${sessionScope.mvo.member_id}"> 

    </form>
    
 
    <c:if test="${requestScope.mlist.mspagingBean.isPreviousPageGroup()}">
<a href="${initParam.root}auth_messagebox.do?pageNo=${requestScope.mlist.mspagingBean.startPageOfPageGroup-1}&member_id=${sessionScope.mvo.member_id }">◀ </a>
</c:if>
		
		
<c:forEach begin="${requestScope.mlist.mspagingBean.startPageOfPageGroup }" end="${requestScope.mlist.mspagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.mlist.mspagingBean.nowPage==i}">
		${i}
	</c:when>
	<c:otherwise>
			<a href="${initParam.root}auth_messagebox.do?pageNo=${i }&member_id=${sessionScope.mvo.member_id }">${i }</a>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.mlist.mspagingBean.isNextPageGroup()}">
	<a href="${initParam.root}auth_messagebox.do?pageNo=${requestScope.mlist.mspagingBean.endPageOfPageGroup+1}&member_id=${sessionScope.mvo.member_id }">▶ </a>
	</c:if> 

	
</div>

																								
<div class="modal" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
        <form>
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
    
