<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 자동완성 sheet -->
<script type="text/javascript">
	$(document).ready(function(){	
		//모달창 뜨기 전 발생하는 이벤트
		$('#searchFollowIdView').on('click','.followingIdBtn',function(){
			//$('.클래스명').html("");
			$('#recipient-name').val($(this).text());
		});
		$('#searchFollowIdView').on('click','.followerIdBtn',function(){
			$('#recipient-name').val($(this).text());
		});
		$('#searchIdView').on('click','.findMemberByIdBtn',function(){
			$('#recipient-name').val($(this).text());
		});
	$("#sendMessage").click(function(){		
 			if(confirm('메세지를 보내시겠습니까?')==true){			
 				$("#sendForm").submit();
 			}else{
 				return false;
 			}
 			
 		}); 
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
		 $('#searchFollowIdView').on('click','.messagePopBtn1',function(){
			 var id=$(this).children().children().val();
				if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
				 	 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
				"resizable=true,toolbar=no,width=300,height=300,left=200,top=200");  
					
				}else{
						return false;
					}
		   }); */	 
	/* 	//팔로우 이름 클릭시 발동 되는 이벤트
		 $('#searchFollowIdView').on('click','.messagePopBtn2',function(){
			 var id=$(this).children().children().val();
				if(confirm(id + '님에게 메세지를 보내시겠습니까?')==true){			
					 window.open("${initParam.root}messagePopForm1.do?member_id="+id,"popup",
				"resizable=true,toolbar=no,width=300,height=300,left=200,top=200"); 
					}else{
						return false;
					}
		   });	 */
		   $.ajax({
				type:"POST",
				url:"auth_findFollowingId.do",
				data:"member_id=${mvo.member_id}",
				dataType:"json",
				success:function(data){
					var followingId="";
					var index="<table class='table'><thead>";
					if(data!=""){
						index +="<tr class='success'><td>ID</td></tr></thead><tbody>";
						for(var i=0; i<data.length;i++){		
							//index += "<tr><td><a href='${initParam.root}messagePopForm1.do?member_id='>"+data[i].following+"</a></td></tr>";
							/* index += "<tr><td><a href='#'><span class='messagePopBtn1'>"+data[i].following+
							"<form><input type='hidden' value="+data[i].following+"></form></span></a></td></tr>"; */

							index += "<tr><td><a href='#'class='followingIdBtn' data-toggle='modal' data-target='#exampleModal'>"+data[i].following+
							"<form><input type='hidden' value="+data[i].following+"></form></a></td></tr>";

						}
					/*     <div class="btn-group">
						   <button class="btn">행동</button>
						   <button class='btn dropdown-toggle' data-toggle='dropdown'>
						     <span class='caret'></span>
						   </button>
						   <ul class='dropdown-menu'>
						   <a href='#'class='followerIdBtn' data-toggle='modal' data-target='#exampleModal'>메세지 보내기</a>
						 
						   </ul>
						 </div>  */
						index+="<tbody></table>"
							$("#searchIdView").html("");	
						$("#searchFollowIdView").html(index);
			  	   }else{
			  		 $("#searchIdView").html("");
			  		 $("#searchFollowIdView").html("");
			  	   }
					
					}	
			});//ajax
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
							if(data[i].member_id=='${sessionScope.mvo.member_id}'&&data[i].member_id!="admingalbage"){
								index +="<tr><td>"+data[i].member_id+"</td><td></td></tr>";
							 }else if(data[i].member_id=="admingalbage"){
									index +="";
							 }else if(data[i].isFollow==true){
								index +="<tr><td><a href='#'class='findMemberByIdBtn' data-toggle='modal' data-target='#exampleModal'>"+data[i].member_id+"<form><input type='hidden' value="+data[i].member_id+"></form></a></td><td><form><input type='button' value='v팔로잉' id='addBtn' class='messagePopBtn3'></form></td></tr>";
							}else{
								index +="<tr><td><a href='#'class='findMemberByIdBtn' data-toggle='modal' data-target='#exampleModal'>"+data[i].member_id+"<form><input type='hidden' value="+data[i].member_id+"></form></a></td><td><form><input type='button' value='+팔로우' id='addBtn' class='messagePopBtn3'></form></td></tr>";						
							}
						}
						index+="</table>";
						$("#searchIdView").html(index);
						$("#searchFollowIdView").html("");
					}else{
						$("#searchIdView").html(index);
						$("#searchFollowIdView").html("");
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

								index += "<tr><td><a href='#'class='followingIdBtn' data-toggle='modal' data-target='#exampleModal'>"+data[i].following+
								"<form><input type='hidden' value="+data[i].following+"></form></a></td></tr>";

							}
							index+="<tbody></table>"
								$("#searchIdView").html("");	
							$("#searchFollowIdView").html(index);
				  	   }else{
				  		 $("#searchIdView").html("");
				  		 $("#searchFollowIdView").html("");
				  	   }
						
						}		
				});//ajax
		   }); 
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
								index += "<tr><td><a href='#'class='followerIdBtn' data-toggle='modal' data-target='#exampleModal'>"+data[i].follower+
								"<form><input type='hidden' value="+data[i].follower+"></form></a></td></tr>";
							}
							index+="</tbody></table>"
								$("#searchIdView").html("");
							$("#searchFollowIdView").html(index);
				  	        }else{
				  	        	$("#searchIdView").html("");
						  		 $("#searchFollowIdView").html("");
						  	   }
						}		
				});//ajax
		   });
	});	
</script>

<!-- 아이디 검색하는 창과 팔로잉 팔로우 탭 
session이 있을경우만 보여주고 세션이 끊겼을 경우에는 보여주지 않는다 -->
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
		<br>
		<span id="searchIdView"></span>
		<span id="searchFollowIdView"></span>
	</c:if>
</div>

<!-- 팔로워 팔로우 또는 사람 검색에서 아이디를 눌렀을때 발생하는 모달창 -->
 <div class="modal fade"  aria-hidden="true" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="example2ModalLabel">메세지</h4>
			</div>
			<div class="modal-body">
				<form action="sendToMemberMessage.do" id="sendForm">
					<!-- sendMessage.do -->
					<div class="form-group">
						<label for="recipient-name" class="control-label">받는사람</label>
						<input type="text" class="form-control" id="recipient-name" 
							name="member_id" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="message-title" class="control-label">제목</label> <input
							type="text" class="form-control" id="message-text"
							placeholder="제목" name="title">
					</div>
					<div class="form-group">
						<label for="message-text" class="control-label">메세지</label>
						<textarea class="form-control" id="message-text"
							placeholder="보낼내용" name="message"></textarea>
					</div>
					<input type="hidden" value="${sessionScope.mvo.member_id}"
						name="sender">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="submit" class="btn btn-primary" id="sendMessage">보내기</button>
			</div>
		</div>
	</div>
</div>
 <!-- 사용기술 ajax 부추스트랩 자바스크립트 -->







