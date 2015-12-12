<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	/* function follow_view(){
		 location.href="auth_findFollowingId.do?member_id=${mvo.member_id}";
	} */
	/* function follow1_view(){
		 location.href="auth_findFollowerId.do?member_id=${mvo.member_id}";
	} */	
	$(document).ready(function(){
		
	   $("#findBtn").click(function(){
		   var min = $("#inputId3").val();
		   if($("#inputId3").val()==""){
	         alert("아이디를 입력해주시오");
	         return false;
	      }/* else{   
		   $.ajax({
				type:"POST",
				url:"auth_findMemberById.do",
				data:"sessionId=${sessionScope.mvo.member_id}&member_id="+min,
				dataType:"json",
				success:function(data){
					var index="";
					if(data!=""){				
						for(var i=0; i<data.length;i++){						
							index += "<tr><td>"+data[i].member_id+"</td></tr>";
						}		
					}
						$("#searchIdView1").html(index);    
					}		
			});//ajax 
	      } */
	   });
	   
	   $("#followingBtn").click(function(){
		   $.ajax({
				type:"POST",
				url:"auth_findFollowingId.do",
				data:"member_id=${mvo.member_id}",
				dataType:"json",
				success:function(data){
					var index="";
					if(data!=""){
						for(var i=0; i<data.length;i++){						
							index += "<tr><td>"+data[i].following+"</td></tr>";
						 }
			  	        }
					$("#searchIdView1").html(index);
					}		
			});//ajax	
	   }); 
	   
	   $("#followerBtn").click(function(){
		   $.ajax({
				type:"POST",
				url:"auth_findFollowerId.do",
				data:"member_id=${mvo.member_id}",
				dataType:"json",
				success:function(data){
					var index="";
					if(data!=""){
						for(var i=0; i<data.length;i++){
							index += "<tr><td>"+data[i].follower+"</td></tr>";
						}
						$("#searchIdView1").html(index);
			  	        }
					}		
			});//ajax
	   });  
	});
	
	function onKeyup(){
		var min = $("#inputId3").val();
		 $.ajax({
	         type:"POST",
	         url:"auth_onkeyupId.do",
	         data:"searchId="+min,
	         dataType:"json",
	         success:function(data){
	        	var index = "";
	        	if(data!=""){
	        		for(var i=0;i<data.length;i++){
		  	        	   index += "<tr><td>"+data[i].member_id+"</td></tr>";
		  	        }
	  	        	$("#searchIdView").html(index);
	        	}else{
	        		$("#searchIdView").html(index);
	        	}
	  	           
	         }//callback         
	      });//ajax
	}
</script>

<div class="col-md-2" align="center">
	<c:if test="${sessionScope.mvo!=null}">
		<form class="form-horizontal" role="form" action="auth_findMemberById.do">
			<div class="form-group">
				<div class="col-sm-12">
					<input type="hidden" name="sessionId"
						value="${sessionScope.mvo.member_id}">
					<div class="input-group">
						<input type="text" class="form-control" id="inputId3"
							name="member_id" onkeyup="onKeyup()" placeholder="ID로 회원 검색">
						<span class="input-group-btn"> <input
							class="btn btn-success" type="submit" id="findBtn" value="검색">
						</span>
					</div>
				</div>
			</div>
		</form>
		<hr>
		<input class="btn btn-default" type="button" value="팔로잉"
			id="followingBtn">
		<!-- onclick="follow_view()" -->
		<input class="btn btn-default" type="button" value="팔로워"
			id="followerBtn">
		<!-- onclick="follow1_view()" -->
		<span id="searchIdView"></span>
		<span id="searchIdView1"></span>

	</c:if>
</div>
<br>
<br>
<br>
<br>

