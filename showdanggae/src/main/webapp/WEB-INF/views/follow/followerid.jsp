<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templates/right.jsp"></jsp:include>
<table border='1'>
   <thead>
      <tr>
         <th>ID</th>
      </tr>
   </thead>
   <tbody>
      <c:forEach items="${requestScope.followerList}" var="fvo">
         <tr>
            <td>${fvo.follower}</td>
         </tr>
      </c:forEach>
   </tbody>
</table>