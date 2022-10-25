<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 관리자모드(회원 정보 보기)</title>
<style>
	
		
	
</style>
<<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<jsp:include page="../header.jsp"/>
<div id="admin_inex">
<section id = "memberInfoArea">	
	<div class="memberInfoArea">
		<h1>INFOMATION</h1>
		<table>
			<tr>
				<td class="td_le">ID  </td>
				<td class="td_ri">${member.u_id }</td>
			</tr>
					<tr>
				<td class="td_le">PW  </td>
				<td class="td_ri">${member.u_pw }</td>
			</tr>
					<tr>
				<td class="td_le">NAME  </td>
				<td class="td_ri">${member.name }</td>
			</tr>
					<tr>
				<td class="td_le">NICK  </td>
				<td class="td_ri">${member.nickname }</td>
			</tr>
					<tr>
				<td class="td_le">TEL  </td>
				<td class="td_ri">${member.tel }</td>
			</tr>
		</table>
		<ul>
			<li class="ListBack_li">
				<a class="ListBack" href="index.admin">리스트로 돌아가기</a>
			</li>
		</ul>
	</div>
</section>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>