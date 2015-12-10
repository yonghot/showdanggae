<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>

<script type="text/javascript">
	$(document).ready(function() {
		alert("메롱");
			$.ajax({
				type : "post",
				url  : "auth_ajaxMemberCategoryList.do",
				/* data : "member_id", */
				dataType : "json",
				success:function(result) {
					if(jsonData.result=="success")
						$.each(result, function(category) {
							var list = result[category];
							var content = "<table>";
							for(i=0; i<list.length; i++) {
								content +="<tr>";
								content +="<td>" + list[i].category + "</td>";
								content +="<td>" + list[i].category_id + "</td>";
								content +="<td>" + list[i].member_id + "</td>";
								content +="</tr>";
							}
							content +="<table class='table table-striped'>";
							$("#ajaxList").html(content);
						});
				}
			});
		});
</script>

<div class="col-md-8">
	
	<div>
		<form method="post" action="addCategory.do">
			<div>
					<c:forEach items="${requestScope.mainCategoryList}" var="list">
					<div class="radio-inline">
					
						<label> <input type="radio" name="optionsRadios" id="addCategoryBtn" value="${list.category}"> ${list.category}
						</label>
					
					</div>
					</c:forEach>&nbsp;&nbsp;
				<button type="submit" class="btn btn-default btn-xs" id="add" >추가</button>
			</div>
		</form>
	</div>
	
	<!--**************************************************************************  -->
	<div>
	<table class="table table-striped">
		<thead>
			<tr>
				<td>카테고리</td>
				<td>카테고리ID</td>
				<td>회원ID</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.memberCategoryList }" var="clist">
				<tr>
					<td>${clist.category}</td>
					<td>${clist.category_id}</td>
					<td>${clist.member_id}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- ******************************************************************************************* -->
	<div>
	<table class="table table-striped">
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
		<tbody>
			<!-- Controller에서 request 객체에 setAttribute해 둔 list를 EL로 가져와서 사용 -->
			<c:forEach items="${requestScope.pvoList}" var="list">
				<!-- 리스트에 순차적으로 접근하는 forEace문 -->
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
	</table>
	

	</div>
	<br></br><!-- 테이블 밑에 항상 글쓰기 버튼 따라다니도록 삽입 -->
	<a href="beforeGoingRegistProduct.do?category_id=${requestScope.productAndCategoryMap.pvoList[0].category_id}"> 
	<img src="${initParam.root}img/write_btn.jpg" border="0"></a>
</div>

