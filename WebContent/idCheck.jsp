<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body onload="init()">
<div id="idCheck">
	<div class="wrap">
		<div class="bod">
		<form  action="${pageContext.request.contextPath}/idCheck.me" method="post">
			<ul>
	   			<li>아이디 중복 확인</li>
	   			<li><input type="text" name="idCheck" id="idCheck"/></li>
		       	<li>닉네임 중복 확인</li>
		       	<li><input type="text" name="nickname" id="nickname"/></li>
		     	<li><input type="submit" value="중복확인"/></li> 
		   </ul>
	   </form>
   </div>
   </div>
</div>
</body>
</html>