<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.id eq null }">
	<script>
		alert('로그인이 필요합니다..');
		location.href="${pageContext.request.contextPath}/MyPageLoginForm.me";
	</script>
</c:if>
</body>
</html>