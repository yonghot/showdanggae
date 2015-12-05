<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${initParam.root}js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#followForm :input[name=addBtn]").click(function(){
			var dor=$(this).parent().siblings().next().text();
			/* alert((dor+"2")); */
			/* alert(member_id); */
		 	if ($(this).val() == "+팔로우") {
				$(this).val("v팔로잉");
				$.ajax({
					type:"POST",
					url:"add.do",
					data:"follower=${sessionScope.mvo.member_id}&following="+dor,
					dataType:"json",
					success:function(data){
						
					}//callback			
				});//ajax
			} else {
				$(this).val("+팔로우");
				 $.ajax({
		               type:"POST",
		               url:"delete.do",
		               data:"follower=${sessionScope.mvo.member_id}&following="+dor,
		               dataType:"json",
		               success:function(data){
		                 
		               }//callback         
		            });//ajax
			}  
		 	
		}); 
	});
</script>
</head>
<body>
<jsp:include page="../templates/right.jsp"></jsp:include>
<a href="home.do">홈으로</a><br><br>
<!-- <form action ="add.do"> -->
<form id="followForm">
<hr>
<table border=2 id="followTable" class="table-condensed">

<thead>
<tr><th>순번</th><th>ID</th></tr>
</thead>
<tbody id="listView">

<c:forEach items="${requestScope.mvoList}" var="mvo" varStatus="status">
	 <%-- ${mvoList[status.index]} --%>
	<c:choose>
		<c:when test="${sessionScope.mvo.member_id==mvo.member_id}">
		<tr>
			<td>${status.index+1}</td><td>${mvo.member_id} </td> 
		</tr>
		</c:when>
		
		<c:when test="${mvo.isFollow==true}">
		<tr>
			<td>${status.index+1}</td><td>${mvo.member_id}<input type="button" value="v팔로잉" name="addBtn"> </td>
		</tr>
		</c:when>
		
		<c:otherwise>
		<tr>
			<td>${status.index+1}</td><td>${mvo.member_id}<input type="button" value="+팔로우" name="addBtn"> </td>
		</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>

</tbody>
</table>
</form>
