<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<!-- 자동완성 sheet -->
<script type="text/javascript">

	/* function follow_view(){
		 location.href="auth_findFollowingId.do?member_id=${mvo.member_id}";
	} */
	/* function follow1_view(){
		 location.href="auth_findFollowerId.do?member_id=${mvo.member_id}";
	} */	
	$(document).ready(function(){	
		//팔로잉 이름을 클릭시 발동 되는 이벤트
		 $('#searchIdView1').on('click','.messagePopBtn1',function(){
			 var id=$(this).children().children().val();
				if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
					 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
				"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
					}else{
						return false;
					}
		   });	
		//팔로우 이름 클릭시 발동 되는 이벤트
		 $('#searchIdView1').on('click','.messagePopBtn2',function(){
			 var id=$(this).children().children().val();
				if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
					 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
				"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
					}else{
						return false;
					}
		   });	
		$.ajax({
			type:"get",
			url:"falarm.do?following=${sessionScope.mvo.member_id}",
			dataType:"json",
			success:function(data){	
				if(data!=""){
					var index="";
					for(var i=0; i<data.length;i++){						
						index += data[i].following_date + data[i].follower + "님이 팔로우 하셨습니다<br>";
					 }
					$("#realarm").html(index).css('fontSize', 5);
					$("#alarm").html("알람(" + data.length + ")");
				}else{
					$("#alarm").html("알람" + "(0)");
				}
			}
		}); 
		
		//클릭했을때 팔로우 알림 
		$("#alarm").click(function(){
			$.ajax({
				type:"get",
				url:"falarm.do?following=${sessionScope.mvo.member_id}",
				dataType:"json",
				success:function(data){	
					if(data!=""){
						var index="";
						for(var i=0; i<data.length;i++){						
							index += data[i].following_date + data[i].follower + "님이 팔로우 하셨습니다<br>";
						 }
						$("#realarm").html(index).css('fontSize', 5);
						$("#alarm").html("알림(" + data.length + ")");
					}else{
						$("#alarm").html("알림" + "(0)");
					}
				}
			});
		}) 

	   $("#findBtn").click(function(){
		   var min = $("#inputId3").val();
		if($("#inputId3").val()==""){
	         alert("아이디를 입력해주시오");
	         return false;
	      }else{
		   $.ajax({
				type:"POST",
				url:"auth_findMemberById.do",
				data:"sessionId=${sessionScope.mvo.member_id}&member_id="+min,
				dataType:"json",
				success:function(data){
					var index="";
					if(data!=null){				
						for(var i=0; i<data.length;i++){	
							//alert(data[i].member_id);
							index += data[i].member_id;
						}
						$("#searchIdView").html(index);
					}else{
						$("#searchIdView").html(index);
					}
					/* if(data!=""){				
						for(var i=0; i<data.length;i++){	
							alert(data[i].member_id);
<<<<<<< HEAD
							index += "<tr><td>"+data[i].member_id+"</td></tr>";
						}		
						$("#searchIdView1").html(index); 
					}
						   
					}		
=======

							index += data[i].member_id;
						}
						$("#searchIdView").html(index);
					}else{
						$("#searchIdView").html(index);
					} */
				}		
>>>>>>> branch 'master' of https://github.com/yonghot/showdanggae.git
			});//ajax  
	      }   
	   });
		
		$("#followingView a").click(function(){
			var id=$(this).text();
			if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
			 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
		"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
			}else{
				return false;
			}
		});	
	   //팔로잉 버튼 클릭시 팔로잉 아이디
	   $("#followingBtn").click(function(){
		   $.ajax({
				type:"POST",
				url:"auth_findFollowingId.do",
				data:"member_id=${mvo.member_id}",
				dataType:"json",
				success:function(data){
					var index="<table class='table'><thead>";
					if(data!=""){
						index +="<tr class='success'><td>ID</td></tr></thead><tbody>";
						for(var i=0; i<data.length;i++){						
							//index += "<tr><td><a href='${initParam.root}messagePopForm1.do?member_id='>"+data[i].following+"</a></td></tr>";
							index += "<tr><td><a href='#'><span class='messagePopBtn1'>"+data[i].following+
							"<form><input type='hidden' class='followingName' value="+data[i].following+"></form></span></a></td></tr>";
						}
						index+="<tbody></table>"
							$("#searchIdView1").html(index);
			  	   }else{
			  		 $("#searchIdView1").html("");
			  	   }
					
					}		
			});//ajax
	   }); 
	   //팔로우 버튼 클릭시 팔로우 아이디
	   $("#followerBtn").click(function(){
		   $.ajax({
				type:"POST",
				url:"auth_findFollowerId.do",
				data:"member_id=${mvo.member_id}",
				dataType:"json",
				success:function(data){
					var index="<table class='table'><thead>";
					if(data!=""){
						index +="<tr class='success'><td>ID</td></tr></thead><tbody>";
						for(var i=0; i<data.length;i++){
							//index += "<tr><td><a href='${initParam.root}messagePopForm1.do?member_id=j'>"+data[i].follower+"</a></td></tr>";
							index += "<tr><td><a href='#'><span class='messagePopBtn2'>"+data[i].follower+
							"<form><input type='hidden' class='followingName' value="+data[i].follower+"></form></span></a></td></tr>";
						}
						index+="</tbody></table>"
						$("#searchIdView1").html(index);
			  	        }else{
					  		 $("#searchIdView1").html("");
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
		  	       	var autocomplete_text=[];
	        		for(var i=0;i<data.length;i++){
		  	        	 // index += "<tr><td>"+data[i].member_id+"</td></tr>";	  	        	 
							autocomplete_text.push(data[i].member_id);
		  	        }
	        			$("#inputId3").autocomplete({
	       	             source:autocomplete_text
	       	          });
	  	        	//$("#searchIdView").html(index);
	        	}else{
	        	//	$("#searchIdView").html(index);
	        	}
	  	           
	         }//callback         
	      });//ajax
	} 
</script>

<div class="col-md-2" align="center">
	<c:if test="${sessionScope.mvo!=null}">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-12">
					<input type="hidden" name="sessionId"
						value="${sessionScope.mvo.member_id}">
					<div class="input-group">
						<input type="text" class="form-control" id="inputId3"
							name="member_id" onkeyup="onKeyup()" placeholder="ID로 회원 검색">
						<span class="input-group-btn"> <input
							class="btn btn-success" type="button" id="findBtn" value="검색">
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
			id="followerBtn"><br>
		<!-- onclick="follow1_view()" -->
		<span id="searchIdView"></span>
		<span id="searchIdView1"></span>
	</c:if>


	<div data-spy="affix" data-offset-top="200">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"> 알림 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">내용</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo"> 2 </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a id="alarm" class="collapsed" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree"> </a>
					</h4>
					<div id="alarmcount"></div>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body" id="realarm"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<br>
<br>
<br>
<br>

