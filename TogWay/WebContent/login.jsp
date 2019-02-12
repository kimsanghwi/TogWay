<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, intial-scale=1.0" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/codingBootstrap.css">



<title>로그인 페이지</title>
<script type="text/javascript" src="member.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>

</head>

<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Open+Sans" rel="stylesheet"> 

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
            <form action="Search_Servlet" class="navbar-form navbar-left" style="margin-left: 380px";>   <!-- 검색 -->
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
		
		<form action="login.do" method="post" name="frm">
			<div id="login-content">
			
				<!-- 로그인 부
				분 -->
				<div class="login-box">
					<!-- 로그인 박스 안에 id, password 부분 -->
					<div class="box">
						<input type="text" id="userid" name="userid" placeholder="아이디 입력" class="input" required>
						<div class="icon-user icon">
						</div>
					</div>
					<div class="box">
						<input type="password" id="passwd" name="passwd" placeholder="비밀번호 입력" class="input" required>
						<div class="icon-password icon">
						</div>
					</div>	
					<div class="box" style="margin-bottom: 10px;">
						<input type="submit" name="submit" value="로그인" class="btn-box">
					</div>	
					<div class="box">
						<input type="submit" name="submit" value="회원가입" onclick="location.href='join.do'" class="btn-box">
					</div>
					<div>
				<img src="images/pawblue2.svg">
				</div>
				</div>

			</div>

		</form>

		
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