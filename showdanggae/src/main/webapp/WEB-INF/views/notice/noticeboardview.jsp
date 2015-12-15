<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<h3>공지사항 관리</h3>

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
			      <c:if test="${sessionScope.mvo.member_id=='admingalbage'}">
			    <input type="checkbox" id="checkbox_no" name="check_no" value="${bvo.no }">
			    </c:if>
			    ${bvo.no }
			    </td> 	
			    	 	    	
				<td><a href="${initParam.root}noticeShow.do?no=${bvo.no}">
				${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.writeDate}</td>
				<td>${bvo.hit }</td>
			</tr>	
			</c:forEach>
		</tbody>					
	</table><br>
	    <c:if test="${sessionScope.mvo.member_id=='admingalbage'}">
			<!--관리자가 로그인 했을때 -->
<span style="float:right"> <input type="submit"  value="선택 글 삭제"  id="notice_delete"  class="btn btn-info btn-default"></span>
		</c:if>	
			</form>	
	<div style="margin: left;">
	    <c:if test="${sessionScope.mvo.member_id=='admingalbage'}">
	<a href="${initParam.root}noticeWriteForm.do">글쓰기</a><br>
	</c:if>
</div>

	
<div align="center">
	<nav>
  <ul class="pagination">
    
<c:if test="${requestScope.noticeList.pagingBean.isPreviousPageGroup()}">
 <li>
      <a href="${initParam.root}notice.do?pageNo=${requestScope.noticeList.pagingBean.startPageOfPageGroup-1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
</c:if>
		
		
 
<c:forEach begin="${requestScope.noticeList.pagingBean.startPageOfPageGroup }" end="${requestScope.noticeList.pagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.noticeList.pagingBean.nowPage==i}">
	 <li class="active"><a href="${initParam.root}notice.do?pageNo=${i }">${i } <span class="sr-only">(current)</span></a></li>
		
	</c:when>
	<c:otherwise>
		  <li><a href="${initParam.root}notice.do?pageNo=${i }">${i} </a></li>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.noticeList.pagingBean.isNextPageGroup()}">
	  <li>
      <a href="${initParam.root}notice.do?pageNo=${requestScope.noticeList.pagingBean.endPageOfPageGroup+1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
	</c:if>  

	</ul>
</nav>
	</div>
	
	
	<br><br>	
	    </div>
	    
	    
	    

	
	
	