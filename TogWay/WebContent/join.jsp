<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, intial-scale=1.0" />
<link rel="stylesheet" href="css/join.css" />
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/codingBootstrap.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>회원가입</title>
<script type="text/javascript" src="member.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
</head>

<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Open+Sans" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Nanum+Pen+Script|Open+Sans|Sunflower:300|Ubuntu" rel="stylesheet"> 

<link href="https://fonts.googleapis.com/css?family=Allerta|Cabin+Sketch|Chela+One|Concert+One|Days+One|Fredoka+One|Fugaz+One|Kaushan+Script|Neucha|Open+Sans|Permanent+Marker|Prosto+One|Viga" rel="stylesheet">  

<body>
	<div id="weup">
	<nav class="navbar navbar-default">   <!-- 기본적인 부트스트랩에서 메뉴바 제공 -->
      <div class="container-fluid">
         <div class="navbar-header"><!-- 헤더부분을 만들때 사용 -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only"></span>
            <span class="icon-bar"></span> <!-- 기본적인 형태 -->
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="TogWay_Servlet?copmmand=main_list"><b>TogWay</b></a>
         </div>
         <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
               <!-- active : 현재 선택 -->
               <li><a href="Board_Controller?command=mypet_list&div_num=1">나의 반려동물<span class="sr-only"></span></a></li>
               <li><a href="Board_Controller?command=fleamarket_list&div_num=3">벼룩시장</a></li>
               <li><a href="Board_Controller?command=hospital_list&div_num=4">동물병원</a></li>
               <li><a href="Board_Controller?command=review_list&div_num=2">애완용품 후기</a></li>
            </ul>
            <form action="Search_Servlet" class="navbar-form navbar-left" style="margin-left: 420px";>   <!-- 검색 -->
               <div class="form-group">
                  <input type="text" class="form-control" placeholder="내용을 입력하세요." size="30" name="search">
               </div>
               <button type="submit" class="btn btn-default">검색</button>
               <c:if test="${!empty userid}">
               <span> ${userid}님이 로그인</span>
               </c:if>
               <c:if test="${empty userid}">
               <span>로그인 해주세요!</span>
               </c:if>
            </form>
            <ul class="nav navbar-nav navbar-right">
               <li class="dropdown">
  
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                     aria-haspopup="true" aria-expanded="false"><c:if test="${empty userid}">접속하기 </c:if><c:if test="${!empty userid}">정보보기</c:if><span class="caret"></span></a>
                      
        
                
                
                
                  <ul class="dropdown-menu">
                  <c:if test="${empty userid}">
                     <li><a href="login.do">로그인</a></li>
                     <li><a href="join.do">회원가입</a></li>
                     </c:if>
                      <c:if test="${!empty userid}">
                      <li><a href="logout.do">로그아웃</a></li>
                     <li><a href="memberdata.do">정보보기</a></li>
                      </c:if>
                  </ul>               
               </li>
            </ul>
         </div>
      </div>
      </nav>
		
		<div id="content">
			<div class="main">
				<header>
					<div class="image"><img src="images/pawblue2.svg"></div>
					<div class="company">Tog-way</div>
					<div class="heading">우리가 함께하는 길</div>
				</header>
				
				<form action="join.do" id="form-box" method="post" name="frm">
					<div class="icon">
						<span class="fa fa-user"></span>
					</div>
					<div class="input">
						<input type="text" name="name" placeholder="사용자 이름 입력" class="inp" autofocus required>
						<!-- autofocus : 자동으로 커서 , required : 입력안하고 로그인 버튼 누를시에 경고창-->
					</div>
					
					<div class="icon">
						<span class="fa fa-user"></span>
					</div>
					<div class="input">
						<input type="text" name="userid" placeholder="아이디 입력" class="checkid" required>
						<input type="hidden" name="reid"><input type="button" class="checkbtn" value="중복체크" onclick="idCheck()">		
					</div>
					
					<div class="icon">
						<span class="fa fa-lock"></span>
					</div>
					<div class="input">
						<input type="password" name="passwd" placeholder="비밀번호 입력" class="inp" required>
					</div>
			
					<div class="icon">
						<span class="fa fa-lock"></span>
					</div>
					<div class="input">
						<input type="password" name="repasswd" placeholder="비밀번호 확인" class="inp" required>
					</div>		
							
					<div class="icon">
						<span class="fa fa-envelope"></span>
					</div>
					<div class="input">
						<input type="email" name="email" placeholder="이메일 주소" class="inp" required>
					</div>		
							
					<div class=icon>
						<span class="fa fa-phone"></span>
					</div>
					<div class="input">	
						<input type="text" name="tel" placeholder="핸드폰 번호" class="inp" required>
					</div>
							
					<div class="joinbtn">
						<input type="submit" name="submit" value="회원가입" onclick="return joinCheck()" class="sub-btn">
					</div>
					<div class="resetbtn">
						<input type="reset" value="취소" class="sub-btn">
					</div>	
					</form>
				</div>
			</div>
			
			 <footer> 
         <div class="container">
            <br>
            <div class="row" style="background-color: #0063c6; color: #000000">
            <!-- col-sm- : 총 12칸 을 만들수 있다 -->
               <div class="col-sm-3" style="text-align: center"><h4><b>TogWayTeam member &copy; 2018</b></h4>
               <h5>소준혁(SoJunHyuk)</h5><h5>김상휘(KimSangHwi)</h5><h5>박아영(ParkAYoung)</h5><h5>박현옥(ParkHyunOk)</h5></div>
               <div class="col-sm-4""><h4><b>TogWay 소개</b></h4><p>TogWay의 의미는 Together(함께)Way(길)이라는 의미로 만들었습니다. </div>
               <div class="col-sm-2"><h4 style="text-align: center;"><b>SNS</b></h4>
                  <div style="text-align: center;">
                     <a href="https://ko-kr.facebook.com/" class="list-group-item" >페이스북</a>
                     <a href="https://www.instagram.com/?hl=ko" class="list-group-item">인스타그램</a>
                     <a href="https://twitter.com/?lang=ko" class="list-group-item" style="margin-bottom: 2px;">트위터</a>
                  </div>
               </div>
               <div  class="col-sm-2"><h4 style="text-align: center"><b>인터넷</b></h4>
                  <a href="http://www.naver.com" class="list-group-item" style="text-align: center;">네이버</a>
                  <a href="https://www.daum.net" class="list-group-item" style="text-align: center;">다음</a>
                  <a href="https://www.google.co.kr" class="list-group-item" style="text-align: center;">구글</a>
               </div>
            </div>
         </div>
      </footer>
	</div>
</body>
</html>