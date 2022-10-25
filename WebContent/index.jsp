<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"/>
    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<!-- Swiper -->
    <div class="swiper mySwiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
        	<div class="main_first">
        		<div class="header">
        			<p>내가 만드는 나의 목표</p>	
        			<h2>우주문 프로젝트</h2>
        			<img src="img/right.jpg">
        			
        		</div>
        		<span class="skip">
        			<a href="${pageContext.request.contextPath }/MyPageLoginForm.me">skip</a>
        		</span>
        	</div>
        </div>
        <div class="swiper-slide">
        	<div class="main_second">
        		<div class="header">
        			<h3>다른사람들과 함께 하는 챌린지 !</h3>
        			<div class="phone_box">
        				<span class="camera"></span>
        				<span class="call"></span>
        				<div class="img_box">
        					<img src="img/list.png">
        				</div>
        				<span class="home"></span>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="swiper-slide">
        	<div class="main_third">
        		<div class="header">
        			<h3>나만의 챌린지를 만들고 !</h3>
        			<div class="phone_box">
        				<span class="camera"></span>
        				<span class="call"></span>
        				<div class="img_box">
        					<img src="img/make_room.png">
        				</div>
        				<span class="home"></span>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="swiper-slide">
        	<div class="main_four">
        		<div class="header">
        			<h3>나의 일정을 관리를 하자 !</h3>
        			<div class="phone_box">
        				<span class="camera"></span>
        				<span class="call"></span>
        				<div class="img_box">
        					<img src="img/mypage.png">
        				</div>
        				<span class="home"></span>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="swiper-slide">
        	<div class="main_five">
        		<div class="header">
        			<h3>오늘의 당신의 목표는 ?</h3>
        			<a href="${pageContext.request.contextPath }/MyPageLoginForm.me"><p>도전하러 가기</p></a>
        		</div>
        	</div>
        </div>
      </div>
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
      <div class="swiper-pagination"></div>
    </div>
</body>
<!-- Swiper JS -->
    <!-- Initialize Swiper -->	
    <script>
      var swiper = new Swiper(".mySwiper", {
        pagination: {
          el: ".swiper-pagination",
          type: "progressbar",
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      });
    </script>
</html>