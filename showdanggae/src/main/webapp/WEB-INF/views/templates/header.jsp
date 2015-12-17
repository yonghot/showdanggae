<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="css/selectReport.css" type="text/css">
<script type="text/javascript">
  $(document).ready(function() {
	  
		$("#logout").click(function(){
			if (confirm("로그아웃하시겠습니까??") == true) {
				location.href = "${initParam.root}logout.do";
				return true;
			} else {
				return false;
			}
		});
		
		$.ajax({
			type:"POST",
			url:"selectReport.do",
			dataType:"json",
			success:function(data){
				var index="<div id='keyword_table' listnum='5' class='realtime_content'><table class='table'><thead>";
				/* var index="<div id='keyword_table' listnum='5' class='realtime_content'><table id='keyword_table'><thead>"; */
				index +="<tr class='success'><td><font color='red'>순위</td><td><font color='red'>검색어</td></tr></thead><tbody>";
					for(var i=0; i<data.length;i++){	
						index +="<tr><td>"+data[i].RANKING+"</td><td>"+data[i].WORD+"</td></tr></tbody>";
					}
					index+="</table>";
					$("#selectReport").html(index);
			}		
		});//ajax  
		
	});

</script>
 
<div class="navbar navbar-default navbar-static-top" style="background-color: #ff7777; z-index:2;">
   <div class="container">
       <div class="collapse navbar-collapse" id="navbar-ex-collapse">
           <ul class="nav navbar-nav navbar-left">
           	<li class="active">
                   <a href="home.do" style="background-color: #ffcccc;">홈</a>
               </li>
           </ul>
           <ul class="nav navbar-nav navbar-right">
               <li class="active">
                   <c:choose>
						<c:when test="${!empty sessionScope.managerlogin}">
						<!-- 관리자 로그인 되었을때 -->
						 <a href="logout.do" id="logout">로그아웃</a>
						</c:when>
						<c:when test="${!empty sessionScope.mvo}">
						<!-- 일반 회원 로그인 되었을때 -->          
					
		 <a href="logout.do" id="logout" style="background-color: #ffcccc;">로그아웃</a>
			  <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="background-color: #ffcccc;">내정보<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><span class="glyphicon glyphicon-user" aria-hidden="true"><a href="auth_member_update_password.do">  내정보수정</a></span></li> 
            <li><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"><a href="auth_getMyProductList.do?member_id=${sessionScope.mvo.member_id}&currentCategory=1"> 내장바구니</a></span></li>
            <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"><a href="auth_messagebox.do?member_id=${sessionScope.mvo.member_id}"> 쪽지함</a></span></li>       
          </ul>
        	</li> 
						</c:when>
						<c:otherwise>
						<!-- 그 외 -->
			                    	<a href="member_login.do" style="background-color: #ffe5e5;">로그인</a>
						</c:otherwise>			
					</c:choose>	
                </li>
                <li class="active">
                    <a href="notice.do" style="background-color: #ffcccc;">공지사항</a>                    
                </li>
                <li class="active">
                    <a href="qnaboard.do" style="background-color: #ffcccc;">Q&A</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="navbar navbar-default navbar-static-top" style="background-image: url('img/showdanggae_header_bg.jpg'); background-size: 100%; z-index:1;">
      <div class="col-md-12">
          <h2 class="text-center"><a href ="home.do" style="text-decoration:none">
	          <img src="${initParam.root}img/showdanggae_logo_nobg.PNG" width="150">
	          <img src="${initParam.root}img/showdanggae_explanation.png" width="150">
          </a></h2>
      </div>
      <div class="col-md-offset-3 col-md-6">
          <form role="form">
              <div class="form-group">
                  <div class="input-group">
                      <input type="text" class="form-control" placeholder="상품명 또는 카테고리를 입력하세요">
                      <span class="input-group-btn">
                          <a class="btn btn-success" type="submit">검색</a>
                      </span>
                  </div>
              </div>
          </form>
          <span id=selectReport></span>
<br>
      </div>
 </div>
