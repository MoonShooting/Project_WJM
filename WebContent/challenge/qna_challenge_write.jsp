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
	<div id="make_room">
		<div class="wrap">
			<h2>방만들기</h2>
			<div class="room">
			
				<form action ="ChallengeWritePro.bo" method="post">
				
				<ul class="mode">
				
					<li>
						<input type="checkbox" name="mode" value="long" id="long" onclick='checkOnlyOne(this)'>
						<label for="long"><span>장기 챌린지<img src="img/week.jpg"></span></label>
					</li>
					<li>
						<input type="checkbox" name="mode" value="short" id="short" onclick='checkOnlyOne(this)'>
						<label for="short"><span>단기 챌린지<img src="img/day.jpg"></span></label>
					</li>
				</ul>
				<ul class="room_info">
					<li>
						<span>챌린지 명</span>
						<input type="text" name="c_subject">
					</li>
					<li>
						<span>세부내용</span>
						<textarea name="c_text" required></textarea>
					</li>
					<li>
						<span>시작일</span>
						<input type="date" name="startday" pattern="yyyy-MM-dd" required>
					</li>
					<li>
						<span>종료일</span>
						<input type="date" name="endday" pattern="yyyy-MM-dd" required>
					</li>
					<li>
						<span>시간</span>
						<input type="time" name="c_time" required>
					</li>
					<li>
						<span>반복 요일</span>
						<label><input type="checkbox" name="day" value="월"/> 월</label>
						<label><input type="checkbox" name="day" value="화"/> 화</label>
						<label><input type="checkbox" name="day" value="수"/> 수</label>
						<label><input type="checkbox" name="day" value="목"/> 목</label>	
						<label><input type="checkbox" name="day" value="금"/> 금</label>
						<label><input type="checkbox" name="day" value="토"/> 토</label>
						<label><input type="checkbox" name="day" value="일"/> 일</label>
					</li>
				</ul>
				<ul class="button">
					<li><input type="submit" value="만들기"/></li>
					<li><input type="reset" value="리셋"/></li>
					<li><input type="button" value="나가기" onclick="history.back()"/></li>
				</ul>
				</form>
			</div>		
		</div>
	</div>
	<script>
	function checkOnlyOne(element) {
		  
		  const checkboxes 
		      = document.getElementsByName("mode");
		  
		  checkboxes.forEach((cb) => {
		    cb.checked = false;
		  })
		  
		  element.checked = true;
		}
	</script>	
	<jsp:include page="/footer.jsp"/>
</body>
</html>