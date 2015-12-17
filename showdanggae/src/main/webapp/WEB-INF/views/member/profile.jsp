<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">  
   	$(document).ready(function(){
   		
   		$("#member_infoBtn").click(function(){
   			alert("Asdf");
   			var infoComp = $(":input[name=member_info]").val();
   			location.href="infoUpdate.do?member_info=" + infoComp +"&member_id=${sessionScope.mvo.member_id}";
   		});
   		
   		//파일 업로드
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
   	});
</script>
<div class="col-md-8">
<h3>프로필 수정</h3>

<br>
<div class="col-md-6">
<div align="center">
<table border="1" class="table table-bordered">
<tr>
	<td colspan="2">
	<h5>프로필 사진편집</h5>
	</td>
</tr>
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
	<tr>
	<td colspan="2">
	<h5>내소개 편집</h5>
	</td>
	</tr>
	<tr>
	<td colspan="2">
		<div class="col-lg-10">
    <div class="input-group">
      <input type="text" class="form-control"  name="member_info" value="${sessionScope.mvo.member_info}">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" id=member_infoBtn>등록</button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
	
	</td>
	</tr>
	<tr>
	<td colspan="2"><h5>관심사 편집</h5></td>
	</tr>
	<tr>
	<td colspan="2">
	관심사 목록

        <button class="btn btn-default" type="button">등록하기</button>
	<br>

	<c:forEach items="${requestScope.interestList}" var="interestList" >
		<c:forEach items="${requestScope.myinterestList}" var="myinterestList" >
 	<c:choose>
	<c:when test="${interestList==myinterestList&&myinterestList==interestList}">
	<div class="checkbox disabled">
	  <label>
	    <input type="checkbox" value="" disabled>
	   		${interestList }
	  </label>
	</div>
		
	</c:when>
	<c:otherwise>
	  <div class="checkbox">
	  <label>
	    <input type="checkbox" >
	${interestList }
	  </label>
	</div>
	</c:otherwise>
	</c:choose>	 
		</c:forEach>
	</c:forEach>	
	</td>

	
	</tr>


</table>
</div>
</div>
</div>

