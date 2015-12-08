<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

     $(document).ready(function(){
    	 
     	$("#messageForm a").click(function(){
 			//메세지 확인form
 			var mno=$(this).children().eq(0).val();	
			alert(mno);
 			 window.open("${initParam.root}messageBoxContent.do?member_id=${sessionScope.mvo.member_id}&mno="+mno,"popup",
			"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 

     	});
    });
         
         //${requestScope.list}
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
    	<c:forEach var="msvo" items="${requestScope.list}">	
    	<tr>
    		<td> ${msvo.spand_name }</td>
    		<td>${msvo.title }</td>
    		<td> 
    		<span class=shorttitle><a>${msvo.message}<input type="hidden" value="${msvo.mno}"></a></span>
  
    		</td>     	
    		<td> ${msvo.spand_date }</td>
    		<td>
  				<c:choose>
					<c:when test="${msvo.read>1}">
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
    </form>

    </div>
    
