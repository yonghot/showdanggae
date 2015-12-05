<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

  $(document).ready(function() {

	   function login_view(){
		   location.href="${initParam.root}loginview.do";
	   }
	
	   function notice_view(){
		   location.href="${initParam.root}notice.do";
	   }
	   
	   function myinfo_view(){
		  /*  내정보 요청 */
		   location.href="${initParam.root}myinfo_view.do";
	   }
	   
		$("#logout").click(function(){
			if (confirm("로그아웃하시겠습니까??") == true) {
				location.href = "${initParam.root}logout.do";
				return true;
			} else {
				return false;
			}
		});
	});
</script>
 
 
 <div class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                    <ul class="nav navbar-nav navbar-left">
                    	<li class="active">
                            <a href="home.do">홈</a>
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
	                        	 <a href="logout.do" id="logout">로그아웃</a>
								</c:when>
								<c:otherwise>
								<!-- 그 외 -->
	                        	<a href="member_login.do" >로그인</a>
								</c:otherwise>
							</c:choose>	
                        </li>
                        <li>
                            <a href="notice.do">공지사항</a>                    
                        </li>
                        <li>
                            <a href="shoping_getProductInfoList.do">Q&A</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div><div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center">쇼당개</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-offset-3 col-md-6">
                        <form role="form">
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="상품명 또는 카테고리를 입력하세요">
                                    <span class="input-group-btn">
                                        <a class="btn btn-success" type="submit">Go</a>
                                    </span>
                                </div>
                                <hr>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>