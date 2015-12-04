<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       
 <script type="text/javascript">
 	$(document).ready(function(){
 			
 		$("#allselBtn").click(function(){
 			$("#memberManagingTable :input[name=check_member]").prop("checked",true);	
 		});

 		$("#cancelallsellBtn").click(function(){
 			$("#memberManagingTable :input[name=check_member]").prop("checked", false);		
 		});
 		
 		$("#messageBtn").click(function(){
 			
 		});
 		
 		$("#memberManagingTable :input[name=deleteBtn]").click(function(){	
 		
			var id=$(this).parent().parent().children().eq(1).text();
 			if(confirm(id + '회원을 삭제하시겠습니까?')==true){			
 				location.href="memberDelete.do?member_Id=" + id;
 			}else{
 				return false;
 			}
 		
 	 	});
 	/* 	
 		$("#memberManagerForm td").click(function(){
 			//클릭된 td가 속한 tr의 자식 td중 첫번째 요소의 텍스트를 alert으로
 				//alert($(this).parent().children().eq(1).text());
 			//
 			//alert($("#memberManagingTable :input[name=check_member]:checked").length + "명의 정보를 삭제하시겠습니까?");
 			//alert($("#memberManagingTable :input[name=check_member]:checked").parent().children().eq(1).text());
	 	});
 		 */
 		$("#memberManagerForm a").click(function(){
 			var id=$(this).text();	
 			if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
 			 window.open("${initParam.root}messagePopForm.do?member_Id="+id,"popup",
			"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
 			}else{
 				return false;
 			}
 		});
 		
 	});
 
 </script>
  
	<div class="col-md-7">
 <h3>회원관리</h3>
 <form id="memberManagingTable">

   <input type="button" value="전체선택" id="allselBtn" class="btn btn-default">
  <input type="button" value="선택취소" id="cancelallsellBtn" class="btn btn-default">
  <input type="button" value="메세지보내기" id="messageBtn" class="btn btn-default">
 <br>
  <table class="table"  id="memberManagerForm">
  		<tr>
  			<td></td>  	
			<td>id</td>  	
			<td>name</td>  
			<td>email</td>  				
			<td>birthday</td>  	
			<td>report</td>  	
			<td>delete</td>			
  		</tr>
			<c:forEach var="mvo" items="${requestScope.memberList.list}">				
			<tr>
				<td> <input type="checkbox"  id="checkbox_no" name="check_member"></td>
			    <td><a href="">${mvo.member_id }</a></td> 	
				<td>${mvo.member_name }</td>
				<td>${mvo.email}</td>
				<td>${mvo.birthday }</td>
				<td>${mvo.report }</td>
				<td><input type="button" value="삭제" class="btn btn-default" id="deleteBtn" name="deleteBtn"></td>
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
			<a href="${initParam.root}memberManagerForm.do?pageNo=${i }">${i }</a>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.memberList.mpagingBean.isNextPageGroup()}">
	<a href="${initParam.root}memberManagerForm.do?pageNo=${requestScope.memberList.mpagingBean.endPageOfPageGroup+1}">▶ </a>
	</c:if>  

	
</form>
	
	</div>