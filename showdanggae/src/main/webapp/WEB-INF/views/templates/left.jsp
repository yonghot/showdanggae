<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"profileInfo.do?member_id=${sessionScope.mvo.member_id}",
		dataType:"json",
<<<<<<< HEAD
		success:function(data){		
			//alert(data.followerCount); 나를 팔로잉하는 사람들
			//alert(data.followingCount); 내가 팔로우한 사람들
=======
		success:function(data){
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
			if(data!=""){
				$("#followingcount").html(data.followingCount);
				$("#followcount").html(data.followerCount);
				 }			
			}
		
	});
	
	$.ajax({
		type:"get",
		url:"profileInterest.do?member_id=${sessionScope.mvo.member_id}",
		dataType:"json",
		success:function(data){	
			if(data!=""){
				var index="";
				//<span class="label label-default">#화장품</span> 
				for(var i=0; i<data.length; i++){
					index += "<span class='label label-default'>#"+data[i]+ "</span> ";
				}
				$("#interest").html(index);					
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
  			<li role="presentation"><a href="Profile.do?member_id=${sessionScope.mvo.member_id}">Profile</a></li>
			</ul>
	 	<span class="social social-linked-in"></span> 

				<div class="row">
				<!-- 	 <div class="col-sm-6 col-md-4" > -->
					    <div class="thumbnail">
				${sessionScope.mvo.member_name} 님 로그인중
					    <img src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" alt="..." class="img-circle">
				 <br>
				 
 					      <div class="caption">		
 					  ${sessionScope.mvo.member_id}     					    
					<ol class="breadcrumb">
  					<li class="active">게시글</li>
 					 <li class="active">팔로잉</li>
  					<li class="active">팔로우</li>
  					&nbsp;&nbsp;<li><span id="boardcount"></span></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 					 <li><span id="followingcount"></span></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<li><span id="followcount"></span></li>
					</ol>	
					내소개	
					<ol class="breadcrumb" >
					<li class="active">${sessionScope.mvo.member_info}</li>
					</ol>	
					나의 관심사
					<ol class="breadcrumb" >
					<li class="active"> 
					<span id="interest"></span>
					</li>
					</ol>	
					<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> 
					      </div> 
					 
					 </div>
				</div>
		</c:when>
	</c:choose>	     
		
</div>



