<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <link rel="stylesheet" href="css/type5.css" type="text/css">
<script type="text/javascript">
	$(document).ready(function(){
		$("#followForm :input[name=addBtn]").click(function(){
			var dor=$(this).parent().siblings().next().text();
			//var dor=$("#listView tr td").parent().siblings().next().text();
			//alert(dor);
			/* alert((dor+"2")); */
			/* alert(member_id); */
		 	if ($(this).val() == "+팔로우") {
				$(this).val("v팔로잉");
				$.ajax({
					type:"POST",
					url:"auth_add.do",
					data:"follower=${sessionScope.mvo.member_id}&following="+dor,
					dataType:"json",
					success:function(data){
					}//callback			
				});//ajax
			} else {
				$(this).val("+팔로우");
				 $.ajax({
		               type:"POST",
		               url:"auth_delete.do",
		               data:"follower=${sessionScope.mvo.member_id}&following="+dor,
		               dataType:"json",
		               success:function(data){
		               }//callback         
		            });//ajax
			}  		 	
		}); 
		$("#listView a").click(function(){
 			var id=$(this).text();
 			if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
 			 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
			"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
 			}else{
 				return false;
 			}
 		});
	});
</script>
<!-- <form action ="add.do"> -->
<form id="followForm" >
 <table border="1" class="type05">
<thead>
<tr><th>사  진</th><th>ID</th></tr>
</thead>
<tbody id="listView">
<c:forEach items="${requestScope.mvoList}" var="mvo" varStatus="status">
	 ${mvoList[status.index]}
	<c:choose>
		<c:when test="${sessionScope.mvo.member_id==mvo.member_id}">
		<tr>
			<td></td><td>${mvo.member_id}</td>
		</tr>
		</c:when>
		<c:when test="${mvo.isFollow==true}">
		<tr>
			<td></td><td><a href="">${mvo.member_id}</a><input type="button" value="v팔로잉" name="addBtn"></td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td></td><td><a href="">${mvo.member_id}</a><input type="button" value="+팔로우" name="addBtn"></td>
		</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
</tbody>
</table>
</form>
