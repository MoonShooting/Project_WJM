<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div id="myInfo">
			<div class="wrap">
				<div class="myInfo_box">
					<h2>개인정보</h2>
					<form action="${pageContext.request.contextPath}/MyInfoModify.me">
						<ul>
							<li>
								<span>아이디 </span> ${memberInfo.u_id }
								<input type="hidden" value="${memberInfo.u_id }" name="id"/>
							</li>
							<li>
								<span>비밀번호 </span>
								<input type="password" value="${memberInfo.u_pw }" name="pw"/>
							</li>
							<li>
								<span>이름 </span>${memberInfo.name }
								<input type="hidden" value="${memberInfo.name }" name="name"/>
							</li>
							<li>
								<span>닉네임 </span>
								<input type="text" value="${memberInfo.nickname }" name="nickname"/>
							</li>
							<li>
								<span>전화번호 </span>
								<input type="text" value="${memberInfo.tel }" name="tel"/>
							</li>
							<li>
								<span>생년월일 </span>${memberInfo.birth }
								<input type="hidden" value="${memberInfo.birth}" name="birth"/>
							</li>
							<li><input type="submit" value="수정하기"/></li>							
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>