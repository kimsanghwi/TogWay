<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<script src = "http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"> 

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!-- 스마트 에디터 부분 -->
<script type="text/javascript" src="./smarteditor2-2.8.2.3/js/HuskyEZCreator.js" charset="utf-8"></script>
 
<script>

$(function(){
    //전역변수선언
    var editor_object = [];
     
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "content",
        sSkinURI: "./smarteditor2-2.8.2.3/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부
            bUseModeChanger : true,
        }
    });
    $("#save").click(function() {
    	editor_object.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    	$("#frm").submit()
    });
});











</script>
<script type="text/javascript" src="board.js"></script>
<script type="text/javascript" src="board.js?ver=1"></script>



<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<title>게시글 작성</title>
<link rel="stylesheet" href="css/bootstrap.css">
   <link rel="stylesheet" href="css/codingBootstrap.css">
   <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Nanum+Pen+Script|Open+Sans|Sunflower:300|Ubuntu" rel="stylesheet">
   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>


</head>
<body>
	<div id="wrap" align="center">
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
               <li><a href="Board_Controller?command=fleamarket_list&div_num=3">플리마켓</a></li>
               <li><a href="Board_Controller?command=hospital_list&div_num=4">동물병원</a></li>
               <li><a href="Board_Controller?command=review_list&div_num=2">애완용품 후기</a></li>
            </ul>
            <form class="navbar-form navbar-left" style="margin-left: 420px";>   <!-- 검색 -->
               <div class="form-group">
                  <input type="text" class="form-control" placeholder="내용을 입력하세요." size="30">
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	<div class="contents">
		<div class="container">
			<thead>
				<div class="board-title" style="text-align:center; height: 50;"><h2>게시글 작성</h2></div>
				<form name="frm" method="get" action="Board_Controller?">
				<input type="hidden" name="command" value="write">
				<input type="hidden" name="div_num" value="${div_num}">
			</thead>

	
	<table class="table table-border" style="width: 60%">
	<tr>
		<th class="test">작성자</th>
	</tr>
	<tr>
		<td><input type="text" name="writer" size="20" style="border:none;" placeholder="작성자 입력" value="${userid}" disabled></td>
	</tr>
	<tr>
		<th>비밀번호</th>
	</tr>
	<tr>
		<td><input type="password" name="passwd" size="20" style="border:none" placeholder="비밀번호 입력"></td>
	</tr>
	<tr>
		<th>제목</th>
	</tr>
	<tr>
		<td><input type="text" name="subject" size="118" style="border:none" placeholder="제목 입력"></td>
	</tr>
	<tr>
			<th>내용</th>
	</tr>
	<tr>
			<td><textarea cols="118" rows="15" name="content" id="content" placeholder="내용 입력"></textarea></td>
	</tr>		
	</table>
	<br>
	<br>
	<div style="position: relative; bottom: 66px; left: 345px;">
	<input type="submit" id="save" value="등록" class="content-btn">
	<input type="reset" value="다시 작성" class="content-btn">
	<input type="button" value="목록" class="content-btn"
		onclick="location.href='Board_Controller?command=mypet_list&div_num=${div_num}'">
	</div>
	</form>
	</div>
	</div>
	
	
		<footer> 
         <div class="container">
            <br>
            <div class="row" style="background-color: #1e90ff; color: #000000">
            <!-- col-sm- : 총 12칸 을 만들수 있다 -->
               <div class="col-sm-3" style="text-align: center"><h4><b>TogWayTeam member &copy; 2018</b></h4>
               <h5>소준혁(SoJunHyuk)</h5><h5>김상휘(KimSangHwi)</h5><h5>박아영(ParkAYoung)</h5><h5>박현옥(ParkHyunOk)</h5></div>
               <div class="col-sm-5"><h4><b>TogWay 소개</b></h4><p>TogWay의 의미는 Together(함께)Way(길)이라는 의미로 만들었습니다. </div>
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