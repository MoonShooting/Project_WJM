<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%
    int c_num = (Integer)request.getAttribute("c_num");

    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<div class="Dttb">
<jsp:include page="/header.jsp"/>
	<div class="passForm">
		<form class="D_form" name="deleteForm" action ="ChallengeDeletePro.bo?c_num=<%=c_num %>" method = "post">
		<div class ="D_full">
		<div class="D_box">
			<div class="D_text">
			<ul>
				
				<li><H2>비밀번호 확인</H2></li>
				</ul>
				<ul>
					<li class="pwcheck"> <input type="password" name="c_pw" id ="c_pw"/></li>
				</ul>
				<br>
				
				<ul>
					<li class="D_button">
					<input type = "submit" value = "삭제"/>
					<br>
					<input type = "button" value = "돌아가기" onclick="javascript:history.go(-1)"/>
					
				</li>
			</ul>
			</div>
		</div>
		</div>
		</form>
	</div>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>




</html>