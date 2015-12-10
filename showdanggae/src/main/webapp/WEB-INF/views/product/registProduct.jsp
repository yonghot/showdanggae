<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">     

	$(document).ready(function(){
		
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
			
			var shortenLink = $(":text[name=link]").val();
			if($(":text[name=link]").val().length>=65) {
				shortenLink = $(":text[name=link]").val().substring(0, 65) + "...";
			}
			
			$("#linkView").append(
				"<tr>"+
				"<td><img src='img/link_icon.png' width='15'></td>"+
				"<td><a href="+$(":text[name=link]").val()+">"+shortenLink+"</a><td>"+
				"<td>"+AddComma($(":text[name=price]").val())+" 원"+"<td><tr>"
			);
			
			$(":text[name=link]").val("");
			$(":text[name=price]").val("");
			$(":text[name=link]").focus();
			$(":text[name=price]").focus();
		});
		
		
		var itemList = "";
		for(var i=0;i<$("#itemList :input").length;i++){
			itemList += "<option>"+$("#itemList :input[name="+i+"]").val()+"</option>"
		}
		
		
		$("#itemPlusBtn").click(function(){
			
			if($("#itemView tr").length>=3) {
				alert("평가 항목은 3개까지 등록 가능합니다.")
				return;
			}
			
			$("#itemView").append(
				"<tr>"+
				"<td><img src='img/minus_icon.png' width='25'></td>"+
				"<td><select><option>------</option>"+itemList+"</select></td>"+
				"<td>"+"<input type='text' class='form-control' name='item_point' size='1' placeholder='10점 만점에'>"+"</td>"+
				"</tr>"
			);
		});
		
		
		$("#itemView").on("keyup",":input[type=text]",function(){
			if($("#itemView :input[type=text]").val()>10) {
				alert("10점 만점입니다!");
				$(this).val("");
			}
		}); //on
		
		$("#itemView").on("click","img",function(){
			if(confirm("항목을 삭제하시겠습니까?")) {
				$(this).parent().parent().remove();
			}
		}); //on
		
		$("#uploadBtn").click(function(){
			 var data = new FormData();
            $.each($('#uploadForm')[0].files, function(i, file) {          
                data.append('file-' + i, file);
            });
             
            $.ajax({
                url: 'fileupload.do',
                type: "post",
                dataType: "text",
                data: data,
                // cache: false,
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                    alert(data);
                }, error: function(jqXHR, textStatus, errorThrown) {}
            });
		});
		
		
		$("#registProductBtn").click(function(){
			
			if($(":input[name=product_name]").val()=="") {
				alert("제품명을 입력해주세요!");
				return false;
			} else if($(":input[name=price]").val()=="") {
				$(":input[name=price]").val(0);
			} else {
				$("#registForm").submit();
			}
		});
		
	});

</script>

<span id="itemList">
	<c:forEach items="${requestScope.itemList}" varStatus="status" var="i">
		<input type="hidden" name="${status.count-1}" value="${i}">
	</c:forEach>
</span>

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
		<a href="fileupload.do" class="thumbnail">
			<c:choose>
				<c:when test="">
					<img src="img/no_image.png" width="285">
				</c:when>
				<c:otherwise>
					<img src="img/no_image.png" width="285">
				</c:otherwise>
			</c:choose>
		</a>
	    <form id="uploadForm" action="${initParam.root}fileupload.do" enctype="multipart/form-data" method="post" class="thumbnail">
			<input type="file" name="file[0]"><br>
			<input type="button" value="파일 업로드" id="uploadBtn"><br>
		</form>
	</div>
	<form class="form-horizontal" role="form" action="auth_registProduct.do" id="registForm">
		<div class="col-md-7">
			<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
			<input type="hidden" class="form-control" name="category_id" value="${requestScope.category_id}">
			<div class="form-group">
				<div class="col-sm-3">
					<label for="product_name" class="control-label">제품명</label>
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
					<label><input type="checkbox" name="visiblity" value="1">비공개</label>
				</div>
			</div>
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
							<table class="table table-hover table-condensed table-bordered">
								<thead align="center">
									<tr>
										<td>
											<button type="button" class="btn btn-default btn-xs" id="itemPlusBtn" title="평가항목을 추가합니다"><img src="img/plus_icon.png" width="20"/></button>
										</td>
										<td>평가 항목</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 수&nbsp;&nbsp;&nbsp;</td>
									</tr>
								</thead>
								<tbody id="itemView" align="center" valign="middle">
								</tbody>
							</table>
						</div>
						<div class="col-sm-7">
							<textarea cols="57" rows="9" name="detail" placeholder="메모를 입력해주세요"></textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="상품 등록" id="registProductBtn" class="btn btn-info btn-md">
						<input type="button" value="취소" id="cancelRegist" class="btn btn-info btn-md">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>