<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">

    </script>
    
    
    	<div class="col-md-8">
    			

    	
		<h3>Q&A</h3>
<div align="right">


  </div>

    <table class="table" id="qnaTable">
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
		<c:forEach var="qvo" items="${requestScope.qvo.list}">				
			<tr>
			    <td>
			    ${qvo.no }
			    </td> 	 	 	    	
				<td>
				<c:if test="${qvo.relevel!=0}">
				<c:forEach begin="0" end="${qvo.relevel}" step="1">&nbsp;&nbsp;</c:forEach>
				└▶
				<%-- <img src="${initParam.root }img/reply.jpg"> --%>
				</c:if>
				<a href="${initParam.root}showContent.do?no=${qvo.no}">${qvo.title }[${qvo.total }]</a>
				</td>
				<td>${qvo.writer }</td>
				<td>${qvo.writeDate}</td>
				<td>${qvo. viewCount }</td>
			</tr>	
			</c:forEach>
		</tbody>					
	</table><br>
			<div align="right">
		
	    <c:if test="${!empty sessionScope.mvo}">
<button type="button" class="btn btn-default btn-sm">
  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><a href="${initParam.root}qnaWriteForm.do"> 글쓰기</a> <br>
</button>
	</c:if>
	</div> 


<div align="center">
<nav>
  <ul class="pagination">

	<c:if test="${requestScope.qvo.qpagingBean.isPreviousPageGroup()}">
			<%-- <a href="${initParam.root}auth_qnaboard.do?pageNo=${requestScope.qvo.qpagingBean.startPageOfPageGroup-1}">◀ </a> --%>
	 <li>
      <a href="${initParam.root}auth_qnaboard.do?pageNo=${requestScope.qvo.qpagingBean.startPageOfPageGroup-1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
	</c:if>
		
		
<c:forEach begin="${requestScope.qvo.qpagingBean.startPageOfPageGroup }" end="${requestScope.qvo.qpagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.qvo.qpagingBean.nowPage==i}">
	
	<%-- <li class="active"><a href="${initParam.root}auth_qnaboard.do?pageNo=${i }">${i } <span class="sr-only">(current)</span></a></li> --%>
	<li class="active"><a href="#">${i }<span class="sr-only">(current)</span></a></li>
		<%-- ${i} --%>
	</c:when>
	<c:otherwise>
	
			  <li><a href="${initParam.root}qnaboard.do?pageNo=${i }">${i} </a></li>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.qvo.qpagingBean.isNextPageGroup()}">

	  <li>
      <a href="${initParam.root}qnaboard.do?pageNo=${requestScope.qvo.qpagingBean.endPageOfPageGroup+1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
  
	 </li>
	</c:if>  

	</ul>
</nav>

  
  
  
</div>


</div>


