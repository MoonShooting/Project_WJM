<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>
    <%@page import="javax.naming.*" %>
    <%@page import="javax.sql.*" %>
    <%
    	Connection con = null;
    	try{
    		Context init = new InitialContext();
    		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/wjm");
    		con = ds.getConnection();
    		out.println("연결성공");
    	}catch(Exception e){
    		out.println("연결실패");
    		e.printStackTrace();
    	}    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>	