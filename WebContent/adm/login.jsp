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
	<div>
		<div class="wrap" id="admin_index">
			<div id="admin_login">
				<h3>관리자 로그인 페이지</h3>
				<ul>
					<form name="loginform" action="${pageContext.request.contextPath}/adminLoginAction.admin" method="post">
						<li>
							<label for="admin_id">ID : </label>
							<input type="text" name="admin_id" id="admin_id">
						</li>
						<li>
							<label for="admin_pw">PW : </label>
							<input type="password" name="admin_pw" id="admin_pw">
						</li>
						<li>
							<input type="submit" value="로그인"/>
						</li>
					</form>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>