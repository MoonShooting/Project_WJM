<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "vo.PageInfo"%>
<%@ page import = "vo.ChallengeBean" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Challenge List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <c:set var="listCount" value="${pageInfo.listCount}"/>
    <c:set var="nowPage" value="${pageInfo.page }"/>
    <c:set var="maxPage" value="${pageInfo.maxPage }"/>
    <c:set var="startPage" value="${pageInfo.startPage }"/>
    <c:set var="endPage" value="${pageInfo.endPage }"/>
    <jsp:include page="/header.jsp"/>
	<div id="list">
		<section id ="listForm"> 	
			<div class="mfc">
				<div class="write_box">
					<c:choose>
					<c:when test="${members.grade eq '성냥' || sessionScope.id eq null}">
						
					</c:when>
					<c:otherwise>
						<a href="ChallengeWriteForm.bo"class="write">방만들기</a>
					</c:otherwise>
				</c:choose>
					<a href="ChallengeScore.bo">점수받기</a>
				</div>
				<div class="challenge_search">
					<form action="${pageContext.request.contextPath}/ChallengeList.bo">	
						<select name="challenge_menu" id="challenge_menu">
							<option value="c_num">번호</option>
							<option value="c_subject">제목</option>
							<option value="c_id">작성자</option>
							<option value="startday">시작일시</option>
						</select>
						<input type="text" name="challenge_search"/>
						<input type="submit" value="검색"/>
					</form>
				</div>
			</div>
				<div class="challenge_list">
				
					<ul class="challenge_box">
						<c:choose>
							<c:when test="${articleList != null && listCount > 0}">
								<c:forEach var="list" items="${articleList}" >
									<li class="listflex">
										<c:choose>
											<c:when test="${list.c_condition == 'Y' }">
												<a href="ChallengeDetail.bo?c_num=${list.c_num}&page=${nowPage}&id=${sessionScope.id}" class="attend">
									
												
											</c:when>
											<c:when test="${list.c_condition == 'ing' }">
												<a href="ChallengeDetail.bo?c_num=${list.c_num}&page=${nowPage}&id=${sessionScope.id}" class="ing">
													
											</c:when>
											<c:otherwise>
												<a href="ChallengeDetail.bo?c_num=${list.c_num}&page=${nowPage}&id=${sessionScope.id}" class="end">
												
											</c:otherwise>
										</c:choose>
										
											<div>
												<ul>
												<c:choose>
													<c:when test="${list.c_condition == 'Y' }">
														<li><img src="img/ican.jpg"></li>		
													</c:when>
													<c:when test="${list.c_condition == 'ing' }">
														<li><img src="img/run.png"></li>	
													</c:when>
													<c:otherwise>
														<li><img src="img/sleep.jpg"></li>
													</c:otherwise>
												</c:choose>
													
													<li>${list.c_subject }</li>
													<li class="list_icon">
														<span><b>
															<c:choose>
																<c:when test="${list.c_condition == 'Y' }">
																	<i class="fa-sharp fa-solid fa-play"></i>
																</c:when>
																<c:when test="${list.c_condition == 'ing' }">
																	<i class="fa-solid fa-pause"></i>	
																</c:when>
																<c:otherwise>
																	<i class="fa-sharp fa-solid fa-lock"></i>
																</c:otherwise>
															</c:choose>
														</b></span>
														<span><i class="fa-solid fa-user"></i>&nbsp;&nbsp;${list.particip}</span>
													</li>
												</ul>
											</div>
											</a>
										</li>
								</c:forEach>
							</c:when>
						</c:choose>
						
						<li>
							
						</li>
					</ul>
				</div>
			
				<div class ="pageList">
					<c:choose>
						<c:when test="${nowPage <= 1 }">
							<i class="fa-sharp fa-solid fa-caret-left"></i>&nbsp;
						</c:when>
						<c:otherwise>
							<a href = "ChallengeList.bo?page=${nowPage-1 }" class="button_on"><i class="fa-sharp fa-solid fa-caret-left"></i></a>
						</c:otherwise>
					</c:choose>
					<c:forEach var="a" begin="${startPage }" end="${endPage }">
							<c:choose>
								<c:when test="${a == nowPage }">
									<span class="now_page">${a }</span>
								</c:when>
								<c:otherwise>
									<a href = "ChallengeList.bo?page=${a }" class="page_num">&nbsp;${a }</a>
								</c:otherwise>
							</c:choose>
					</c:forEach>
					<c:choose>
							<c:when test="${nowPage >= maxPage }">
								<i class="fa-sharp fa-solid fa-caret-right"></i>
							</c:when>
							<c:otherwise>
								<a href = "ChallengeList.bo?page=${nowPage+1 }" class="button_on"><i class="fa-sharp fa-solid fa-caret-right"></i></a>
							</c:otherwise>
					</c:choose>
				</div>
			</section>
		</div>
		<jsp:include page="/footer.jsp"/>
</body>
</html>