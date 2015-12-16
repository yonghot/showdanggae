<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/type5.css" type="text/css">
<jsp:include page="../templates/right.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
/* 	$("#followingView a").click(function(){
		var id=$(this).text();
		if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
		 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
	"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
		}else{
			return false;
		}
	});	 */
});
</script>
<table border="1" class="type05">
   <thead>
      <tr>
         <th>ID</th>
      </tr>
   </thead>
   <tbody id="followingView">
      <c:forEach items="${requestScope.followerList}" var="fvo">
         <tr>
            <td><a href="">${fvo.follower}</a></td>
         </tr>
      </c:forEach>
   </tbody>
</table>
