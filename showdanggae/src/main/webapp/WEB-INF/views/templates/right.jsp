<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${initParam.root}js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
	   $("#findBtn").click(function(){
	      if($("#Id3").val()==""){
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
	           for(var i=0;i<data.svoList.length;i++){
	        	   index += "<tr><td>"+data.svoList[i].member_id+"</td></tr>";
	           }
	        	   $("#searchIdView").html(index);
	         }//callback         
	      });//ajax
	}
</script>

<div class="col-md-2">
	<c:if test="${sessionScope.mvo!=null}">
	    <form class="form-horizontal" role="form" action="findMemberById.do">
	        <div class="form-group">
	            <div class="col-sm-2">
	                <label for="inputEmail3" class="control-label">ID</label>
	            </div>
	            <div class="col-sm-10">
	            	<input type="hidden" name="sessionId" value="${sessionScope.mvo.member_id}">
	                <input type="text" class="form-control" id="inputId3" name="member_id" placeholder="아이디 입력" onkeyup="onKeyup()">
	                <br>
	                <input class="active btn btn-danger btn-sm" type="submit" value="검색" id="findBtn">
	                <span id="searchIdView"></span>
	            </div>
	        </div>
		    <hr>
	    </form>
	    <form id="followingForm" action="findFollowingId.do">
			<input type="hidden" name="member_id" value="${mvo.member_id}">
			<input class="btn btn-default" type="submit" value="팔로잉">
		</form>
		<form id="followerForm" action="findFollowerId.do">
			<input type="hidden" name="member_id" value="${mvo.member_id}">
			<input class="btn btn-default" type="submit" value="팔로워">
		</form>
	</c:if>
</div>

