<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">  
   	$(document).ready(function(){
   		
   		$("#interestAddBtn").click(function(){
			   			var checkval=$("#interestForm :input[name=interestCheck]:checked").val();
			   			if(checkval==null){
			   				alert("추가할 관심사를 선택하세요");
			   				return false;
			   			}
			   			var myin=$("input:hidden[name='myinterestList']").val();
			   				if(myin==null){
			   					location.href="interestAdd.do?member_id=${sessionScope.mvo.member_id}&category=" + checkval;		
			   				}
			   			$("input:hidden[name='myinterestList']").each(function (index){		 
			   				var myinterestList=$("input:hidden[name='myinterestList']").eq(index).val();	 	   		    	   
			   			   if(myinterestList==checkval){
			   		    	  	alert(myinterestList + "는 이미 관심사에 추가한 카테고리입니다");		    	
			   		    	  	return false;
			   		       }else	   			   		       
			   		        	location.href="interestAdd.do?member_id=${sessionScope.mvo.member_id}&category=" + checkval;			   		     		   		      
				   		});
			   	
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
	<td>
	파일 미리보기<br>
	<img src="https://igcdn-photos-a-a.akamaihd.net/hphotos-ak-xtf1/t51.2885-19/10948561_1404359156532776_1425325698_a.jpg" alt="..." class="img-circle">
	</td>
	<td>
	 파일 업로드
	 	    <form id="profileupimgloadForm" action="${initParam.root}profileupimgload.do" enctype="multipart/form-data" method="post">
			<input type="text" name="userInfo"><br>
			<input type="file" name="file"><br>
			
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
	<td colspan="2">
	관심사 목록
		<form id="interestForm">  
        <button class="btn btn-default" type="button" id="interestAddBtn">추가</button>
        <button class="btn btn-default" type="button" id="interestDelBtn">삭제</button>
	<br>

	<c:forEach items="${requestScope.interestList}" var="interestList" >
				
					    <input type="checkbox" name=interestCheck  value="${interestList}">${interestList}
						 
	</c:forEach>
	
	<c:forEach items="${requestScope.myinterestList}" var="myinterestList" >
		    <input type="hidden" name=myinterestList  id=myinter value="${myinterestList}">					 
	</c:forEach>
	
	</form>

	</td>

		 	
<%-- 					<div class="checkbox disabled">
					  <label>
					    <input type="checkbox"  disabled>
					   		${myinterestList }
					  </label>
					</div> --%>

<%-- 				  <div class="checkbox">
					  <label>
					    <input type="checkbox" >
							${interestList }
					  </label>
					</div> --%>
	
	</tr>


</table>
</div>
</div>
</div>

