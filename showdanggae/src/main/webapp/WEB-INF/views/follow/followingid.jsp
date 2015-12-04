<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table border='1'>
   <thead>
      <tr>
         <th>ID</th>
      </tr>
   </thead>
   <tbody>
      <c:forEach items="${requestScope.fvoList}" var="fvo">
         <tr>
            <td>${fvo.following}</td>
         </tr>
      </c:forEach>
   </tbody>
</table>