<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css" >
.m38{display:block;margin-left:auto;margin-right:auto}
</style>
<style type="text/css" >
.o39{background-color:#fbfbfb;border-radius:50%;border:1px solid #cccfd0;box-sizing:border-box;overflow:hidden;position:relative}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"profileInfo.do?member_id=${sessionScope.mvo.member_id}",
		dataType:"json",
		success:function(data){
			if(data!=""){
				for(var i=0; i<data.length;i++){						
					
				 }			
			}
		}
	});

	    
	});
</script>

<div class="col-md-2">

	<c:choose>
		<c:when test="${sessionScope.mvo.member_id=='admingalbage'}">
			<!-- 매니져로 로그인 되었을때 -->
			<h3>manager Page</h3>
			<hr>
			<h4><a href="memberManagerForm.do">회원관리</a></h4>
			<h4><a href="notice.do">공지사항관리</a></h4>
		</c:when>
		<c:when test="${!empty sessionScope.mvo && sessionScope.mvo.member_id!='admingalbage'}">
			<!-- 일반회원으로 로그인 되었을때 -->

			<ul class="nav nav-tabs">
  			<li role="presentation" class="active"><a href="home.do">Home</a></li>
  			<li role="presentation"><a href="Profile.do">Profile</a></li>
			</ul>
	 	<span class="social social-linked-in"></span> 

				<div class="row">
				<!-- 	 <div class="col-sm-6 col-md-4" > -->
					    <div class="thumbnail">
				${sessionScope.mvo.member_name} 님 로그인중
					    <img src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" alt="..." class="img-circle">
				  			
    					   <span class="label label-default"  data-toggle="modal" data-target="#myModal" >프로필사진편집</span>  
							

	 <br>
 					      <div class="caption">		
 					    
 					     
					     
					        게시물
					        팔로잉 
					        팔로우

					     
					      
					      <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> 
					      </div> 
					    


					 </div>
				</div>
		</c:when>
	</c:choose>	     
</div>



