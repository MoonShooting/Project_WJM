<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
	<c:set var="member" value="${members}"/>	
	<c:choose>
		<c:when test="${member eq null }">
			<div class="guest">
				<img src="img/guest.jpg"><a href="loginForm.jsp"><h1>로그인을 해 주세요</h1></a>
				
			</div>
		</c:when>
		<c:otherwise>
			<div id="header">
				<div class="wrap">
					<div class="headerbox">
						<ul class="imgbox">
							<li>
								<c:choose>
									<c:when test="${member.grade eq '성냥' }">
										<img src="img/baby.jpg">
									</c:when>
									<c:when test="${member.grade eq '촛불'}">
										<img src="img/student.jpg">
									</c:when>
									<c:when test="${member.grade eq '횃불'}">
										<img src="img/adult.jpg">
									</c:when>
									<c:when test="${member.grade eq '빛'}">
										<img src="img/right.jpg">
									</c:when>
									<c:when test="${member.grade eq '관리자'}">
										<img src="img/ceo.jpg">
									</c:when>
									<c:otherwise>
										<img src="img/guest.jpg">
									</c:otherwise>
								</c:choose>
							</li>
							<li><b>${member.nickname}</b></li>
						</ul>
						<ul>
							<li><i class="fa-solid fa-graduation-cap"></i>&nbsp;&nbsp;${member.grade }</li>
							<li><i class="fa-solid fa-star"></i>&nbsp;&nbsp;${member.c_score }<b>점</b></li>
							<a href="${pageContext.request.contextPath}/MyPageLogout.me"><li><i class="fa-solid fa-toggle-off"></i>&nbsp;&nbsp;out</li></a>
						</ul>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>