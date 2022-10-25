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
	<section id = "loginFormArea">
		<div class="loginForm">
			<div class="head">
				<p>내가 만드는 나</p>
       			<h1>우 주 문</h1>
       			<p>p r o j e c t</p>
       		</div>
       			
			<div class="wrap">
				<form name="loginForm" action="${pageContext.request.contextPath }/MyPageLoginAction.me" method="post">
					<ul>
						<li>
							<label for="u_id"> ID &nbsp; </label>
							<input class ="whiteplz"type="text" name="u_id" id="u_id">
						</li>
						<li class="bottom_line">
							<label for="u_pw"> PW </label>
							<input class="whiteplz"type="password" name="u_pw" id="u_pw">
						</li>

						<li><input class="login" type="submit" value="LOGIN"></li>
						<li><a class="join"href="${pageContext.request.contextPath }/MyPageJoinForm.me"><span>JOINUS</span></a></li>
						<li><a class="guest" href="${pageContext.request.contextPath }/ChallengeList.bo"><span>GUEST LOGIN</span></a></li>
					</ul>	
				</form>				
			</div>
			</div>
		<hr>
		<div class="Copyright">
			<ul>
				<li>&nbsp;&nbsp;Copyright 2022 WJM All right reserved</li>
				<li>&nbsp;&nbsp;Donation : Sinhan 111 - 111 -111111</li>
				<li>&nbsp;&nbsp;Address : Daegu Dalseogu Dalgujangdaelo 251 Angil 15</li>
			</ul>
		</div>
	</section>
</body>
</html>