<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
    
    </script>
    
    
    	<div class="col-md-8">
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

	
	    <c:if test="${!empty sessionScope.mvo}">
	<a href="${initParam.root}qnaWriteForm.do">글쓰기</a><br>
	</c:if>
	

	

		<c:if test="${requestScope.qvo.qpagingBean.isPreviousPageGroup()}">
			<a href="${initParam.root}auth_qnaboard.do?pageNo=${requestScope.qvo.qpagingBean.startPageOfPageGroup-1}">◀ </a>
		</c:if>
		
		
<c:forEach begin="${requestScope.qvo.qpagingBean.startPageOfPageGroup }" end="${requestScope.qvo.qpagingBean.endPageOfPageGroup }" var="i">
	<c:choose>
	<c:when test="${requestScope.qvo.qpagingBean.nowPage==i}">
		${i}
	</c:when>
	<c:otherwise>
			<a href="${initParam.root}auth_qnaboard.do?pageNo=${i }">${i }</a>
	</c:otherwise>
	</c:choose>	
	</c:forEach>
	
	
 	<c:if test="${requestScope.qvo.qpagingBean.isNextPageGroup()}">
	<a href="${initParam.root}notice.do?pageNo=${requestScope.qvo.qpagingBean.endPageOfPageGroup+1}">▶ </a>
	</c:if>  
	
	
	<br><br>	
</div>

