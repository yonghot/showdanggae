<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<script type="text/javascript">

	$(document).ready(function(){
		$("#linkPlusBtn").hover(function(){
			
		}, function(){
			
		});
		
		function AddComma(data_value) {
			 
		    var txtNumber = '' + data_value;    // 입력된 값을 문자열 변수에 저장합니다.
		 
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
		
		$("#linkPlusBtn").click(function(){
			
			if($(":text[name=link]").val()=="") {
				alert("판매자 링크를 입력해주세요");
				return;
			} else if($(":text[name=price]").val()=="") {
				alert("가격을 입력해주세요");
				return;
			} else if(isNaN($(":text[name=price]").val())) {
				alert("가격은 숫자로 입력해주세요");
				return;
			}
			
			if($(":text[name=link]").val().length>=50) {
				var shortenLink = $(":text[name=link]").val().substring(0, 50) + "...";;
			}
			
			$("#linkView").append(
				"<tr>"+
				"<td><img src='img/link_icon.png' width='15'></td>"+
				"<td><a href="+$(":text[name=link]").val()+">"+shortenLink+"</a><td>"+
				"<td>"+AddComma($(":text[name=price]").val())+" 원"+"<td><tr>"
			);
			
		});
		
		
		$("#linkView").on("change","tbody",function(){

			for(var i=0;i<$("tbody").length;i++) {
			}
		}); //on
		
		
		$("button[data-toggle=popover]").popover().click(function(e) {
            e.preventDefault();
        });
	});

</script>


<div class="col-md-8">
	<div class="col-sm-9">
		<h3 contenteditable="true">상품 등록하기</h3>
		<hr>
	</div>
	<div class="col-sm-3" align="right">
		<div class="btn-group btn-group-xs">
			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" contenteditable="true">카테고리 선택<br>
			<span class="fa fa-caret-down"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" contenteditable="true">Action</a></li>
			</ul>
		</div>
	</div>
	<div class="col-md-5">
		<div class="thumbnail">
			<img src="http://cfile24.uf.tistory.com/image/2567C64151A2AAB1352FF7"/>
		</div>
		
	</div>
	<div class="col-md-7">
		<form class="form-horizontal" role="form" action="">
			<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
			<input type="hidden" class="form-control" name="category_id" value="${requestScope.category_id}">
			<div class="form-group">
				<div class="col-sm-3">
					<label for="product_id" class="control-label">제품명</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="product_name" placeholder="제품명을 입력해주세요">
					<hr>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">판매 링크</label>
				</div>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="link" placeholder="링크를 복사해주세요">
					<br>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">가격</label>
				</div>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="price" placeholder="판매자의 가격을 입력해주세요">
					<br>
				</div>
				<button type="button" class="btn btn-default" id="linkPlusBtn"><img src="img/plus_icon.png" width="20"
				data-toggle="popover" title="판매자 링크를 추가합니다" data-placement="right"/></button>
				<div class="checkbox col-sm-4">
					<label><input type="checkbox">비공개</label>
				</div>
			</div>
		</form>
	</div>
	<table class="table table-hover table-condensed">
		<thead>
			<tr></tr>
		</thead>
		<tbody id="linkView" valign="middle"></tbody>
	</table>
	<table class="table">
		<tbody>
			<tr>
				<td>
					<div class="col-sm-5">
						<table class="table table-hover table-condensed">
							<thead>
								<tr>
									<td>&nbsp</td><td>평가 항목</td><td>점수</td>
								</tr>
							</thead>
							<tbody id="linkView" valign="middle">
								<tr>
									<td><img src="img/plus_icon.png" width="20"/></td>
									<td>
										<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu4">
										  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
										  <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
										  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
										</ul>
									</td>
									<td>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="link">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-sm-7">
						<textarea cols="57" rows="5" name="content" placeholder="메모를 입력해주세요"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<input type="submit" value="상품 등록" id="subBtn" class="btn btn-info btn-md">
					<input type="button" value="취소" id="noticeWrBtn" class="btn btn-info btn-md">
				</td>
			</tr>
		</tbody>
	</table>
</div>

