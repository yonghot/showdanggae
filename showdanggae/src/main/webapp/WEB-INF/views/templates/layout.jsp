<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="${initParam.root}js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${initParam.root}css/home.css" rel="stylesheet" type="text/css">
    <title>쇼당개 삼년이면 핑월을 읊는다</title>
</head>
<body>
	<div class="wrap" style="background-color: #F9F9F1;">
	    <div id="header"><tiles:insertAttribute name="header"/></div>
	    <div class="section">
	    	<div class="container">
	    		<div class="row">
				    <div id="left"><tiles:insertAttribute name="left"/></div>
				    <div id="main"><tiles:insertAttribute name="main"/></div>
				    <div id="right"><tiles:insertAttribute name="right"/></div>
		    	</div>
		   	</div>
	    </div>
    </div>
	<div id="footer"><tiles:insertAttribute name="footer"/></div>
</body>
</html>






