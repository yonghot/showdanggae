<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function(){
		
		//라디오 버튼 체크 후 선택하기 버튼을 누르면 DB에 저장 되고 새로 갱신된 카테고리를 표시한다. 12월 10일
		//$("#addBtn").on("click","#addBtn", function(){
		$("#addBtn").click(function() {
		//val() 값은 String 이다. 카메라, 노트북 등등
		var category=$(":input[name=category]:checked").val();
		alert(category+"을 추가 하셨습니다.");
		if(category==undefined) {
			alert("카테고리를 추가 하세요~");
			return;
		}
		$.ajax({
			type : "POST",
			url : "auth_addCategory.do",
			data : $("#addCategoryForm").serialize()+"&member_id=${sessionScope.mvo.member_id}",
			dataType : "JSON",
			success : function(result) {
				if(result.exception!=null) {
					alert("통신실패 퐁당~" +" "+ result.exception);
				}else{
					var newInfo="";
					//삭제 아이콘을 하나더 추가 해준다. 12월 11일
					/* var deleteComp="<a><input type='button' value='삭제' id='deleteCateroryBtn'></a>"; */
					 var deleteComp="<a><input type='radio' name='category' value='${clist.category_id}'></a>";
					$.each(result, function(index,sh) {
						//링크로 만들어 준다. 12월 11일
						newInfo+="<tr><td>"+deleteComp+"</td>"
						newInfo+="<td><a href='#'>"+sh.category+"</td>"
						newInfo+="<td>"+sh.category_id+"</td>"
						newInfo+="<td>"+sh.member_id+"</td></tr>"
						/* newInfo+="<td>"+deleteComp+"</td></tr>" */
					});
					$("#CategoryView").html(newInfo);
				} //else
			} //success
		}); //ajax

		}); //#addBtn
		
		//라디오 버튼 형식의 카테고리 삭제(작성중....)
		$("#deleteCateroryBtn").click(function() {
		//$("#deleteCateroryBtn").on("click", "#deleteCateroryBtn", function(){
			var category_id=$(":input[name=category_id]:checked").val();
			//카테고리 삭제시 confirm 확인창을 한번 띄워준다. 12월 11일 미완~~
			if(confirm("선택한 카테고리에 포함된 상품까지 모두 삭제 됩니다. 삭제 하시겠습니까?")==true){
				$.ajax({
					type : "POST",
					url : "auth_deleteProductListAndCategory.do",
					data : $("#deleteCategoryForm").serialize()+"&member_id=${sessionScope.mvo.member_id}",
					dataType : "JSON",
					success : function(result) {
						if(result.exception!=null) {
							alert(result.exception);
						}else{
							var newInfo="";
							/* var deleteComp="<a><input type='button' value='삭제' id='deleteCateroryBtn'></a>"; */
							 var deleteComp="<a><input type='radio' name='category' value='${clist.category_id}'></a>";
							$.each(result, function(index,sh) {
								newInfo+="<tr><td>"+deleteComp+"</td>"
								newInfo+="<td><a href='#'>"+sh.category+"</td>"
								newInfo+="<td>"+sh.category_id+"</td>"
								newInfo+="<td>"+sh.member_id+"</td></tr>"
								/* newInfo+="<td>"+deleteComp+"</td></tr>" */
							});
						$("#CategoryView").html(newInfo);
						}
				} //success
			}); //ajax
			}else{
				return false;
			} //confirm else
		}); //#deleteCateroryBtn click
		
		//탭 형식의 카테고리 뷰 (작성중....) 라디오 형식의 카테고리 뷰와 동일 하지만 
		//$.each(result, function(index,sh) {} 부분이 다르다.
		$("#tap_addBtn").click(function() {
			var category=$(":input[name=category]:checked").val();
			alert(category+"을 추가 하셨습니다.");
			if(category==undefined) {
				alert("카테고리를 추가 하세요~");
				return;
			}
			$.ajax({
				type : "POST",
				url : "auth_addCategory.do",
				//String categoty
				data : $("#tap_addCategoryForm").serialize()+"&member_id=${sessionScope.mvo.member_id}",
				dataType : "JSON",
				success : function(result) {
					if(result.exception!=null) {
						alert("ok"+result.exception);
					}else{
						alert("aa");
						
						$("#tapCategoryView").html(newInfo);
					} //else
				} //success
			}); //ajax

			}); //#addBtn
			
		
		
		//탭 형식의 카테고리 삭제
		$("#tapDeleteCateroryBtn").click(function() {
			//$("#deleteCateroryBtn").on("click", "#deleteCateroryBtn", function(){
				//val() 값은 숫자이다. 112 143 등등
				var category=$(":input[name=category]:checked").val();
				alert("삭제클릭");
				//카테고리 삭제시 confirm 확인창을 한번 띄워준다. 12월 11일 미완~~
				if(confirm("선택한 카테고리에 포함된 상품까지 모두 삭제 됩니다. 삭제 하시겠습니까?")==true){
					alert("진짜로 지운데이~");
					$.ajax({
						type : "POST",
						url : "auth_deleteProductListAndCategory.do",
						data : $("#deleteCategoryForm").serialize()+"&member_id=${sessionScope.mvo.member_id}",
						dataType : "JSON",
						success : function(result) {
							alert("통신실패" );	
							if(result.exception!=null) {
								alert("통신실패 퐁당~" +" "+ result.exception);
							}else{
								var newInfo="";
								/* var deleteComp="<a><input type='button' value='삭제' id='deleteCateroryBtn'></a>"; */
								 var deleteComp="<a><input type='radio' name='category' value='${clist.category_id}'></a>";
								$.each(result, function(index,sh) {
									newInfo+="<tr><td>"+deleteComp+"</td>"
									newInfo+="<td><a href='#'>"+sh.category+"</td>"
									newInfo+="<td>"+sh.category_id+"</td>"
									newInfo+="<td>"+sh.member_id+"</td></tr>"
									/* newInfo+="<td>"+deleteComp+"</td></tr>" */
								});
							$("#CategoryView").html(newInfo);
							}
					} //success
				}); //ajax
				}else{
					return false;
				} //confirm else
			}); //#deleteCateroryBtn click
	
		//카테고리명 수정(작성중....)
		$("#updateCateroryBtn").click(function() {
				var category=$(":input[name=category]:checked").val();
				if(confirm("선택한 카테고리명을 수정합니다!")==true){
					$.ajax({
						type : "POST",
						url : ".do",
						data : $("#updateCategoryForm").serialize()+"&member_id=${sessionScope.mvo.member_id}",
						dataType : "JSON",
						success : function(result) {
							if(result.exception!=null) {
								alert("통신실패 퐁당~" +" "+ result.exception);
							//수정된 카테고리명을 ajax로 보여준다. 공통 코드.	
							}else{
								var newInfo="";
							    var deleteComp="<a><input type='radio' name='category' value='${clist.category_id}'></a>";
								$.each(result, function(index,sh) {
									newInfo+="<tr><td>"+deleteComp+"</td>"
									newInfo+="<td><a href='#'>"+sh.category+"</td>"
									newInfo+="<td>"+sh.category_id+"</td>"
									newInfo+="<td>"+sh.member_id+"</td></tr>"
								});
							$("#CategoryView").html(newInfo);
							}
					} //success
				}); //ajax
				}else{
					return false;
				} //confirm else
			}); //#deleteCateroryBtn click
		
	}); //ready
	/* <input type="hidden" name="member_id" value="${sessionScope.mvo.member_id}"> */
	
