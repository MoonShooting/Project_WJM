<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rankView</title>
<style>
   /*공통 css*/
   *{margin:0px; padding:0px;}
   ul,ol{list-style:none;}
   a{text-decoration: none; color: #000;}
   
   /*button css*/
   #btn{border: 2px outset #4996fe; background-color: rgba(0,0,0,0); color: #4996fe; padding: 10px; border-radius:10px; margin-right: 4px;}
   
</style>
<script>
   function Popup() {
      document.getElementById("rb").onclick = function() {
         var u_id = document.getElementById("u_id").value;
         window.opener.document.getElementById("u_id").value;
      }
   }
</script>
</head>
<body>
   <div>
   <section id="RankInfoArea">
      <div class="wrap">
         <div class="rankview">
         <form name="popup" action="#">
            <p id="u_id">아이디 :  ${param.u_id }</p>
          </form>
           <input id="btn" type="button" value="창닫기" onclick="window.close();">
         </div>
      </div>
   </section>
   </div>
</body>
</html>