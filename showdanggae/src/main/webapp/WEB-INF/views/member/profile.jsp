<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript">  
   	$(document).ready(function(){
    		$("#profileupimgloadBtn").click(function(){
			 var data = new FormData();
            $.each($('#profileupimgloadForm')[0].files, function(i, file) {          
                data.append('file-' + i, file);
            });
             
            $.ajax({
                url: 'profileupimgload.do',
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
</script>
<div class="col-md-8">
<h3>프로필 수정</h3>

<br>
<div class="col-md-6">
<div align="center">
<table border="1">
<tr>
	<td>
	파일 미리보기<br>
	<img src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" alt="..." class="img-circle">
	</td>
	<td>
	 파일 업로드
	 	    <form id="profileupimgloadForm" action="${initParam.root}profileupimgload.do" enctype="multipart/form-data" method="post">
			<input type="file" name="file[0]"><br>
			<input type="button" value="파일 업로드" id="profileupimgloadBtn"><br>
		</form>
	</td>
</tr>

</table>
</div>
</div>
</div>