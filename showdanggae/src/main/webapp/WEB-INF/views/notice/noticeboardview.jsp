<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
   
	$("#notice_delete").click(function(){
 		var checkval=$("#notice_deleteForm :input[name=check_no]:checked");
 	
 	
 		if(checkval.length<1){
 			alert("삭제할 게시물을 선택해주세요");
 				return false;
 		}
 		/*
		//$("#testForm :checkbox[name=hobby]:checked").length
			var send="";
			var String=[];
		//var checkval=$("#notice_deleteForm :input[name=check_no]:checked").val();
		for(var i=0; i<checkval.length; i++){		
			 String=$(checkval[i]).val();
					alert(String);
		} */
		location.href="${initParam.root}notice_delete.do";
		
	});
  });
</script>
  
    
	<div class="col-md-8">
   
	<form id="notice_deleteForm" action="${initParam.root}notice_delete.do">	
	    <c:if test="${!empty sessionScope.managerlogin}">
			<!--관리자가 로그인 했을때 -->
			<h3>공지사항 관리</h3>
		  <input type="submit"  value="선택 글 삭제"  id="notice_delete" class="btn btn-default">  
		</c:if>	

    <table class="table" id="noticeTable">
	
		<thead>
		<tr>
			<th>  NO</th>
			<th>제목</th>
			<th>이름</th>
			<th>작성일</th>
			<th>조회수</th>
			</tr>
		</thead>
		<tbody>	
		<c:forEach var="bvo" items="${requestScope.noticeList.list}">				
			<tr>
			    <td>
			      <c:if test="${!empty sessionScope.managerlogin}">
			    <input type="checkbox" id="checkbox_no" name="check_no" value="${bvo.no }">
			    </c:if>
			    ${bvo.no }
			    </td> 	
			    	 	    	
				<td><a href="${initParam.root}noticeShow.do?no=${bvo.no }">
				${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.time_post}</td>
				<td>${bvo.hit }</td>
			</tr>	
			</c:forEach>
		</tbody>					
	</table><br>
			</form>	
	
	    <c:if test="${!empty sessionScope.managerlogin}">
	<a href="${initParam.root}noticeWriteForm.do">글쓰기</a><br>
	</c:if>
	

	

		<c:if test="${requestScope.noticeList.pagingBean.isPreviousPageGroup()}">
			<a href="${initParam.root}notice.do?pageNo=${requestScope.noticeList.pagingBean.startPageOfPageGroup-1}">◀ </a>
		</c:if>
		
		
<c:forEach begin="${requestScope.noticeList.pagingBean.startPageOfPageGroup }" end="${requestScope.noticeList.pagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.noticeList.pagingBean.nowPage==i}">
		${i}
	</c:when>
	<c:otherwise>
			<a href="${initParam.root}notice.do?pageNo=${i }">${i }</a>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.noticeList.pagingBean.isNextPageGroup()}">
	<a href="${initParam.root}notice.do?pageNo=${requestScope.noticeList.pagingBean.endPageOfPageGroup+1}">▶ </a>
	</c:if>  
	
	
	<br><br>	
	    </div>
	    
	    
	    

	
	
	