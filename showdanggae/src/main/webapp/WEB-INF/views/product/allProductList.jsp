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
     
  });
  
</script>

<%-- <div class="col-md-6">
   <div class="thumbnail">
   		<a href="auth_hit.do?no=${list.product_id}">
       		<img src="http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B" class="img-responsive">
       	</a>
       <div class="caption" align="center">
           <h3>${list.product_name}</h3>
       </div>
   </div>
</div> --%>
    

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
</div>

