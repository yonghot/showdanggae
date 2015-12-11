<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String cookieName = "myCookie";
	Cookie cookie = new Cookie(cookieName, "Apple");
	cookie.setMaxAge(60);
	cookie.setValue("Melone"); //생성된 Cookie 객체의 값을 "Melone"이란 값으로 재 설정
	response.addCookie(cookie); //웹 브라우저(클라이언트)로 생성된 쿠키를 전송
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<P>
		쿠키가 생성 되었습니다.<br> 쿠키 내용은 <a href="takeCookie.jsp">여기로</a>!!!
	</P>

</body>
</html>