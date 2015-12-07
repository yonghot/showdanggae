<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(document).ready(function() {
      //스크롤 이벤트 발생 시
      
      $(window).scroll(function() {
         var documentHeight = $(document).height();
         var scrollHeight = $(window).height()+$(window).scrollTop();

         if (documentHeight <= scrollHeight) { //그냥 = 으로 해서 계속 안됐었음.. 주의합시다
            for(var i=0;i<2;i++) {
               $(
                   "<div class='col-md-6'>"+
                      "<div class='thumbnail'>"+
                          "<img src='http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B' class='img-responsive'>"+
                          "<div class='caption'>"+
                              "<h3>Thumbnail label</h3>"+
                              "<p>"+
                              "Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id"+
                              "    elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies"+
                              "    vehicula ut id elit.</p>"+
                          "</div>"+
                      "</div>"+
                   "</div>" 
               ).appendTo("#main_row");
            }
         }
      });
   });
   
</script>
    
<div class="col-md-8">
    <div class="btn-group">
    	<a href="#" class="btn btn-default">좋아요</a>
    	<a href="#" class="btn btn-default">관심사</a>
    	<br>
    </div>
    <hr>
    <div class="row" id="main_row">
    	<div class="col-md-6">
		    <div class="thumbnail">
		        <img src="http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B" class="img-responsive">
		        <div class="caption">
		            <h3>Thumbnail label</h3>
		            <p>
		            Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id
		                elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies
		                vehicula ut id elit.</p>
		        </div>
		    </div>
		 </div>   
    	<div class="col-md-6">
		    <div class="thumbnail">
		        <img src="http://cfile28.uf.tistory.com/image/2113AC3755228C8F163A5B" class="img-responsive">
		        <div class="caption">
		            <h3>Thumbnail label</h3>
		            <p>
		            Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id
		                elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies
		                vehicula ut id elit.</p>
		        </div>
		    </div>
		 </div>   
	</div>
</div>
