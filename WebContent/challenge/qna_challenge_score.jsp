<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<jsp:include page="/header.jsp"/>
<div>
	<div id="score">
		<div class="wrap">
			<ul>
				<li>
					<c:set var="mission" value="${fn:length(mission)}"/>
					<h1>${sessionScope.id }님 총점수 : ${mission * 15 }점</h1>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/MyPageLoginForm.me">로그인으로 돌아가기</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>