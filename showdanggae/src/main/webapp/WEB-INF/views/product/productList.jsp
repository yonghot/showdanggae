<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${initParam.root}bootstrap.css" type="text/css">

<div class="col-md-8">
	<table class="table">
		<caption>목록</caption>
		<thead>
		<tr>
			<th class="no">글 번호</th>
			<th class="title">제목</th>
			<th class="name">이름</th>
			<th class="date">작성일</th>
			<th class="hit">조회수</th>
		</tr>
		</thead>
		<tbody><!-- Controller에서 request 객체에 setAttribute해 둔 list를 EL로 가져와서 사용 -->			
			<c:forEach items="${requestScope.pvoList}" var="list"><!-- 리스트에 순차적으로 접근하는 forEace문 -->
				<tr>
				    <td>${list.product_id}</td>
				    
				    <c:choose>
						<c:when test="${sessionScope.mvo!=null}">
							<td><a href="hit.do?no=${list.product_id}">${list.product_name}</a></td>
							<!-- 클릭시 조회수를 올리면서 글 내용을 봐야 하는데 이게 동시에 이루어 지면 글보기에서 새로고침시 조회수가 오른다 -->
							<!-- 그래서 HitController를 먼저 들러서 조회수만 올린 뒤 showContent로 redirect하여 우회한다. -->
						</c:when>
						<c:otherwise>
							<td>${list.product_name}</td>
						</c:otherwise>
				    </c:choose>			
					<td>${list.review_score}</td>
					<td>${list.regist_date}</td>
					<td>${list.hits}</td>
					<td>${list.likes}</td>
					<td>${list.dislikes}</td>
				</tr>					
			</c:forEach>
		</tbody>					
	</table><br></br><!-- 테이블 밑에 항상 글쓰기 버튼 따라다니도록 삽입 -->
	
	<a href="board/write.jsp"><img src="${initParam.root}img/write_btn.jpg" border="0"></a>
</div>