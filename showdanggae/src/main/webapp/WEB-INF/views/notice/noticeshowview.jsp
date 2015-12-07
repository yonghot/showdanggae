<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function sendList(){
		location.href="${initParam.root}notice.do";
	}	
	
	function updateForm(){
		location.href="${initParam.root}noticeUpdateForm.do?sno=${requestScope.content.no}";
	}	
	
	function deleteForm(){
		location.href="${initParam.root}noticeUpdateForm.do?sno=${requestScope.content.no}";
	}	

</script>

<div class="col-md-8">
	<h3>공지사항</h3>
	<hr>
    <table class="table">
		<tr>
		<td>NO : ${requestScope.content.no} </td> 
			<td colspan="2">${requestScope.content.title} </td>
		</tr>
		<tr>
			<td>작성자 :  ${requestScope.content.writer }</td>
			<td> ${requestScope.content.time_post }</td>
			<td>조회수 : ${requestScope.content.hit }</td>
		</tr>
		<tr>
			<td colspan="3">
			<pre>${requestScope.content.content}</pre>
			</td>
		</tr>
		<tr>
		<td valign="middle" align="center" colspan="3">
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<input type="button" value="목록" onclick="sendList()"class="btn btn-info btn-md">
				 	<c:if test="${!empty sessionScope.managerlogin}">
						<input type="button" value="수정" onclick="updateForm()" class="btn btn-info btn-md">
						<input type="button" value="삭제" onclick="deleteForm()" class="btn btn-info btn-md">
					</c:if>
				</div>
			</div>
		</td>				 
		</tr>
	</table> 
</div>