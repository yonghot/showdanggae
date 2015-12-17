<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<!-- 자동완성 sheet -->
<script type="text/javascript">
	$(document).ready(function(){	
	//var quick_menu = $('#quick').offset().top;
	
	var boxtop = $('#quick');
	var q=100;
    $(window).scroll(function(){ 
    boxtop.stop();
   // boxtop.animate({"top": $('#quick').scrollTop() + q + "px"}, 500); 
   //alert("asdf");
    boxtop.offset({"top": $('#quick').scrollTop() + q + "px"}, 100);

  });  			
	/* 	//팔로잉 이름을 클릭시 발동 되는 이벤트
		 $('#searchIdView1').on('click','.messagePopBtn1',function(){
			 var id=$(this).children().children().val();
				if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
					 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
				"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
					}else{
						return false;
					}
		   });	 */
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
		$("#searchIdView").on('click','.messagePopBtn3',function(){ 
			var follow = $(this).val();
			/* var id = $("#followForm").parent().siblings().text(); */
			var id =$(this).parent().parent().siblings().eq(0).text();
			//alert(id);
			if(follow=="+팔로우"){
				$(this).val("v팔로잉");
				$.ajax({
					type:"POST",
					url:"auth_add.do",
					data:"follower=${sessionScope.mvo.member_id}&following="+id,
					dataType:"json",
					success:function(data){
					}//callback			
				});//ajax
			} else if(follow=="v팔로잉") {
				$(this).val("+팔로우");
				 $.ajax({
		               type:"POST",
		               url:"auth_delete.do",
		               data:"follower=${sessionScope.mvo.member_id}&following="+id,
		               dataType:"json",
		               success:function(data){
		               }//callback         
		            });//ajax
			}  			
		});
/* 		$.ajax({
			type:"get",
			url:"falarm.do?following=${sessionScope.mvo.member_id}",
			dataType:"json",
			success:function(data){	
				if(data!=""){
					var index="";
					for(var i=0; i<data.length;i++){						
						index += "<hr>"+data[i].following_date+ "<hr>" + data[i].follower + "님이 팔로우 하셨습니다<br>";
					 }
					$("#realarm").html(index).css('fontSize', 5);
					$("#alarm").html("알람" + " <span class='badge'>" +data.length + "</span></a>");
					//<a href="#">Inbox <span class="badge">42</span></a>
				}else{
					$("#alarm").html("알람" + " <span class='badge'>"  + "</span></a>");
				}
			}
		}); 	 */
		//클릭했을때 팔로우 알림 
/* 		$("#alarm").click(function(){
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
						$("#alarm").html("알람" + " <span class='badge'>" + +data.length + "</span></a>");
					}else{
						$("#alarm").html("알람" + " <span class='badge'>"  + "</span></a>");
					}
				}
			});
		})  */
		//아이디로 회원검색
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
					var index="<table class='table'><thead>";
					 if(data!=""){		
						 index +="<tr class='success'><td>ID</td><td></td></tr></thead><tbody>";
						for(var i=0; i<data.length;i++){	
							alert(data[i].member_id);
							alert(data[i].isFollow);
							if(data[i].member_id=='${sessionScope.mvo.member_id}'){
								index +="<tr><td>"+data[i].member_id+"</td><td></td></tr>";
							 }else if(data[i].member_id=="admingalbage"){
									
							 }else if(data[i].isFollow==true){
								index +="<tr><td>"+data[i].member_id+"</td><td><form><input type='button' value='v팔로잉' id='addBtn' class='messagePopBtn3'></form></td></tr>";
							}else{
								index +="<tr><td>"+data[i].member_id+"</td><td><form><input type='button' value='+팔로우' id='addBtn' class='messagePopBtn3'></form></td></tr>";
							}
						}
						index+="</table>";
						$("#searchIdView").html(index);
						$("#searchIdView1").html("");
					}else{
						$("#searchIdView").html(index);
						$("#searchIdView1").html("");
					}
				}		
			});//ajax  
	      }   
	   });
		
		 //팔로잉 버튼 클릭시 팔로잉 아이디
		   $("#followingBtn").click(function(event){
			   event.preventDefault();
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
							/* 	index += "<tr><td><a href='#'><span class='messagePopBtn1'>"+data[i].following+
								"<form><input type='hidden' value="+data[i].following+"></form></span></a></td></tr>"; */
								index += "<tr><td><a href='#exampleModal' data-toggle='modal'>"+data[i].following+
								"<form><input type='hidden' value="+data[i].following+"></form></a></td></tr>";
							}
							index+="<tbody></table>"
								$("#searchIdView").html("");	
							$("#searchIdView1").html(index);
				  	   }else{
				  		 $("#searchIdView").html("");
				  		 $("#searchIdView1").html("");
				  	   }
						
						}		
				});//ajax
		   }); 
		 
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
							"<form><input type='hidden' value="+data[i].following+"></form></span></a></td></tr>";
						}
						
						index+="<tbody></table>"
							$("#searchIdView").html("");	
						$("#searchIdView1").html(index);
			  	   }else{
			  		 $("#searchIdView").html("");
			  		 $("#searchIdView1").html("");
			  	   }
					
					}		
			});//ajax
		   //팔로워 버튼 클릭시 팔로우 아이디
		   $("#followerBtn").click(function(event){
			   event.preventDefault();
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
								
								//index += "<tr><td><a href='${initParam.root}messagePopForm1.do?member_id='>"+data[i].follower+"</a></td></tr>";
								index += "<tr><td><a href='#'><span class='messagePopBtn2'>"+data[i].follower+
								"<form><input type='hidden' value="+data[i].follower+"></form></span></a></td></tr>";
							}
							index+="</tbody></table>"
								$("#searchIdView").html("");
							$("#searchIdView1").html(index);
				  	        }else{
				  	        	$("#searchIdView").html("");
						  		 $("#searchIdView1").html("");
						  	   }
						}		
				});//ajax
		   });  	  
	/* function onKeyup(){
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
	} */
	});	
