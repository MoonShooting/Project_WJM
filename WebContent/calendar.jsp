<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Calendar" %>
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
    %>
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML.4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>달력</title>
<style>
/*cal_box css*/
	#calendar{background:#e1e1e1;}
	.cal_box{margin: 0 auto; padding: 50px 10p;}
	.cal_bt_box{display: flex;justify-content: space-evenly;align-items: center; padding: 20px;}
	.cal_bt_box a p{ font-size: 25px; background: #c9c2d1; box-shadow: 5px 5px 5px #393949; border-radius: 22px; padding: 15px;}
	.cal_bt_box a b{font-size: 25px; background: #c9c2d1; box-shadow: 5px 5px 5px #393949; border-radius: 22px; padding: 15px;}
	.cal_content ul{display: flex; flex-wrap: wrap;}
	.cal_content ul:first-child{border-bottom: 5px solid #000;flex-wrap: nowrap;}
	.cal_content ul:first-child li{width: 14.28%; text-align: center; font-size: 30px;}
	.cal_content ul a{width: 14.28%; text-align: center; font-size: 20px;}
	.cal_content ul:nth-child(2) li{height: 150px; font-size: 25px;border: 2px solid #eee; font-weight: 700;}
	.red{color: red;}
	.blue{color: blue;}
	.gray{color: gray;}
	#calendar .today{border:2px solid yellow}
</style>
</head>
<body>
	<div id="calendar">
		<div class="wrap">
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
							<div class="<%=year%>_<%=month-1%>_<%=i%>"></div>
						</li></a>
						<%} %>
						<%
							for(int j=1; j<=endDay; j++){
								week++;
								if(week % 7 == 2){
									if(year == nowYear && month == nowMonth && j == nowDate){
						%>
										<a href="#none"><li class="red today">
											<%=j %>
											<div class="<%=year%>_<%=month%>_<%=j%>"></div>
										</li></a>						
									<%}else{%>
										<a href="#none"><li class="red">
											<%=j %>
											<div class="<%=year%>_<%=month%>_<%=j%>"></div>
										</li></a>
						<%			
									}
								}else if(week % 7 == 1){
									if(year == nowYear && month == nowMonth && j == nowDate){
						%>
											<a href="#none"><li class="blue today">
												<%=j %>
												<div class="<%=year%>_<%=month%>_<%=j%>"></div>
											</li></a>
															
									<%}else{%>
										<a href="#none"><li class="blue">
											<%=j %>
											<div class="<%=year%>_<%=month%>_<%=j%>"></div>
										</li></a>
						<%			}			
								}else if(year == nowYear && month == nowMonth && j == nowDate){
						%>
									<a href="#none"><li class="today">
										<%=j %>
										<div class="<%=year%>_<%=month%>_<%=j%>"></div>
									</li></a>
								<%}else{ %>	
									<a href="#none"><li class="black">
										<%=j %>
										<div class="<%=year%>_<%=month%>_<%=j%>"></div>
									</li></a>
						<%			
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
						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>