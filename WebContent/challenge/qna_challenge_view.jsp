<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.ChallengeBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date, java.time.*, java.time.format.*"%>
<%@ page import="challenge.svc.ChallengeDetailService"%>

<%
	Calendar cal = Calendar.getInstance();

int DayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

String korDayOfWeek = "";
switch (DayOfWeek) {
case 1:
	korDayOfWeek = "일";
	break;
case 2:
	korDayOfWeek = "월";
	break;
case 3:
	korDayOfWeek = "화";
	break;
case 4:
	korDayOfWeek = "수";
	break;
case 5:
	korDayOfWeek = "목";
	break;
case 6:
	korDayOfWeek = "금";
	break;
case 7:
	korDayOfWeek = "토";
	break;

}
pageContext.setAttribute("korDayOfWeek", korDayOfWeek);

int compareTime1 = cal.get(Calendar.HOUR);
int compareTime2 = cal.get(Calendar.MINUTE);

LocalDateTime nowtime = LocalDateTime.now();
LocalDate nowday = nowtime.toLocalDate();

pageContext.setAttribute("nowday", nowday);

String c_time = (String) request.getAttribute("c_time");
System.out.println("c_time = " + c_time);

LocalTime thistime = LocalTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
thistime = LocalTime.of(thistime.getHour(), thistime.getMinute());

LocalTime time = LocalTime.parse(c_time);
LocalTime Etime = time.plusHours(1);
LocalTime Stime = time.minusHours(1);
pageContext.setAttribute("Stime", Stime);
pageContext.setAttribute("Etime", Etime);
pageContext.setAttribute("thistime", thistime);

boolean Before = false;
Before = Stime.isBefore(thistime);
System.out.println(Before + "=Before");

boolean After = false;
After = thistime.isBefore(Etime);
System.out.println(After + "=After");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
	
	<jsp:include page="/header.jsp" />
	<div class="wrap">
	<div id="View_index">
		<div id="ChallengeViewArea">
			<div class="articleTextArea">

				<ul class="V_titleul">
					<li class="V_title"><p>${article.c_subject }</p></li>
							
							
							
							
					<ul>
						<li class="V_title_button"><a class="button"
							href="ChallengeModifyForm.bo?c_num=${article.c_num}&c_id=${sessionScope.id}">
								<i class="fa-solid fa-wrench Tlqkf"></i></a>
						</li>
						<li class="V_title_button"><a class="button"
							href="ChallengeDeleteForm.bo?c_num=${article.c_num}&c_id=${sessionScope.id}">
								<i class="fa-solid fa-trash-can Tlqkf"></i></a>
						</li>
					</ul>
				</ul>
				
				
				<ul><li><img class ="img_rest"src="img/view.png"></li></ul>
				
				
				<ul class="V_button">
						<c:choose>
							<c:when test="${sessionScope.id eq null }">											
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${attend != false}">
										<li class="V_etc">
											<a class="whiteplz" href="${pageContext.request.contextPath}/ChallengeGiveup.bo?c_num=${article.c_num}&id=${sessionScope.id}">포기하기</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="V_etc">
											<a class="whiteplz" href="${pageContext.request.contextPath}/ChallengeParticip.bo?c_num=${article.c_num}&id=${sessionScope.id}">참여하기</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
							<c:set var ="timetest" value="${false }"/>
							<c:choose>
								<c:when test="${article.startday le nowday and nowday le article.endday }">
											
												<c:set var="day0" value="${fn:substringAfter(article.day, '[')}"/>
												<c:set var="day1" value="${fn:substringBefore(day0, ']')}"/>
												<c:set var="day" value="${fn:split(day1, ', ') }"/>
											<c:forEach var="i" begin="0" end="${fn:length(day)}">
												<c:choose>
													<c:when test="${day[i] == fn:trim(korDayOfWeek)}">
														<c:set var="Before" value="<%=Before%>"></c:set>
														<c:set var="After" value="<%=After%>"></c:set>
															<c:choose>
																<c:when test="${Before and After}">
																	<c:set var="timetest" value="${true }"/>
																</c:when>
																<c:otherwise>
																</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											</c:when>
											<c:otherwise>
											</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${sessionScope.id eq null }">
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${timetest eq true }">
											<li class="V_etc">
												<a href="${pageContext.request.contextPath}/ChallengeMission.bo?c_num=${article.c_num}">미션완료 </a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="V_etc">
												준비중
											</li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>	
					</ul>	
				<div class="V_body">
				<ul class="Vul_date">
					<li class="V_period">${article.startday } ~ ${article.endday }</li>
					<li class="V_day">${article.day }</li>
					<li class="Vc_time">${c_time }</li>
				</ul>
				<div class="flexflex">
				<ul class="V_ultext">
					<li class="V_text">${article.c_text } </li>
				</ul>
				
				</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>
