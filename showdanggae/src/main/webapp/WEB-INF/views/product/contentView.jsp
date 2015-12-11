<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">     

	$(document).ready(function(){
		
		$("#moveToMyProductListBtn").click(function(){
			location.href="auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=${requestScope.productInfo.pvo.category_id}";
		});
		
		$("#updateProductBtn").click(function(){
			location.href="beforeGoingUpdateProduct.do";
		});
		
		function AddComma(data_value) {
			
		    var txtNumber = "" + data_value;    // 입력된 값을 문자열 변수에 저장합니다.
		 
		    if (isNaN(txtNumber) || txtNumber == "") {    // 숫자 형태의 값이 정상적으로 입력되었는지 확인합니다.
		        alert("숫자만 입력 하세요");
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
		
		shortenLink = $("#link").text();
		
		if($("#link").text().length>=50) {
			shortenLink = $("#link").text().substring(0, 50) + "...";
		}
		
		$("#link").text(shortenLink);
		$("#price").text(AddComma($("#price").text())+" 원");
				
	});

</script>

<div class="col-md-8">
	<div class="col-sm-9">
		<h3 contenteditable="true">상품 보기</h3>
		<hr>
	</div>
	<div class="col-sm-3" align="right">
		<!-- 카테고리 표시 -->
	</div>
	<div class="col-md-5">
		<div class="thumbnail">
			<c:choose>
				<c:when test="">
					<img src="img/no_image.png" width="285">
				</c:when>
				<c:otherwise>
					<img src="img/no_image.png" width="285">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<form class="form-horizontal" role="form" action="auth_registProduct.do" id="registForm">
		<div class="col-md-7">
			<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
			<div class="form-group">
				<div class="col-sm-12" align="center">
					<h4>${requestScope.productInfo.pvo.product_name}</h4>
					<hr>
				</div>
				<div class="col-sm-11">
					<table class="table table-hover table-condensed">
						<tbody valign="middle">
							<c:forEach items="${requestScope.productInfo.slvoList}" var="slvoList">
								<tr>
									<td id="link"><a href="${slvoList.link}">${slvoList.link}</a></td><td id="price">${slvoList.price}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="checkbox col-sm-4">
					<label><input type="checkbox" name="visiblity" value="1">비공개</label>
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
						<button type="button" id="moveToMyProductListBtn" class="btn btn-info btn-md">목록</button>
						<button type="button" id="updateProductBtn" class="btn btn-info btn-md">수정</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
