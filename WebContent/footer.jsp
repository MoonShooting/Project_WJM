<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/64669cc033.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<div>
	<div id="footer">
	 	<div class="wrap">
	 		<ul>
	 			<a href="${pageContext.request.contextPath}/ChallengeList.bo">
					<li><i class="fa-solid fa-hand-fist"></i> </li>
					<li>Challenge</li>
				</a>
			</ul>
			<ul>
				<a href="${pageContext.request.contextPath}/rank.ra">
					<li><i class="fa-solid fa-ranking-star"></i></i></li>
					<li>Rank</li>
				</a>
			</ul>
			<ul>
				<c:choose>
					<c:when test="${sessionScope.id ne null }">
						<a href="${pageContext.request.contextPath }/MyPageForm.me?id=${sessionScope.id}">
							<li><i class="fa-solid fa-address-card"></i></li>
							<li> MyPage</li>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/MyPageLogin.me">
							<li><i class="fa-solid fa-address-card"></i></li>
							<li>MyPage</li>
						</a>
					</c:otherwise>
				</c:choose>
			</ul>	
			<ul>		
				<c:choose>
					<c:when test="${sessionScope.id eq 'admin' }">
						<a href="${pageContext.request.contextPath }/index.admin">
							<li><i class="fa-solid fa-user-tie"></i></li>
							<li>manager</li>
						</a>
					</c:when>
					<c:when test="${sessionScope.id ne null }">
						<a href="${pageContext.request.contextPath }/MyInfomation.me">
							<li><i class="fa-solid fa-user-tie"></i></li>
							<li>Infomation</li>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/MyPageLoginForm.me">
							<li><i class="fa-solid fa-comment"></i></i></li>
							<li>Login</li>
						</a>	
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>

</body>
</html>