<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function(){	
		
		$.ajax({
			type : "POST",
			url : "getMemberCategoryList.do",
			data : "member_id=${sessionScope.mvo.member_id}",
			dataType : "JSON",
			success : function(result) {
				
				var newInfo="";
				
				if(result.length==0) {
					$("#tab_categoryView").html("<h4>카테고리를 추가해주세요</h4>");
					return;
				}
				
				$.each(result, function(i, data) {
					newInfo += "<li role='presentation' class='active' value=''>"+
					"<a href='auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory="+data.category_id+"'>"+(i+1)+". "+data.category+"</a></li>";
				});
				
				$("#tab_categoryView").html(newInfo);
				
				var newInfoForDeleteDropdown="";
				
				$.each(result, function(i, data) {
					newInfoForDeleteDropdown += "<li role='presentation'>"+
					    		// 라디오 버튼
					    		"<a href='' role='menuitem' tabindex='-1'><input type='radio' name='tap_category_delete' value='"+data.category_id+"'>"+
					    		" "+(i+1)+". "+data.category+" "+
					    		// 배지 삽입!  product 갯수 조회 후 입력
					    		"<span class='badge'>"+data.productCountNumber+"</span></a>"+
				   				"</li>"
				});
				
				newInfoForDeleteDropdown += "<li role='presentation' class='divider'></li>"+
	    					"<li role='presentation'><a role='menuitem' tabindex='-1' id='tap_deleteCategoryBtn' href='#'>내 카테고리 삭제하기</a></li>"
				
				$("#deleteDropDown").html(newInfoForDeleteDropdown);
				
			} //success
		}); //ajax
		
		$("#tap_addCategoryBtn").click(function() {

			if($("#tab_categoryView li").length>=5) {
				alert("카테고리는 5개까지 추가 가능합니다.");
				return;
			}
			
			var category=$(":radio[name=tap_category_add]:checked").val();
			
			if(category==undefined) {
				alert("추가할 카테고리를 선택해주세요");
				return;
			}
			
			$.ajax({
				type : "POST",
				url : "auth_addCategory.do",
				data : "category="+category+"&member_id=${sessionScope.mvo.member_id}",
				dataType : "JSON",
				success : function(result) {
					
					var newInfo="";
					
					$.each(result, function(i, data) {
						newInfo += "<li role='presentation' class='active' value=''>"+
						"<a href='auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory="+data.category_id+"'>"+(i+1)+". "+data.category+"</a></li>";
					});
					
					$("#tab_categoryView").html(newInfo);
					
					var newInfoForDeleteDropdown="";
					
					$.each(result, function(i, data) {
						newInfoForDeleteDropdown += "<li role='presentation'>"+
						    		// 라디오 버튼
						    		"<a role='menuitem' tabindex='-1'><input type='radio' name='tap_category_delete' value='"+data.category_id+"'>"+
						    		" "+(i+1)+". "+data.category+" "+
						    		// 배지 삽입!  product 갯수 조회 후 입력
						    		"<span class='badge'>"+data.productCountNumber+"</span></a>"+
					   				"</li>"
					});
					
					newInfoForDeleteDropdown += "<li role='presentation' class='divider'></li>"+
		    					"<li role='presentation'><a role='menuitem' tabindex='-1' id='tap_deleteCategoryBtn' href='#'>내 카테고리 삭제하기</a></li>"
					
					$("#deleteDropDown").html(newInfoForDeleteDropdown);
					
					alert("카테고리가 추가되었습니다");
				} //success
			}); //ajax
			
		}); //click

		
		
		$("#deleteDropDown").on("click", "#tap_deleteCategoryBtn", function() {
			
			var category_id=$(":radio[name=tap_category_delete]:checked").val();
			
			if(category_id==undefined) {
				alert("삭제할 카테고리를 선택해주세요");
				return;
			}
			
			if(!confirm("카테고리에 포함된 모든 하위 내용까지 삭제하시겠습니까?")) {
				return;
			}
			
			$.ajax({
				type : "POST",
				url : "auth_deleteProductListAndCategory.do",
				data : "category_id="+category_id+"&member_id=${sessionScope.mvo.member_id}",
				dataType : "JSON",
				success : function(result) {
					
					if(result.length==0) {
						$("#tab_categoryView").html("<h4>카테고리를 추가해주세요</h4>");
						return;
					}
					
					var newInfo="";
					
					$.each(result, function(i, data) {
						newInfo += "<li role='presentation' class='active' value=''>"+
						"<a href='auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory="+data.category_id+"'>"+(i+1)+". "+data.category+"</a></li>";
					});
					
					$("#tab_categoryView").html(newInfo);
					
					
					var newInfoForDeleteDropdown="";
					
					$.each(result, function(i, data) {
						newInfoForDeleteDropdown += "<li role='presentation'>"+
						    		// 라디오 버튼
						    		"<a href='' role='menuitem' tabindex='-1'><input type='radio' name='tap_category_delete' value='"+data.category_id+"'>"+
						    		" "+(i+1)+". "+data.category+" "+
						    		// 배지 삽입!  product 갯수 조회 후 입력
						    		"<span class='badge'>"+data.productCountNumber+"</span></a>"+
					   				"</li>"
					});
					
					newInfoForDeleteDropdown += "<li role='presentation' class='divider'></li>"+
		    					"<li role='presentation'><a role='menuitem' tabindex='-1' id='tap_deleteCategoryBtn' href='#'>내 카테고리 삭제하기</a></li>"
					
					$("#deleteDropDown").html(newInfoForDeleteDropdown);
					
					alert("카테고리와 하위 내용이 모두 삭제되었습니다");
					
				} //success
			}); //ajax
				
		}); //click
		
		
		$("#tab_categoryView").on("mouseover","a", function(){
    	 	$(this).css("background-color","#e6e6e6");
		}).on("mouseout","a", function(){
			$(this).css("background-color","white");
		});//on
			

		$(".productCard").hover(function(){
			$(this).css("border","solid 2px #ff1a1a");
		}, function(){
			$(this).css("border","solid 2px #e6e6e6");
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
		
		for(var i=0;i<8;i++) {
			$(".lowestPrice").eq(i).text(AddComma($(".lowestPrice").eq(i).text()));
		}
		
		var div = $(".thumbnailImgDiv"); // 이미지를 감싸는 div
		var img = $(".thumbnailImg"); // 이미지
		var divAspect = 200 / 285; // div의 가로세로비는 알고 있는 값이다
		var imgAspect = img.height / img.width;
		 
		if (imgAspect <= divAspect) {
		    // 이미지가 div보다 납작한 경우 세로를 div에 맞추고 가로는 잘라낸다
		    var imgWidthActual = div.offsetHeight / imgAspect;
		    var imgWidthToBe = div.offsetHeight / divAspect;
		    var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2);
		    img.style.cssText = 'width: auto; height: 100%; margin-left: 0;'+ marginLeft + 'px;';
		} else {
		    // 이미지가 div보다 길쭉한 경우 가로를 div에 맞추고 세로를 잘라낸다
		    img.style.cssText = 'width: 100%; height: auto; margin-left: 0;';
		}
		
	}); //ready
	
