<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
         <script type="text/javascript">
         $(document).ready(function(){
        	 $("#readBtn").click(function(){ 	
        		 $("#messageContent").submit();	
        		 //self.close();
        	  });        	         	 
         });
         </script>

<h3>메세지 내용</h3>

<form class="form-inline" id="messageContent" action="messageRead.do">
<input type="text" class="form-control"  size="30" value="${requestScope.mvo.title }" style="height:30px;" name="title" ><br>
보내는이:<input type="text" value="${requestScope.mvo.spand_name }" 
size="10"  readonly="readonly"  name="spand_name" ><br><br>
<input type="hidden" value="${requestScope.mvo.mno}" name="mno">
<textarea class="form-control"  rows="10" cols="30" name="message" readonly="readonly">${requestScope.mvo.message }</textarea><br>
<input type="submit"  id="readBtn"  value="확인"> 



</form>