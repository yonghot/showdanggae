<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
  $(document).ready(function() {
	 
	 var totalProductNum = "${requestScope.pvoList.size()}";
	 var showedProductAtOnce = 4;
	 var index;
	 var noMoreProduct = false;
	 
	 for(var i=showedProductAtOnce;i<totalProductNum;i++) {
		 $(".col-md-6").siblings().eq(i).hide();
		 index = showedProductAtOnce;
	 }
	  
     //스크롤 이벤트 발생 시
     $(window).scroll(function() {
    	 
    	if(!noMoreProduct) {
	        var documentHeight = $(document).height();
	        var scrollHeight = $(window).height()+$(window).scrollTop();
	
	        if (documentHeight <= scrollHeight) { //그냥 = 으로 해서 계속 안됐었음.. 주의합시다
	        	
	        	for(var i=index;i<index+showedProductAtOnce;i++) {
	       			$(".col-md-6").siblings().eq(i).show();
	       	 	}
	       		index += showedProductAtOnce;
	       		
	       		if(index>=totalProductNum) {
			       	$("#main_row").append(
			       		"<div class='col-md-12'>"+
			       			"<div class='thumbnail productCard' align='center' style='border: solid 2px #e6e6e6; box-sizing : border-box;'>"+
			       				"<font size='3'>더이상 출력할 상품 목록이 없습니다.</font>"+
			       			"</div>"+
			       		"</div>"
			       	);
			       	noMoreProduct = true;
	       		}
	       	}
        	
           /* for(var i=0;i<8;i++) {
              $("#main_row").append(
            		  "<div class='col-md-6'>"+
                      "<div class='thumbnail productCard' style='border: solid 2px #e6e6e6; box-sizing : border-box;'>"+
                      	"<div class='col-sm-12 thumbnailImgDiv' align='center' style='border: solid 1px #e6e6e6; width: 100%; height: 200px; overflow: hidden;'>"+
                      		"<a href='auth_hit.do?product_id=${list.product_id}'>"+
 		                         "<img src='${list.thumbnail_link}' class='img-responsive thumbnailImg' width='285' height='200'>"+
 		                    "</a>"+
 		                "</div>"+
 		                "<div>"+
 	                         "<div class='caption' align='left'>"+ 
 		                        "<h4>${list.product_name}ㅋㅋㅋ</h4>"+
 		                     "</div>"+
 		                     "<div align='right'>"+
 		                     	"최저가 : <font class='lowestPrice' size='4' face='윤고딕320'>${list.lowestPrice}</font> 원"+
 		                  	 "</div>"+
 		                "</div>"+
 		             "</div>"+
 		          "</div>"	  
              );
           } */
        }
     });

     
     $(".productCard").hover(function(){
			$(this).css("border","solid 2px #ff1a1a");
		}, function(){
			$(this).css("border","solid 2px #e6e6e6");
		});
     
     $("#main_row").on("mouseover",".productCard", function(){
    	 	$(this).css("border","solid 2px #ff1a1a");
		}).on("mouseout",".productCard", function(){
			$(this).css("border","solid 2px #e6e6e6");
		});//on
		
		
		$(".categorySelectLink").click(function(){
			alert($(this).children().val()+" 카테고리에 속하는 제품만 출력됩니다.");
			//location.href="getAllProductListByCategory.do?sortBy="+$(this).children().val();
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
		
		for(var i=0;i<totalProductNum;i++) {
			$(".lowestPrice").eq(i).text(AddComma($(".lowestPrice").eq(i).text()));
		}
		
		
		
		for(var i=0;i<totalProductNum;i++) {
			var productName = $(".product_name").eq(i).text();
			var nameLimit = 25;
			if(productName.length>=nameLimit) {
				productName = productName.substring(0, nameLimit) + "...";
				$(".product_name").eq(i).text(productName);
			}
		}
		
  });
  
</script>


<div class="col-md-8">
	<div align="left">
		<button role="presentation" class="dropup" id="tap_addCategoryForm">
			<a class="dropdown-toggle" data-toggle="dropdown" role="button1" aria-expanded="false">카테고리별 보기 <span class="caret"></span></a> 
				<ul class="dropdown-menu" role="menu">
	    		<!--forEach 반복문-->
	    		<c:forEach items="${requestScope.mainCategoryList}" var="mainCategoryList">
		   			<li role="presentation">
		     		<!--라디오 버튼-->
		    			<a class="categorySelectLink" role="menuitem" tabindex="-1">
		    				<input type="radio" name="tap_category_add" value="${mainCategoryList.category}">
		    				${mainCategoryList.category}
		    			</a>
		    		</li>
	    		</c:forEach>
	    	</ul>
		</button>
	</div>
    <hr>
    <div class="row" id="main_row">
    	<c:forEach items="${requestScope.pvoList}" var="list">
			<div class="col-md-6">
    		<input type="hidden" name="rownum" value="${list.rownum}">
				<div class="thumbnail productCard" style="border: solid 2px #e6e6e6; box-sizing : border-box;">
					<div class="col-sm-12 thumbnailImgDiv" align="center" style="border: solid 1px #e6e6e6; width: 100%; height: 200px; overflow: hidden;">
						<a href="auth_hit.do?product_id=${list.product_id}">
							<img src="${list.thumbnail_link}" class="img-responsive thumbnailImg" width='285' height='200'>
						</a>
					</div>
					<div>
						<div class="caption" align="left">
							<h4 class="product_name">${list.product_name}</h4>
						</div>
						<div align="right">
							최저가 : <font class="lowestPrice" size="4" face="윤고딕320">${list.lowestPrice}</font> 원
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

