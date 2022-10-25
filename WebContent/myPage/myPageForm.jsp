<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Arrays" %>
<%@page import="vo.ChallengeBean" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <%
    	Calendar cal = Calendar.getInstance();
    
    	int nowYear = cal.get(Calendar.YEAR);
    	int nowMonth = cal.get(Calendar.MONTH)+1;
    	int nowDate = cal.get(Calendar.DATE);
    	
    	String fyear = request.getParameter("year");
    	String fmonth = request.getParameter("month");
    	
    	int year = nowYear;
    	int month = nowMonth;
    	int day = nowDate;    	
    	
    	if(fyear != null){
    		year = Integer.parseInt(fyear);
    	}
    	
    	if(fmonth != null){
    		month = Integer.parseInt(fmonth);
    	}
    	
    	cal.set(year, month-1, 1);
    	
    	year = cal.get(Calendar.YEAR);
    	month = cal.get(Calendar.MONTH)+1;
    	day = cal.get(Calendar.DATE);
    	
    	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    	int week = cal.get(Calendar.DAY_OF_WEEK);
    	
    	ArrayList<ChallengeBean> schedule = (ArrayList<ChallengeBean>) request.getAttribute("schedule");
    	if(schedule != null && schedule.size() > 0){
	    	int c_num = 0 ;
	    	String u_condition = null;
			String c_subject = null;
			String[] startday = null;
			
			startday = new String[schedule.size()]; 
			
			int[] startdayY = new int[schedule.size()];
			int[] startdayM = new int[schedule.size()]; 
			int[] startdayD = new int[schedule.size()];
			String[] endday = new String[schedule.size()];
			int[] enddayY = new int[schedule.size()];
			int[] enddayM = new int[schedule.size()]; 
			int[] enddayD = new int[schedule.size()];
			String c_time = null;
			String c_day = null;
			String c_condition = null;
	
			for(int i=0; i < schedule.size(); i++){
				c_num = schedule.get(i).getC_num();				
				u_condition = schedule.get(i).getU_condition();
				c_subject = schedule.get(i).getC_subject();
				if(startday!=null){
					if(startday.length < 1 ){
						startday[i] = "";
					}else{
						startday = schedule.get(i).getStartday().split("-");	
					}
				}
				if(endday != null){
					if(endday.length < 1){
						endday[i] = "";
					}else{
						endday = schedule.get(i).getEndday().split("-");
					}
				}
				if(c_time == null || c_time.equals("")){
					c_time = "";
				}else{
					c_time = schedule.get(i).getC_time();
				}
				if(c_day == null || c_day.equals("")){
					c_day = "";
				}else{
					c_day = schedule.get(i).getDay();
				}
				if(c_condition == null || c_condition.equals("")){
					c_condition = "";
				}else{
					c_condition = schedule.get(i).getC_condition();
				}
			
				
				/*년, 월, 일 문자열 정수로 바꾸기*/
				if(startday.length > 1){
					startdayY[i] = Integer.parseInt(startday[0]);
					startdayM[i] = Integer.parseInt(startday[1]);
					startdayD[i] = Integer.parseInt(startday[2]);
					enddayY[i] = Integer.parseInt(endday[0]);
					enddayM[i] = Integer.parseInt(endday[1]);
					enddayD[i] = Integer.parseInt(endday[2]);
				}
				int length = startdayY.length;
			}
		
		
		
		
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage Info</title>
<style>

</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<section id="MyPageInfoArea">
<div>
	<div id="MyPageInfo">
		<div class="wrap">		
			<div class="mypage_info">
				<h3>나의 정보 & 챌린지 확인</h3>
					<ul>
						<li class="profile">
							<ul>
								<c:choose>
									<c:when test="${members.grade eq '성냥' }">
										<img src="img/baby.jpg">
									</c:when>
									<c:when test="${members.grade eq '촛불'}">
										<img src="img/student.jpg">
									</c:when>
									<c:when test="${members.grade eq '횃불'}">
										<img src="img/adult.jpg">
		 							</c:when>
									<c:when test="${members.grade eq '빛'}">
										<img src="img/right.jpg">
									</c:when>
									<c:when test="${members.grade eq '관리자'}">
										<img src="img/ceo.jpg">
									</c:when>
									<c:otherwise>
										<img src="img/guest.jpg">
									</c:otherwise>
								</c:choose>
							</ul>
							<ul>
								<li class="profile_main"><h3>${mypageList.nickname}</h3> <span>${mypageList.grade}</span></li>
								<li>
									<div class="info_box">
										<div class="half"><span>아이디</span> ${mypageList.u_id}</div>
										<div class="half"><span>이름</span> ${mypageList.name}</div>
									</div>
								</li>
								<li>
									<div class="info_box">
										<div class="half"><span>도전항목</span>${mypageList.count} 개</div>
										<div class="half"><span>성공갯수</span>${mypageList.success} 개</div>
									</div>
								</li>
								<li>
									<div class="info_box">
										<c:choose>
											<c:when test="${mypageList.success ne null and mypageList.success ne 0 }">
												<fmt:parseNumber var="success" value="${mypageList.success/mypageList.c_count * 100}" integerOnly="true" />
												<div class="half"><span>성공률</span>${success } %</div>
												<div class="half"><span>랭킹</span>${rank } 위</div>	
											</c:when>
											<c:otherwise>
												<div class="half"><span>성공률</span>0 %</div>
												<div class="half"><span>랭킹</span>- 위</div>
											</c:otherwise>
										</c:choose>
										
									</div>
								</li>
							</ul>
						</li>
						
					</ul>			
					<div class="calendar">
						<div class="cal_box">
							<div class="cal_bt_box">
								<a href="MyPageForm.me?year=<%=year-1 %>&month=<%=month %>"><p>이전년</p></a>
								<a href="MyPageForm.me?year=<%=year %>&month=<%=month-1 %>"><p>이전달</p></a>
								<a href="MyPageForm.me?year=<%=nowYear%>&month=<%=nowMonth%>"><b><%=year %>년&nbsp;&nbsp;<%=month %>월</b></a>
								<a href="MyPageForm.me?year=<%=year %>&month=<%=month+1 %>"><p>다음달</p></a>
								<a href="MyPageForm.me?year=<%=year+1 %>&month=<%=month %>"><p>다음년</p></a>
							</div>
							<div class="cal_content">
								<ul>
									<li class="red">일</li>
									<li>월</li>
									<li>화</li>
									<li>수</li>
									<li>목</li>
									<li>금</li>
									<li class="blue">토</li>
								</ul>					
									<%
										Calendar preCal = (Calendar)cal.clone();
										preCal.add(Calendar.DATE, -(week-1));
									 	int preDate = preCal.get(Calendar.DATE);
									%>
								<ul>
								
									<%
										for(int i=1; i<week; i++) {
									%>	
									<a href="#none"><li class="gray">
										<%=preDate++ %>
										<div class="<%=year%>-<%=month-1%>-<%=i%>"></div>
									</li></a>
									<%} %>
									<%	
										for(int j=1; j<=endDay; j++){
											week++;	
											if(week % 7 == 2){
												if(startdayY.length != 0){
									%>
													<a href="#none"><li class="red">
													<%=j %>
									<%				
													for(int k = 0; k < startdayD.length ; k++){
														if(year == startdayY[k] && month == startdayM[k] && j == startdayD[k]){
									%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">							
																<%=schedule.get(k).getC_subject() %> 시작
															</div>
									<%							
															if(year == nowYear && month == nowMonth && j == nowDate){
									%>
																<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%> today">
																	<%=schedule.get(k).getC_subject() %> 시작
																</div>
									<%							
															}
														}
														if(year == enddayY[k] && month == enddayM[k] && j == enddayD[k]){
									%>
																<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																	<%=schedule.get(k).getC_subject() %> 종료
																</div>
									<%						
															if(year == nowYear && month == nowMonth && j == nowDate){
									%>
																<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%> today">
																	<%=schedule.get(k).getC_subject() %> 종료
																</div>
									<%							
															}
														}
													}
									%>
													</li></a>
									<%										
												}else{
													if(year == nowYear && month == nowMonth && j == nowDate){
									%>
														<a href="#none"><li class="red ">
															<%=j %>
															<div class="<%=year%>-<%=month%>-<%=j%> today"></div>
														</li></a>						
													<%}else{%>
														<a href="#none"><li class="red">
															<%=j %>
															<div class="<%=year%>-<%=month%>-<%=j%>"></div>
														</li></a>
										<%			
													}
												}
											}else if(week % 7 == 1){
												if(startdayY.length > 0){
										%>
													<a href="#none"><li class="blue">
													<%=j %>
										<%			
													for(int k = 0; k < startdayD.length ; k++){
														if(year == startdayY[k] && month == startdayM[k] && j == startdayD[k]){
										%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																<%=schedule.get(k).getC_subject() %> 시작
															</div>
										<%				
															if(year == nowYear && month == nowMonth && j == nowDate){
										%>
																<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%> today">
																	<%=schedule.get(k).getC_subject() %> 시작
																</div>
										<%						
															}
														}
														if(year == enddayY[k] && month == enddayM[k] && j == enddayD[k]){
										%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																<%=schedule.get(k).getC_subject() %> 종료
															</div>
										<%					
															if(year == nowYear && month == nowMonth && j == nowDate){
										%>
																<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%> today">
																	<%=schedule.get(k).getC_subject() %> 종료
																</div>
										<%						
															}
														}
													}
										%>
													</li></a>
										<%
												}else{
													if(year == nowYear && month == nowMonth && j == nowDate){
										%>
															<a href="#none"><li class="blue today">
																<%=j %>
																<div class="<%=year%>-<%=month%>-<%=j%>"></div>
															</li></a>
																			
													<%}else{%>
														<a href="#none"><li class="blue">
															<%=j %>
															<div class="<%=year%>-<%=month%>-<%=j%>"></div>
														</li></a>
									<%				
													}
												}
											}else if(year == nowYear && month == nowMonth && j == nowDate){
												
									%>
												<a href="#none"><li class="today">
												<%=j %>
									<%			
												if(startdayD.length > 0){
									%>

									<%				
													for(int k = 0; k < startdayD.length; k++){
														if(year == startdayY[k] && month == startdayM[k] && j == startdayD[k]){
									%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																	<%=schedule.get(k).getC_subject() %> 시작
															</div>
									<%						
														}
														if(year == enddayY[k] && month == enddayM[k] && j == enddayD[k]){
									%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																<%=schedule.get(k).getC_subject() %> 종료
															</div>
									<%						
														}
												}
									%>
													</li></a>
									<%
												}else{
									%>
												</li></a>
											<%}}else{ 	
												if(startdayD.length > 0){
									%>
									
													<a href="#none"><li class="black">
														<%=j %>	
									<%				
													for(int k = 0; k < startdayD.length; k++){
														if(year == startdayY[k] && month == startdayM[k] && j == startdayD[k]){
									%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																<%=schedule.get(k).getC_subject() %> 시작
															</div>
																
									<%							
															}
														if(year == enddayY[k] && month == enddayM[k] && j == enddayD[k]){
									%>
															<div class="<%=year%>-<%=month%>-<%=j%> plan<%=k%>">
																<%=schedule.get(k).getC_subject() %> 종료
															</div>
									<%						
														}
													}
												
									%>
												</li></a>
						<%			}else{
						%>			
									<a href="#none"><li class="black">
										<%=j %>	
										<div class="<%=year%>_<%=month%>_<%=j%>"></div>
									</li></a>
						<%			
										}
								}
							}
						%>
						<%
							int n = 1;
							for(int i= (week-1)%7; i<7; i++) {
						%>	
							<a href="#none"><li class="gray">
								<%=n++ %>
								<div class="<%=year%>_<%=month+1%>_<%=i%>"></div>
							</li></a>
						<%}%>
									</ul>
								</div>
							</div>
						</div>
						<%
						}else{
						%>
						<style>
						</style>
						<jsp:include page="../header.jsp"/>
						<section id="MyPageInfoArea">
							<div>
								<div id="MyPageInfo">
									<div class="wrap">		
										<div class="mypage_info">
											<h3>나의 정보 & 챌린지 확인</h3>
												<ul>
													<li class="profile">
														<ul>
															<c:choose>
																<c:when test="${members.grade eq '성냥' }">
																	<img src="img/baby.jpg">
																</c:when>
																<c:when test="${members.grade eq '촛불'}">
																	<img src="img/student.jpg">
																</c:when>
																<c:when test="${members.grade eq '횃불'}">
																	<img src="img/adult.jpg">
																</c:when>
																<c:when test="${members.grade eq '빛'}">
																	<img src="img/right.jpg">
																</c:when>
																<c:when test="${members.grade eq '관리자'}">
																	<img src="img/ceo.jpg">
																</c:when>
																<c:otherwise>
																	<img src="img/guest.jpg">
																</c:otherwise>
															</c:choose>
														</ul>
														<ul>
															<li class="profile_main"><h3>${mypageList.nickname}</h3> <span>${mypageList.grade }</span></li>
															<li>
																<div class="info_box">
																	<div class="half"><span>아이디</span> ${mypageList.u_id}</div>
																	<div class="half"><span>이름</span> ${mypageList.name}</div>
																</div>
															</li>
															<li>
																<div class="info_box">
																	<div class="half"><span>도전항목</span>${mypageList.count } 개</div>
																	<div class="half"><span>성공갯수</span>${mypageList.success } 개</div>
																</div>
															</li>
															<li>
																<div class="info_box">
																<c:choose>
																	<c:when test="${mypageList.success ne null and mypageList.success ne 0 }">
																		<fmt:parseNumber var="success" value="${mypageList.success/mypageList.c_count * 100}" integerOnly="true" />
																		<div class="half"><span>성공률</span>${success } %</div>
																		<div class="half"><span>랭킹</span>${rank } 위</div>	
																	</c:when>
																	<c:otherwise>
																		<div class="half"><span>성공률</span>0 %</div>
																		<div class="half"><span>랭킹</span>- 위</div>
																	</c:otherwise>
																</c:choose>
																</div>
															</li>
														</ul>
													</li>
													
												</ul>
											</div>
										</div>
									</div>
								</div>
						</section>
						<jsp:include page="../calendar.jsp"/>
					<%}%>

			</div>
		</div>
	</div>
</div>
</section>
<jsp:include page="../footer.jsp"/>
</body>
</html>