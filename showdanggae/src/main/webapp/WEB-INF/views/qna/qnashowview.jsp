<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

function sendList(){
	location.href="${initParam.root}qnaboard.do";
}	

function updateForm(){
	var result = confirm('수정하시겠습니까??');
    if(result) {
		location.href="${initParam.root}qnaUpdateForm.do?no=${requestScope.content.no}";
    } else {
    	return false;
    	//home으로 이동
    }
}	

function deleteForm(){
	var result = confirm('삭제하시겠습니까??');
    if(result) {
		location.href="${initParam.root}qnaDelete.do?no=${requestScope.content.no}";	
    } else {
    	return false;
    }
}	

function replyForm(){
	var result = confirm('답글을 다시겠습니까???');
    if(result){
		location.href="${initParam.root}replyView.do?no=${requestScope.content.no}";	
    } else {
    	return false;
    }
	
}

function comment(){
	var result = confirm('댓글을 입력 하시겠습니까???');
    if(result){
		return true;
    } else {
    	return false;
    }
	
}

</script>

<div class="col-md-8">
<form action="comment.do">
    <table class="table">
    		<tr>
		<td>NO : ${requestScope.content.no} </td> 
			<td colspan="2">${requestScope.content.title} </td>
		</tr>
		<tr>
			<td>작성자 :  ${requestScope.content.writer }</td>
			<td> ${requestScope.content.writeDate }</td>
			<td>조회수 : ${requestScope.content.viewCount }</td>
		</tr>
		<tr>
			<td colspan="3">
			<pre>${requestScope.content.content}</pre>
			</td>
		</tr>
		<tr>
	 	<td colspan="2"><textarea cols="90" rows="3" name="replyComment"></textarea></td>  
			<td>	<input type="submit" value="댓글달기" onclick="comment()" class="btn btn-info btn-md"
			 style="WIDTH: 60pt; HEIGHT: 50pt"></td>
		</tr>
		<tr>
		<td valign="middle" align="center" colspan="3">
			<div class="form-group">
				<div class="col-sm-12 text-center">
					<input type="button" value="목록" onclick="sendList()"class="btn btn-info btn-md">
					
				 	<c:if test="${sessionScope.mvo.member_id==requestScope.content.member_id}">
						<input type="button" value="수정" onclick="updateForm()" class="btn btn-info btn-md">
						<input type="button" value="삭제" onclick="deleteForm()" class="btn btn-info btn-md">
					</c:if>
					
					<c:if test="${!empty sessionScope.managerlogin}">					
					<input type="button" value="답글달기" onclick="replyForm()" class="btn btn-info btn-md">
					<input type="button" value="삭제" onclick="deleteForm()" class="btn btn-info btn-md">
					</c:if>
					
			 <input type="hidden" name="no" value="${requestScope.content.no}">
				</div>
			</div>
		</td>				 
		</tr>
    </table>
    </form>

</div>