</script>

  
<div class="col-md-8">
 
	<div class="col-sm-9">
		<ol class="nav nav-tabs" id="tab_categoryView">
		</ol>
	</div>
 
	<div class="col-sm-3" align="right">
		<ol class="nav nav-tabs">
		  <!--추가하기 드롭다운-->
			<li role="presentation" class="dropup" id="tap_addCategoryForm">
				<a class="dropdown-toggle" data-toggle="dropdown" role="button1" aria-expanded="false">추가<span class="caret"></span></a> 
					<ul class="dropdown-menu" role="menu">
		    		<!--forEach 반복문-->
		    		<c:forEach items="${requestScope.mainCategoryList}" var="mainCategoryList">
			   			<li role="presentation">
			     		<!--라디오 버튼-->
			    			<a role="menuitem" tabindex="-1">
			    				<input type="radio" name="tap_category_add" value="${mainCategoryList.category}">
			    				${mainCategoryList.category}
			    			</a>
			    		</li>
		    		</c:forEach>
		    		
		    		<!--구분선-->
		    		<li role="presentation" class="divider"></li>
		    		<li role="presentation"><a role="menuitem" tabindex="-1" id="tap_addCategoryBtn" href="#">카테고리 추가하기</a></li>
		    	</ul>
			</li>
		 
		  <!--삭제하기 드롭다운-->
			<li role="presentation" class="dropup" id="tap_deleteCategoryForm">
			<a class="dropdown-toggle" data-toggle="dropdown" role="button2" aria-expanded="false">삭제<span class="caret"></span></a>
		  		<ul class="dropdown-menu" role="menu" id="deleteDropDown">
		    	</ul>
			</li>
		 </ol>
		<br>
	</div>
	
	<div class="col-md-12" align="center" style="border: solid 2px #e6e6e6;">
		<h4><a href="auth_beforeGoingRegistProduct.do?category_id=${requestScope.category_id}">
			쇼핑 노트 쓰기
		</a></h4>
	</div>
	<hr>

	<c:forEach items="${requestScope.pvoList}" var="list" begin="0" end="7" varStatus="status">
		<div class="col-md-6">
			<div class="thumbnail productCard" style="border: solid 2px #e6e6e6; box-sizing : border-box;">
				<div class="col-sm-12 thumbnailImgDiv" align="center" style="border: solid 1px #e6e6e6; width: 100%; height: 200px; overflow: hidden;">
					<a href="auth_hit.do?product_id=${list.product_id}">
						<img src="${list.thumbnail_link}" class="img-responsive thumbnailImg" width='285' height='200'>
					</a>
				</div>
				<div>
					<div class="caption" align="left">
						<h4>${list.product_name}</h4>
					</div>
					<div align="right">
						최저가 : <font class="lowestPrice" size="4" face="윤고딕320">${list.lowestPrice}</font> 원
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
