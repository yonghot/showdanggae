<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">     

	$(document).ready(function(){
		
		$("#cancelRegist").click(function(){
			location.href="auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=${requestScope.category_id}";
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
		
		var linkCount = 0;
		$("#linkPlusBtn").click(function(){
			
			if($(":text[name=inputLink]").val()=="") {
				alert("판매자 링크를 입력해주세요");
				return;
			} else if($(":text[name=inputPrice]").val()=="") {
				alert("가격을 입력해주세요");
				return;
			} else if(isNaN($(":text[name=inputPrice]").val())) {
				alert("가격은 숫자로 입력해주세요");
				return;
			}
			
			var shortenLink = $(":text[name=inputLink]").val();
			if($(":text[name=inputLink]").val().length>=65) {
				shortenLink = $(":text[name=inputLink]").val().substring(0, 65) + "...";
			}
			
			$("#linkView").append(
				"<tr>"+
				"<td width='10'><img src='img/link_icon.png' width='15' title='항목을 제거합니다.'></td>"+
				"<td width='250'><a href="+$(":text[name=inputLink]").val()+">"+shortenLink+"</a></td>"+
				"<td width='100' align='right'>"+AddComma($(":text[name=inputPrice]").val())+" 원"+"</td>"+
				"<td width='10'><img src='img/minus_icon.png' width='25' id='deleteImg'></td></tr>"+
				"<input type='hidden' class='link"+linkCount+"' name='slvoList["+linkCount+"].link' value='"+$(":text[name=inputLink]").val()+"'>"+
				"<input type='hidden' class='link"+linkCount+"' name='slvoList["+linkCount+"].price' value='"+$(":text[name=inputPrice]").val()+"'>"+
				"<input type='hidden' class='link"+linkCount+"' name='slvoList["+linkCount+"].category_id' value='${requestScope.category_id}'>"
			);
			
			$(":text[name=inputLink]").val("");
			$(":text[name=inputPrice]").val("");
			$(":text[name=inputLink]").focus();
			$(":text[name=inputPrice]").focus();
			
			linkCount++;
		});
		
		$("#linkView").on("click","img",function(){
			
			if(confirm("항목을 삭제하시겠습니까?")) {
				linkCount--;
				$(".link"+$(this).parent().parent().index()/4).remove();
				$(this).parent().parent().remove();
			}
		}); //on
		
		
		var itemList = "";
		for(var i=0;i<$("#itemList :input").length;i++){
			itemList += "<option>"+$("#itemList :input[name="+i+"]").val()+"</option>"
		}
		
		var itemCount = 0;
		$("#itemPlusBtn").click(function(){
			
			if($("#itemView tr").length>=3) {
				alert("평가 항목은 3개까지 등록 가능합니다.");
				return;
			}
			
			$("#itemView").append(
				"<tr>"+
				"<td><img src='img/minus_icon.png' width='25'></td>"+
				"<td><select name='evoList["+itemCount+"].item'><option>------</option>"+itemList+"</select></td>"+
				"<td><input type='text' class='form-control' name='evoList["+itemCount+"].item_point' size='1' placeholder='10점 만점에'></td>"+
				"</tr>"+
				"<input type='hidden' class='item"+itemCount+"' name='evoList["+itemCount+"].category_id' value='${requestScope.category_id}'>"
			);
			
			itemCount++;
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
				itemCount--;
			}
		}); //on
		
		
		/* $("#uploadBtn").click(function(event){

			var uploadFormData = $("#uploadForm");
			uploadFormData.method="post";
			uploadFormData.enctype="multipart/form-data";
			
			var uploadFormDataForAjax = new FormData(uploadFormData);
			
			alert(uploadFormDataForAjax);
			
            $.ajax({
                url: "fileupload.do",
                type: "post",
                data: uploadFormDataForAjax,
                //dataType: "text",
                //cache: false,
                processData: false,
                contentType: false,
                success: function(data, textStatus, jqXHR) {
                   alert(data);
                }, error: function(jqXHR, textStatus, errorThrown) {}
            });
            
		}); */
		
		$("#thumbnailImg").click(function(){
			$("#thumbnailImgView").toggle();
		});
		
		$("#prepareThumbnailImg").click(function(){
			$("#thumbnailImg").html("<img src="+$("#thumbnailImgView :input[name=thumbnailImgLinkInput]").val()+" width='285' height='200'>");
			$("#hiddenGroup").append(
				"<input type='hidden' class='form-control' name='thumbnail_link' value='"+$("#thumbnailImgView :input[name=thumbnailImgLinkInput]").val()+"'>"
			);
		});
		
		
		$("#registProductBtn").click(function(){
			
			if($(":input[name=product_name]").val()=="") {
				alert("제품명을 입력해주세요!");
				return false;
			} else if($(":input[name=price]").val()=="") {
				$(":input[name=price]").val(0);
			} /* else if($(":input[name=price]").val()=="") {
				
			} */ else {
				$("#registForm").submit();
			}
		});
	});

</script>

<span id="itemList">
	<c:forEach items="${requestScope.itemList}" varStatus="status" var="i">
		<input type="hidden" name="${status.index}" value="${i}">
	</c:forEach>
</span>

<div class="col-md-8" style="background-color: #ffffcc;">
	<div class="col-sm-12">
		<h3 contenteditable="true">상품 등록하기</h3>
		<hr>
	</div>
	<div class="col-md-5">
		<a href="#" id="thumbnailImg" title="이미지를 등록합니다" style="text-decoration:none">
			<img src="img/no_image.png" width="285" height="200">
		</a>
		<br><br>
		<div id="thumbnailImgView" style="display:none">
			<div class='input-group'>
				<input type='text' class='form-control' name='thumbnailImgLinkInput' placeholder='이미지 링크를 넣어주세요' aria-describedby='basic-addon1'>
				<span class='input-group-btn'><a class='btn btn-success' id="prepareThumbnailImg">등록</a></span>
			</div>
		</div>
	    <%-- <form id="uploadForm" action="fileupload.do" enctype="multipart/form-data" method="post">
			<input type="hidden" name="member_id" value="${sessionScope.mvo.member_id}"><br>
			<input type="file" name="thumbnailImgFile"><br>
			<input type="submit" value="파일 업로드" id="uploadBtn">
		</form> --%>
	</div>
	<form class="form-horizontal" role="form" action="auth_registProduct.do" id="registForm" method="post">
		<div class="col-md-7">
			<span id="hiddenGroup">
				<input type="hidden" class="form-control" name="member_id" value="${sessionScope.mvo.member_id}">
				<input type="hidden" class="form-control" name="category_id" value="${requestScope.category_id}">
			</span>
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
					<input type="text" class="form-control" name="inputLink" placeholder="링크를 복사해주세요">
					<br>
				</div>
				<div class="col-sm-3">
					<label for="inputEmail3" class="control-label">가격</label>
				</div>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="inputPrice" placeholder="판매자의 가격을 입력해주세요">
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
