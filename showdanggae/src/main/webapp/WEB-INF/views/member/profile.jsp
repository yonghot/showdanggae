<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">  
   	$(document).ready(function(){
   		$("#interestDelBtn").click(function(){
   			var checkval=$("#delinterest :input[name=interestCheck]:checked").val();
   			alert(checkval)
   			if(checkval==null){
			   	alert("삭제할 관심사를 선택하세요");
			   	return false;
			 }
			var result = confirm('삭제하시겠습니까?');
	        if(result) {
	        	location.href="interestDel.do?member_id=${sessionScope.mvo.member_id}&category="+checkval;
	        } else {
	        	location.href="profile.do?member_id=${sessionScope.mvo.member_id}";
	        }
   		});
   		
   		$("#interestAddBtn").click(function(){
			   			var checkval=$("#interestForm :input[name=interestCheck]:checked").val();		   			
			   			//$("input[name=chk1]:checkbox:checked").length
			   			var checklen=$("#interestForm :input[name=interestCheck]:checked").length;
			if(checkval==null){
			   	alert("추가할 관심사를 선택하세요");
			   	return false;
			 }
			if(checklen>1){
				alert("하나씩 선택 가능합니다");
				return false;
			}	
		   		location.href="interestAdd.do?member_id=${sessionScope.mvo.member_id}&category=" + checkval;      		   	
   			});//interestAddBtn
   	
   		$("#member_infoBtn").click(function(){
   			var infoComp = $(":input[name=member_info]").val();
   			location.href="infoUpdate.do?member_info=" + infoComp +"&member_id=${sessionScope.mvo.member_id}";
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
	<td width="600"><!-- file:\\\C:\\java-kosta\\WAS\\project-tomcat\\webapps\\showdanggae\\uploadl\\lipchel.jpg -->
<!-- 	<img src="C:\\java-kosta\\WAS\\project-tomcat\\webapps\\showdanggae\\uploadl\\${sessionScope.mvo.member_id}" alt="..." class="img-circle"> -->
	<img src="${initParam.root }upload/${sessionScope.mvo.member_id}.jpg" class="img-circle"  width=100px, height=100px>
	</td>
	
	<td>
	 	    <form id="profileupimgloadForm" action="${initParam.root}fileupload.do" enctype="multipart/form-data" method="post">
			<input type="file" name="proImgFile"><br>
			<input type="hidden" name="member_id" value="${sessionScope.mvo.member_id}">
			<input type="submit" name="파일업로드"> <br>
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
	<td>
	관심사 추가
		<form id="interestForm">  
        <button class="btn btn-default" type="button" id="interestAddBtn">추가</button>
	<br>

	<c:forEach items="${requestScope.interestList}" var="interestList" >
			<input type="checkbox" name=interestCheck  value="${interestList}">${interestList} 	 
	</c:forEach>
	
	<c:forEach items="${requestScope.myinterestList}" var="myinterestList" >
		    <div class="checkbox disabled">
					  <label>
					    <input type="checkbox"  disabled>
					   		${myinterestList }
					  </label>
					</div>		
	</c:forEach>	
	</form>
	</td>
	<td width=400px, height=400px>
		관심사 삭제
		<form id="delinterest">
        <button class="btn btn-default" type="button" id="interestDelBtn">삭제</button><br>
	<c:forEach items="${requestScope.interestList}" var="interestList" >
			<input type="checkbox"  disabled  value="${interestList}">${interestList} 	 
	</c:forEach>
	
	<c:forEach items="${requestScope.myinterestList}" var="myinterestList" >
		    <div class="checkbox disabled">
					  <label>
					    <input type="checkbox" name=interestCheck  value="${myinterestList}">
					   		${myinterestList }
					  </label>
					</div>		
	</c:forEach>	
	</form>
	</td>
	</tr>


</table>
</div>
</div>
</div>

