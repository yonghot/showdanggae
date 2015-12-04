<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    //noticeWrBtn
    $(document).ready(function() {
    	
    	$("#noticeWrBtn").click(function(){
    		location.href="noticeWritecancel.do";
    	});
   	
    });
    </script>
    	<div class="col-md-7">
    
 <form action="${initParam.root}write.do"  method="post" name="write_form">  
   <table class="table" >
    <caption>글쓰기</caption>
    <tbody>
    <tr>
     <td>제목</td>

     <td colspan="3">
     <input type="text" name="title" size="48">
     </td>
    </tr>
    <tr>
     <td>이름</td>
     <td><input type="text" name="writer" size="20" value="${sessionScope.managerlogin.member_name}" readonly="readonly"></td>  
    </tr>
    <tr>
     <td colspan="4" align="left">
     &nbsp;&nbsp;
     <textarea cols="53" rows="15" name="content">
     </textarea>
     
  <br>
  
		
	 <input type="button" value="취소" id="noticeWrBtn" class="btn btn-info btn-lg">
     <input type="submit" value="글쓰기" id="subBtn" class="btn btn-info btn-lg">
     
     </td>
    </tr> 
    <tr>
     <td colspan="4" align="center" >

      
      
     </td>  
    </tr>
    </tbody>
   </table>
  </form>
    
    	</div>