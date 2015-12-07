<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${initParam.root}js/jquery-1.11.3.min.js"></script>
<<<<<<< HEAD
<!--    <script type="text/javascript">
   function login_view(){
	   location.href="${initParam.root}loginview.do";
   }

</script>
    

  <p align="right">
  
  <input type="button"  value="로그인"  name="login_view" onclick="login_view()">  
    <input type="button"  value="장바구니" name=> 
      <input type="button"  value="Q&A" name=>  -->

=======
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
<script type="text/javascript">
	function follow_view(){
		
		 location.href="findFollowingId.do?member_id=${mvo.member_id}";
		
	}
	function follow1_view(){
		 location.href="findFollowerId.do?member_id=${mvo.member_id}";
	}
	
	$(document).ready(function(){
	   $("#findBtn").click(function(){
	      if($("#inputId3").val()==""){
	         alert("아이디를 입력해주시오");
	         return false;
	      }   
	   });
	});
	
	function onKeyup(){
		var min = $("#inputId3").val();
		 $.ajax({
	         type:"POST",
	         url:"onkeyupId.do",
	         data:"searchId="+min,
	         dataType:"json",
	         success:function(data){
	        	var index = "";
	        	if(data!=""){
	        		for(var i=0;i<data.svoList.length;i++){
		  	        	   index += "<tr><td>"+data.svoList[i].member_id+"</td></tr>";
		  	        }
	  	        	$("#searchIdView").html(index);
	        	}else{
	        		$("#searchIdView").html(index);
	        	}
	  	           
	         }//callback         
	      });//ajax
	}
</script>

<div class="col-md-2">
	<c:if test="${sessionScope.mvo!=null}">
	    <form class="form-horizontal" role="form" action="findMemberById.do">
	        <div class="form-group">
	            <div class="col-sm-12">
	            	<input type="hidden" name="sessionId" value="${sessionScope.mvo.member_id}">
<<<<<<< HEAD
	                <input type="text"  class="form-control" id="inputId3" name="member_id" onkeyup="onKeyup()">
	                <input class="active btn btn-danger btn-sm" type="submit" value="검색" id="findBtn">
	                <span id="searchIdView"></span>
	                
	   
=======
	                <div class="input-group">
		                <input type="text"  class="form-control" id="inputId3" name="member_id" onkeyup="onKeyup()" placeholder="ID로 회원 검색">
		                <span class="input-group-btn">
		                	<a class="btn btn-success" type="submit" id="findBtn">검색</a>
		                </span>
					</div>
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
	            </div>
	        </div>
		    <hr>
			<span id="searchIdView"></span>
	    </form>
			<input class="btn btn-default" type="button" value="팔로잉" onclick="follow_view()">	
			<input class="btn btn-default" type="button" value="팔로워" onclick="follow1_view()">
<<<<<<< HEAD
		
=======
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
	</c:if>
</div>


