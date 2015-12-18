<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
  $(document).ready(function() {
	  
	  if($(":input[name=rownum]").val()>2) {
		  $(this).parent().hide();
	  }
	  
     //스크롤 이벤트 발생 시
     $(window).scroll(function() {
        var documentHeight = $(document).height();
        var scrollHeight = $(window).height()+$(window).scrollTop();

        if (documentHeight <= scrollHeight) { //그냥 = 으로 해서 계속 안됐었음.. 주의합시다
        	
        	if($(":input[name=rownum]").val()>2){
        		$(this).show();
        	}
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
     
  });
  
</script>
<div class="col-md-8">
    <div class="btn-group">
    	<a href="getAllBoardList.do?sortBy=likes" class="btn btn-default">좋아요</a>
    	<a href="getAllBoardList.do?sortBy=interest..." class="btn btn-default">관심사</a>
    	<br>
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

