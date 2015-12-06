<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-2">
	<c:choose>
		<c:when test="${!empty sessionScope.managerlogin}">
			<!-- 매니져로 로그인 되었을때 -->
			<h3>manager Page</h3>
			<hr>
			<h4><a href="memberManagerForm.do">회원관리</a></h4>
			<h4><a href="notice.do">공지사항관리</a></h4>
		</c:when>
		<c:when test="${!empty sessionScope.mvo}">
			<!-- 일반회원으로 로그인 되었을때 -->
			<h3>My Page</h3>
			<hr>
				${sessionScope.mvo.member_name} 님 로그인중
	    	<h4><a href="update_password.do">내정보 수정</a></h4>
			<h4><a href="getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=1">내 장바구니</a></h4>
			<h4><a href="messagebox.do?member_id=${sessionScope.mvo.member_id}">내 쪽지함</a>
			</h4>
		</c:when>
	</c:choose>	     
</div>
