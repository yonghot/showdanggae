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
			<h3>My Page</h3>
				${sessionScope.mvo.member_name} 님 로그인중
								<span class="social social-linked-in"></span>
			<hr>
				<div class="row">
				<!-- 	 <div class="col-sm-6 col-md-4" > -->
					    <div class="thumbnail">
					    
					      <img class="o39 m38" src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" data-reactid=".0.1.0.0:0.0.0">
					    
					  <!--     <img src="..." alt="..."> -->
 					      <div class="caption">		프로필편집박스
    					   <span class="label label-default">프로필편집</span>  
					        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
					      
					        <button type="button" class="btn btn-default btn-sm">
 							 <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Star
							</button>
					      
					      <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> 
					      </div> 
					    <!-- </div> -->


					 </div>
				</div>
		</c:when>
	</c:choose>	     
</div>
