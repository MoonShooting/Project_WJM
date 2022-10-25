<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 error page</title>
<style>
	h3{color: #ccc;}
	#err{width: 100%;}
	#btn{border: 2px outset #4996fe; background-color: rgba(0,0,0,0); color: #4996fe; padding: 10px; border-radius:10px; margin-right: 4px;}
</style>
</head>
<body>
	<h3>시스템이 정상적으로 동작하지 않습니다.</h3>
	<input id="btn" type="button" value="돌아가기" onclick="window.history.back();">
	<img id="err" src="../img/404_img.jpg">
	<!--  alt="<a href="https://www.freepik.com/free-vector/page-found-concept-illustration_7887410.htm#query=404%20page&position=23&from_view=keyword">Image by storyset on Freepik</a> -->	
</body>
</html>