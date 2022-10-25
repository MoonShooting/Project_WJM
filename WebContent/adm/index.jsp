<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/common/checkAdmin.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자모드(회원 목록 보기)</title>
<style>
/* .ttb{height: 1250px;background-color:#d9e1e8;padding-top: 75px;} */
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="ttb">


<c:set var="list" value="${memberList }"/>
<c:set var="pageInfo" value="${pageInfo }"/>
<c:set var="listCount" value="${pageInfo.getListCount() }"/>
<c:set var="nowPage" value="${pageInfo.getPage() }"/> 
<c:set var="maxPage" value="${pageInfo.getMaxPage() }"/>
<c:set var="startPage" value="${pageInfo.getStartPage() }"/>
<c:set var="endPage" value="${pageInfo.getEndPage() }"/>
<div id="admin_index">
<section id = "memberListArea">

	<table>
		<div class="admin_search">
			<form action="index.admin">	
				<select name="admin_menu" id="admin_menu">
					<option value="u_num">회원번호</option>
					<option value="u_id">아이디</option>
					<option value="log_count">접속 횟수</option>
					<option value="log_time">접속 시간</option>
					<option value="grade">등급</option>
				</select>
				<input type="text" name="admin_search"/>
				<input type="submit" value="검색"/>
			</form>
		</div>
		<c:choose> 
			<c:when test="${list != null && listCount > 0 }">
		<tr>
			<td colspan="5"><h1>관리자 페이지</h1></td>
		</tr>
		
		<tr>
			<td>회원번호</td>
			<td>아이디</td>
			<td>접속 횟수</td>
			<td>최근 접속 시간</td>
			<td>등급</td>
		</tr>
		<c:forEach var="member" items="${memberList }">
		<tr>
			<td>
				${member.u_num }
			</td>
			<td>
				<a href="memberViewAction.admin?id=${member.u_id }">
				${member.u_id }
				</a>
			</td>
			<td>
				${member.log_count }
			</td>
			<td>
				${member.log_time}
			</td>
			<td>
				${member.grade}
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class ="pageList">
			<c:choose>
				<c:when test="${nowPage <= 1 }">
					<i class="fa-sharp fa-solid fa-caret-left"></i>&nbsp;
				</c:when>
				<c:otherwise>
					<a href = "index.admin?page=${nowPage-1 }" class="button_on"><i class="fa-sharp fa-solid fa-caret-left"></i></a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="a" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${a == nowPage }">
							<span class="now_page">${a }</span>
						</c:when>
						<c:otherwise>
							<a href = "index.admin?page=${a }" class="page_num">&nbsp;${a }</a>
						</c:otherwise>
					</c:choose>
			</c:forEach>
			<c:choose>
					<c:when test="${nowPage >= maxPage }">
						<i class="fa-sharp fa-solid fa-caret-right"></i>
					</c:when>
					<c:otherwise>
						<a href = "index.admin?page=${nowPage+1 }" class="button_on"><i class="fa-sharp fa-solid fa-caret-right"></i></a>
					</c:otherwise>
				</c:choose>
		</section>
		</div>
</section>
		</c:when>
		<c:otherwise>
			<section id = "emptyArea">등록된 글이 없습니다.</section>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>

</html>