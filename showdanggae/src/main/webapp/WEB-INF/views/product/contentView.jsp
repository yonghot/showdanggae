<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">     

	$(document).ready(function(){
		
		$("#moveToHomeBtn").click(function(){
			location.href="home.do";
		});
		$("#moveToMyProductListBtn").click(function(){
			location.href="auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=${requestScope.productInfo.pvo.category_id}";
		});
		$("#deleteProductBtn").click(function(){
			if(confirm("이 글을 삭제하시겠습니까?")) {
				location.href="deleteProduct.do?product_id=${requestScope.productInfo.pvo.product_id}";
			}
		});
		
		function AddComma(data_value) {
			
		    var txtNumber = "" + data_value;    // 입력된 값을 문자열 변수에 저장합니다.
		 
		    if (isNaN(txtNumber) || txtNumber == "") {    // 숫자 형태의 값이 정상적으로 입력되었는지 확인합니다.
		        return;
		    }
		    else {
		        var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');    // 정규식 형태 생성
		        var arrNumber = txtNumber.split('.');    // 입력받은 숫자를 . 기준으로 나눔. (정수부와 소수부분으로 분리)
		        arrNumber[0] += '.'; // 정수부 끝에 소수점 추가
		 
		        do {
		            arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2'); // 정수부에서 rxSplit 패턴과 일치하는 부분을 찾아 replace 처리
		        } while (rxSplit.test(arrNumber[0])); // 정규식 패턴 rxSplit 가 정수부 내에 있는지 확인하고 있다면 true 반환. 루프 반복.
		 
		        if (arrNumber.length > 1) { // txtNumber를 마침표(.)로 분리한 부분이 2개 이상이라면 (즉 소수점 부분도 있다면)
		            return arrNumber.join(''); // 배열을 그대로 합칩. (join 함수에 인자가 있으면 인자를 구분값으로 두고 합침)
		        }
		        else { // txtNumber 길이가 1이라면 정수부만 있다는 의미.
		            return arrNumber[0].split('.')[0]; // 위에서 정수부 끝에 붙여준 마침표(.)를 그대로 제거
		        }
		    }
		}
		
		for(var i=0;i<$("#linkView tr").length;i++) {
			
			var link = $("#linkView").children().eq(i).children().siblings().eq(1).children();
			var linkText = $("#linkView").children().eq(i).children().siblings().eq(1).children().text();
			var price = $("#linkView").children().eq(i).children().siblings().eq(2);
			
			if(link.text().length>=45) {
				linkText = linkText.substring(0, 45) + "...";
			}
			
			link.text(linkText);
			price.text(AddComma(price.text())+"원");
		}
				
	});

</script>

<div class="col-md-8" style="background-color: ffffb3;">
	<div class="col-sm-9">
		<h3 contenteditable="true">상품 보기</h3>
		<hr>
	</div>
	<div class="col-sm-3" align="right">
		<!-- 카테고리 표시 -->
	</div>
	<div class="col-md-5">
		<div class="thumbnail">
			<img src="${requestScope.productInfo.pvo.thumbnail_link}" width="285">
		</div>
	</div>
	<form class="form-horizontal" role="form" action="beforeGoingUpdateProduct.do" id="registForm">
		<input type="hidden" name="product_id" value="${requestScope.productInfo.pvo.product_id}">
		<div class="col-md-7">
			<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
			<div class="form-group">
				<div class="col-sm-12" align="center">
					<h4>${requestScope.productInfo.pvo.product_name}</h4>
					<hr>
				</div>
				<div class="col-sm-12">
					<table class="table table-hover table-condensed">
						<tbody valign="middle" id="linkView">
						 	<c:forEach items="${requestScope.productInfo.slvoList}" var="slvoList" varStatus="status">
								<tr>
									<td><img src='img/link_icon.png' width='15'></td>
									<td><a href="${slvoList.link}" class="link">${slvoList.link}</a></td>
									<td class="price">${slvoList.price}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<table class="table">
			<tbody>
				<tr>
					<td>
						<div class="col-sm-5">
							<table class="table table-hover table-condensed table-bordered">
									<thead align="center">
										<tr>
											<td>평가 항목</td><td>&nbsp;&nbsp;&nbsp;점 수&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</thead>
									<tbody align="center" valign="middle">
										<c:forEach items="${requestScope.productInfo.evoList}" var="evoList">
											<tr>
												<td>${evoList.item}</td><td>${evoList.item_point}</td>
											</tr>
										</c:forEach>
									</tbody>
							</table>
						</div>
						<div class="col-sm-7">
							<pre>${requestScope.productInfo.pvo.detail}</pre>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<c:choose>
							<c:when test="${sessionScope.mvo.member_id==requestScope.productInfo.pvo.member_id}">
								<button type="button" id="moveToMyProductListBtn" class="btn btn-info btn-md">목록</button>
								<button type="button" id="" class="btn btn-info btn-md">수정</button>
								<button type="button" id="deleteProductBtn" class="btn btn-info btn-md">삭제</button>
							</c:when>
							<c:otherwise>
								<button type="button" id="moveToHomeBtn" class="btn btn-info btn-md">홈으로</button>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
