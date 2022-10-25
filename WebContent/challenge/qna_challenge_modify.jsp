<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "vo.ChallengeBean" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="/header.jsp"/>
	<div id="ChallengeModifyArea">
		<div class="wrap">
			<form action ="ChallengeModifyPro.bo?page=${page}" method="post"name="modifyform">
			<input type="hidden" name ="c_num" value="${article.c_num }"/>
			<h2>수정하기</h2>
			<div class="modify">
				<ul class="mode">
						<li>
							<input type="checkbox" name="mode" value="long" id="long" onclick='checkOnlyOne(this)' <c:if test="${article.c_mode eq 'long' }">checked</c:if>>
							<label for="long"><span>장기 챌린지<img src="img/week.jpg"></span></label>
						</li>
						<li>
							<input type="checkbox" name="mode" value="short" id="short" onclick='checkOnlyOne(this)' <c:if test="${article.c_mode eq 'short' }">checked</c:if>>
							<label for="short"><span>단기 챌린지<img src="img/day.jpg"></span></label>
						</li>
					</ul>
				
				<ul class="room_info">
					<li>
						<span>방장</span>
						<input type="text" name="c_id" readonly value="${article.c_id }">
					</li>
					<li>
						<span>챌린지 명</span>
						<input type="text" name="c_subject" value="${article.c_subject }">
					</li>
					<li>
						<span>세부내용</span>
						<textarea name="c_text" required>${article.c_text }</textarea>
					</li>
					<li>
						<span>시작일</span>
						<input type="date" name="startday" pattern="yyyy-MM-dd" value="${article.startday }" required>
					</li>
					<li>
						<span>종료일</span>
						<input type="date" name="endday" pattern="yyyy-MM-dd" value="${article.endday }" required>
					</li>
					<li>
						<span>시간</span>
						<input type="time" name="c_time" value="${article.c_time }" required>
					</li>
					<li>
						
						<span>반복 요일</span>
						<c:set var="day0" value="${fn:substringAfter(article.day, '[')}"/>
						<c:set var="day1" value="${fn:substringBefore(day0, ']')}"/>
						<c:set var="day" value="${fn:split(day1, ', ') }"/>
						<c:forEach var="i" begin="0" end="${fn:length(day)}" >
							<c:if test="${day[i] eq '월' }">
								<c:set var="mon" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '화' }">
								<c:set var="tue" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '수' }">
								<c:set var="wed" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '목' }">
								<c:set var="thur" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '금' }">
								<c:set var="fri" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '토' }">
								<c:set var="sat" value="true"/>
							</c:if>
							<c:if test="${day[i] eq '일' }">
								<c:set var="sun" value="true"/>
							</c:if>
						</c:forEach>
							<label><input type="checkbox" name="day" value="월" <c:if test="${mon == 'true' }">checked</c:if>> 월</label>
							<label><input type="checkbox" name="day" value="화" <c:if test="${tue == 'true' }">checked</c:if>> 화</label>
							<label><input type="checkbox" name="day" value="수" <c:if test="${wed == 'true' }">checked</c:if>> 수</label>
							<label><input type="checkbox" name="day" value="목" <c:if test="${thur == 'true' }">checked</c:if>> 목</label>	
							<label><input type="checkbox" name="day" value="금" <c:if test="${fri == 'true' }">checked</c:if>> 금</label>
							<label><input type="checkbox" name="day" value="토" <c:if test="${sat == 'true' }">checked</c:if>> 토</label>
							<label><input type="checkbox" name="day" value="일" <c:if test="${sun == 'true' }">checked</c:if>> 일</label>
					</li>
				</ul>
				<ul class="button">
					<li><input type="submit" value="수정하기"/></li>
					<li><input type="reset" value="리셋"/></li>
					<li><input type="button" value="취소하기" onclick="history.back()"/></li>
				</ul>
			</div>
			</form>
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