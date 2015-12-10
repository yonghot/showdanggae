<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
  $(document).ready(function() {
     //스크롤 이벤트 발생 시
     
     $(window).scroll(function() {
        var documentHeight = $(document).height();
        var scrollHeight = $(window).height()+$(window).scrollTop();

        if (documentHeight <= scrollHeight) { //그냥 = 으로 해서 계속 안됐었음.. 주의합시다
        	
           for(var i=0;i<3;i++) {
              $(
                  "<div class='col-md-6'>"+
                     "<div class='thumbnail'>"+
                         "<img src='http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B' class='img-responsive'>"+
                         "<div class='caption' align='center'>"+
                             "<h3>${list.product_name}</h3>"+
                         "</div>"+
                     "</div>"+
                  "</div>" 
              ).appendTo("#main_row");
           }
        }
     });
  });
  
</script>

<%-- <div class="col-md-6">
   <div class="thumbnail">
   		<a href="hit.do?no=${list.product_id}">
       		<img src="http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B" class="img-responsive">
       	</a>
       <div class="caption" align="center">
           <h3>${list.product_name}</h3>
       </div>
   </div>
</div> --%>
    
<div class="col-md-8">
    <div class="btn-group">
    	<a href="getAllBoardList.do?sortBy=likes" class="btn btn-default">좋아요</a>
    	<a href="getAllBoardList.do?sortBy=interest..." class="btn btn-default">관심사</a>
    	<br>
    </div>
    <hr>
    <div class="row" id="main_row">
    	<c:forEach items="${requestScope.pvoList}" var="list" begin="0" end="5">
			<div class="col-md-6">
			    <div class="thumbnail">
			    	<a href="hit.do?no=${list.product_id}">
			        	<img src="http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B" class="img-responsive">
			        </a>
			        <div class="caption" align="center">
			            <h3>${list.product_name}</h3>
			        </div>
			    </div>
			 </div> 
		</c:forEach>
	</div>
</div>