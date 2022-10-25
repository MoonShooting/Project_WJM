<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<!DOCTYPE html>
<html>
<head>
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
<meta charset="UTF-8">
<title>rank page</title>
</head>
<body>
   <jsp:include page="/header.jsp"/>
   <section id="RankAera">
      <div>
         <div class="wrap">
            <div class="rank">
            	<div class="main_box">
            		<h1>Top 10</h1>
            		<ul class="otherClass">
            			<div class="bbox">
	            		<c:forEach var="list" items="${rb}" varStatus="i">
	            			<c:if test="${i.count < 11}">
	            			<div class="rank_in">
	            				<div class="inbox">
									
									<c:choose>
									<c:when test="${i.count <= 3}">
									<li id="ranking" class="r${i.count}">
										${list.ranking}<span id="icon${i.count}" style="font-size: 100%;" class="material-icons">workspace_premium</span></li>
										<li id="user" class="id${i.count}"><a href="#">${list.u_id} ${list.nickname}</a></li>
									</c:when>
									<c:otherwise>
										<li id="ranking" class="r${i.count}">${list.ranking}</li>
										<li id="user" class="id${i.count}"><a href="#">${list.u_id} ${list.nickname}</a></li>
									</c:otherwise>
									</c:choose>
									
		                        </div>
		                           <li class="grade${i.count}">${list.grade} </li> 
		                           <li class="c_score${i.count}"> ${list.c_score}</li>
		                    </div>	
	            			</c:if>
	            		</c:forEach>
	            		</div>
            		</ul>
            	</div>   
            </div>
         </div>
   </section>
   <jsp:include page="/footer.jsp"/>
</body>
</html>