</script>

<div class="col-md-2" align="center">
	<c:if test="${sessionScope.mvo!=null}">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-lg-12">
					<input type="hidden" name="sessionId"
						value="${sessionScope.mvo.member_id}">
					<div class="input-group">
						<input type="text" class="form-control" id="inputId3"
							name="member_id" placeholder="ID로 회원 검색">
						<!-- onkeyup="onKeyup()" -->
						<span class="input-group-btn"> <input
							class="btn btn-success" type="button" id="findBtn" value="검색">
						</span>
					</div>
				</div>
			</div>
		</form>
		<hr>
		<ul class="nav nav-tabs">
			<li><a href="#" id="followingBtn">팔로잉</a></li>
			<li><a href="#" id="followerBtn">팔로워</a></li>
		</ul>
		<!-- <input class="btn btn-default" type="button" value="팔로잉"
			id="followingBtn">
		<input class="btn btn-default" type="button" value="팔로워"
			id="followerBtn"> -->
		<br>
		<span id="searchIdView"></span>
		<span id="searchIdView1"></span>
	</c:if>
</div>
<a href="" class="btn btn-primary" data-toggle="modal"
	data-target="#exampleModal" data-whatever="${mvo.member_id}">메세지보내기</a>
<%-- <!--  data-offset-top="400" -->
<c:if test="${!empty sessionScope.mvo && sessionScope.mvo.member_id!='admingalbage'}">
<div class="quick" data-spy="scroll" style='position:absolute; top: 200px;' >

	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a id="alarm" class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" >
          
        </a>
      </h4>
    		  <div id="alarmcount"></div>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body" id="realarm">
      </div>
      </div>
    </div>
</div>
  </div>
   </c:if> --%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">New message</h4>
			</div>
			<div class="modal-body">
				<form action="sendMessage.do" id="sendForm">
					<!-- sendMessage.do -->
					<div class="form-group">
						<label for="recipient-name" class="control-label">Recipient:</label>
						<input type="text" class="form-control" id="recipient-name" value="${requestScope.recipient}"
							name="member_id">

					</div>
					<div class="form-group">
						<label for="message-title" class="control-label">title:</label> <input
							type="text" class="form-control" id="message-text"
							placeholder="제목" name="title">
					</div>
					<div class="form-group">
						<label for="message-text" class="control-label">Message:</label>
						<textarea class="form-control" id="message-text"
							placeholder="보낼내용" name="message"></textarea>
					</div>
					<input type="hidden" value="${sessionScope.mvo.member_name}"
						name="sender">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="sendMessage">보내기</button>
			</div>
		</div>
	</div>
</div>