</script>

<div class="col-md-8">

<h4>카테고리 만들기</h4>
	<form id="addCategoryForm">
	<c:forEach items="${requestScope.mainCategoryList }" var="mainCategoryList">
	<tr>
	<td><input type="radio" name="category" value="${mainCategoryList.category}">${mainCategoryList.category}</td>
	</tr>
	</c:forEach>
	<input type="button" value="추가하기" id="addBtn">
	<%-- <c:choose> --%>
	<%-- <c:when test="${sessionScope.mvo==null }"> --%>
	</form>
	<%-- </c:when> --%>
	<%-- <c:otherwise> --%>
	
	<form id="deleteCategoryForm">
	<table class="table">
		<thead>
		<tr>
		<td>선택</td>
		<td>카테고리</td>
		<td>카테고리ID</td>
		<td>회원ID</td>
		<!-- <td>deleteComp</td> -->
		</tr>
		<hr>
		<h4>${mvo.member_id }님 카테고리 보기</h4>
		</thead>
		<tbody id="CategoryView">
		<!--최초 페이지 시작시 DB카테고리를 불러와 표시한다. -->
		<c:forEach items="${requestScope.memberCategoryList }" var="clist">
		<tr>
		<td><a><input type="radio" name="category_id" value="${clist.category_id}"></a></td>
		<td><a href='#'>${clist.category}</a></td>
		<td>${clist.category_id}</td>
		<td>${sessionScope.mvo.member_id}</td>
		</tr>
		<!--ajax방식이 아닌 페이지 진입 시 삭제버튼을 추가 한다. -->
		<!-- <td><a><input type="button" value="삭제" id="deleteCateroryBtn"></a></td> -->
		</c:forEach>
		</tbody>
	</table>
	
	<h4>선택한 카테고리 지우기</h4>
	<input type="button" value="삭제" id="deleteCateroryBtn">
	</form>
	
	<hr>
	<%-- </c:otherwise> --%>
	<%-- </c:choose> --%>
	
	<h4>선택한 카테고리 수정하기</h4>
	<input type="button" value="수정" id="updateCateroryBtn">
	<!-- update member set address='성남' where id='jdbc';
	--패스워드가 1234이고 이름이 아이유인 회원의 주소를 강남으로 수정 -->
 
  <h4>탭형식의 카테고리 뷰</h4>
 
  <ul class="nav nav-tabs">
  <c:forEach items="${requestScope.memberCategoryList }" var="clist">
  <li role="presentation" class="active" value="${clist.category_id}"><a href="#">${clist.category}</a></li>
  </c:forEach>
  
  <!--추가하기 드롭다운-->
	<li role="presentation" class="dropup" id="tap_addCategoryForm">
	<a class="dropdown-toggle" data-toggle="dropdown" role="button1" aria-expanded="false">추가하기<span class="caret"></span></a> 
		<ul class="dropdown-menu" role="menu">
    		<!--forEach 반복문-->
    		<c:forEach items="${requestScope.mainCategoryList }" var="mainCategoryList">
   			<li role="presentation">
     		<!--라디오 버튼-->
    		<a role="menuitem" tabindex="-1"><input type="radio" name="category" value="${mainCategoryList.category}">
    		&nbsp;${mainCategoryList.category}</a>
    		</li>
    		</c:forEach>
    		
    		<!--구분선-->
    		<li role="presentation" class="divider"></li>
    		<li role="presentation"><a role="menuitem" tabindex="-1" id="tap_addBtn">카테고리 추가하기</a></li>
    	</ul>
	</li>
 
  <!--삭제하기 드롭다운-->
	<li role="presentation" class="dropup" id="tap_deleteCategoryForm">
	<a class="dropdown-toggle" data-toggle="dropdown" role="button2" aria-expanded="false">삭제하기 <span class="caret"></span></a>
  		<ul class="dropdown-menu" role="menu">
  			<!--forEach 반복문-->
    		<c:forEach items="${requestScope.memberCategoryList}" var="clist">
    		<li role="presentation" value="${clist.category}">
    		<!--배지 삽입!  product 갯수 조회 후 입력 -->
    		<span class="badge">"${requestScope.productCountNumber}"</span>
    		<!--라디오 버튼-->
    		<a role="menuitem" tabindex="-1" href="#"><input type="radio" name="category3" value="${clist.category_id}">
    		&nbsp;${clist.category}</a>
   			 </li>
   			 </c:forEach>
    		<!--구분선-->
   			<li role="presentation" class="divider"></li>
    		<li role="presentation"><a role="menuitem" tabindex="-1" href="#">내 카테고리 삭제하기</a></li>
    	</ul>
	</li>
 </ul>
 
<hr>

	<div align="right">
		<a href="auth_beforeGoingRegistProduct.do?category_id=${requestScope.category_id}">
		<img src="${initParam.root}img/write_btn.jpg" border="0" width="100"></a>
	</div>
	<br><br>
	<c:forEach items="${requestScope.pvoList}" var="list" begin="0" end="10">
		<div class="col-md-6">
			<div class="thumbnail">
				<a href="auth_hit.do?product_id=${list.product_id}"> <img src="img/no_image.png" class="img-responsive"></a>
				<div class="caption" align="center">
					<h3>${list.product_name}</h3>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<!-- 리스트에 순차적으로 접근하는 forEace문 -->
<%-- <tr>
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
</tr> --%>

