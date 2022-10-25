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
<jsp:include page="../header.jsp"/>
	<div>
		<div id="survey">
			<div class="wrap">
				<div class="header">
					<h2>설문조사</h2>
				</div>
				<div class="main">
					<form id="MyForm" action="${pageContext.request.contextPath}/surveyProAction.survey?c_num=${c_num}" method="post">
				<div class="starPoint">
					    <fieldset>
					        <legend>챌린지 만족도 </legend>
					        <input type="radio"  value="5" name="satisfaction" id="satisfaction1"><label for="satisfaction1">⭐</label>
					        <input type="radio"  value="4" name="satisfaction" id="satisfaction2"><label for="satisfaction2">⭐</label>
					        <input type="radio"  value="3" name="satisfaction" id="satisfaction3"><label for="satisfaction3">⭐</label>
					        <input type="radio"  value="2" name="satisfaction" id="satisfaction4"><label for="satisfaction4">⭐</label>
					        <input type="radio"  value="1" name="satisfaction" id="satisfaction5"><label for="satisfaction5">⭐</label>
					        <input type="hidden" value="${c_num }"/>
					    </fieldset>
					    
					    <fieldset>
					        <legend>챌린지 유용성</legend>
					        <input type="radio" name="experience" value="5" id="experience1"><label for="experience1">⭐</label>
					        <input type="radio" name="experience" value="4" id="experience2"><label for="experience2">⭐</label>
					        <input type="radio" name="experience" value="3" id="experience3"><label for="experience3">⭐</label>
					        <input type="radio" name="experience" value="2" id="experience4"><label for="experience4">⭐</label>
					        <input type="radio" name="experience" value="1" id="experience5"><label for="experience5">⭐</label>
					    </fieldset>
					   
				 </div>
							<ul>
								<li><input type="submit" value="제출하기"></li>
							</ul>
				
					</form>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>