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
<c:if test="${sessionScope.id ne 'admin' }">
	<script>
		alert('관리자만 로그인이 가능합니다.');
		location.href="${pageContext.request.contextPath}/login.admin";
	</script>
</c:if>

<%-- <c:if test="${member.grade ne '등급' }">
	<script>
		alert('권한x');
		location.href="${pageContext.request.contextPath }/memberLogin.me";
	</script>
	 </c:if> --%>
</body>
</html>