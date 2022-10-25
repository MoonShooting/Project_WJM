<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>

	<div id="joinForm_box">
		<div class="wrap">
			<div class="head">
				<p>내가 만드는 나</p>	
       			<h1>우 주 문</h1>
       			<p>p r o j e c t</p>
       		</div>
       		
       		<hr>
			<div class="join_box">
			
			
				<h2>  </h2>
				<form action="${pageContext.request.contextPath}/MyPageJoinAction.me" method="post" name="joinForm" id="joinForm">
					<ul class="require">
						<li class="double">
						<%
						String id = null;
						if(request.getParameter("id") != null){
							id = request.getParameter("id");
						%>
						
							<input type="text" name="id" id="id" required placeholder="아이디" value="<%=id%>"readonly/>
						<%}else{ %>
							<input type="text" name="id" id="id" required placeholder="아이디" "readonly/>
						<%} %>	
							<label for="id"><input type="button" value="중복확인" id="idCheck" onclick="location.href='idCheck.jsp?openInit=true'"/></label>
						</li>
						<li class="alone">
						<%
							String nickname = null;
							if(request.getParameter("nickname") != null){
								nickname = request.getParameter("nickname");
						%>
							<input type="text" name="nickname" id="nickname" required placeholder="닉네임" value="<%=nickname%>"/>
						<%}else{ %>	
							<input type="text" name="nickname" id="nickname" required placeholder="닉네임" readonly/>
						<%} %>
						</li>
						<li class="alone">
							<input type="password" name="pw" id="pw"  required placeholder="비밀번호"/>
						</li>
						<li class="double">
							<input type="password" name="pw1" id="pw1" required placeholder="비밀번호 확인"/>
							<label for="pw1"><input type="button" value="확인" onclick="checkPassword()"/></label>
						</li>
						<li class="alone">
							<input type="text" name="name" id="name" required  placeholder="이름"/>
						</li>
						
						<li class="alone">
							<input type="text" name="tel" id="tel" required placeholder="전화번호" />
						</li>
						<li class="alone">
							<input type="text" name="birth" id="birth" required  placeholder="생년월일"/>
						</li>
						<li class="alone">
							<input type="submit" value="회원가입" onclick="check_form()"/>
						</li>
					</ul>	
				</form>
			</div>
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

</body>
<script>
	var all_check = false;
	var id_check = false;
	var pw_check = false;

	function checkPassword(){
	var pw1 = document.getElementById('pw').value;
	var pw2 = document.getElementById('pw1').value;
	if(pw1.length < 6 || pw1.length > 20) {
        alert('입력한 글자가 6자 이상이어야 하고, 20자 이하여야 합니다.');
        pw_check = false;
    }
    
	if(pw1 != pw2){
	      alert('비밀번호가 일치하지 않습니다. 다시 작성하세요.');
	      pw_check = false;
	   }else{
	      pw_check = true;
	   }
   }
	
	function check_form(){
		var id = document.getElementById('id').value;
		if(id.length <= 0){
			alert('아이디를 입력 해 주세요');
			id_check = false;
		}else{
			id_check = true;
		}
		
			
		if(id_check == true && pw_check == true){
			all_check = true;
			form.submit();
		}else{
			alert('아이디 중복확인과 비밀번호가 일치 해야 합니다.');
		}
	}
	
</script>
</html>