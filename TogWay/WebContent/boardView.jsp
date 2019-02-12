<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세보기</title>
<script type="text/javascript" src="board.js"></script>
<script type="text/javascript" src="board.js?ver=1"></script>
<link rel="stylesheet" href="css/bootstrap.css">
   <link rel="stylesheet" href="css/codingBootstrap.css">
   <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah|Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Nanum+Pen+Script|Open+Sans|Sunflower:300|Ubuntu" rel="stylesheet">
   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
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
	
	
	
	
	
	
	
		<div class="content">
		<div class="container">
		<div class="board-title" style="text-align:center; height: 50;"><h2>게시글 상세보기</h2></div>
		<table class="table table-border" style="width: 60%;">
	
			<thead>
			<tr>
				<th>작성자</th>
				<th>${togbean.writer}</th>
				<th>작성일</th>
				<th>
				<fmt:formatDate value="${togbean.reg_date}" pattern="yyyy-MM-dd H:mm:ss"/>
				</th>
				<th>조회수</th>
				<th>${togbean.readcount}</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>제목</th>
			</tr>
			<tr>
				<td colspan="6">${togbean.subject}</td>
			</tr>
			<tr>
				<th>내용</th>
			</tr>
			<tr>
				<td colspan="6"><pre style="border:none; background-color: #ffffff;">${togbean.content}</pre></td>
			</tbody>	
		</table>
		<div class="btn boardView-container" style="	position: relative; bottom: 66px; left: 310px; ">
		<br> <br>
		<input type="button" value="등록" class="content-btn"
		onclick="location.href='Board_Controller?command=writeform&div_num=${togbean.div_num}'">
		<input type="button" value="수정" class="content-btn"
		onclick=
		"open_win('Board_Controller?command=board_check_pass_form&num=${togbean.num}&div_num=${togbean.div_num}',
		'update')">
		<input type="button" value="삭제" class="content-btn"
		onclick=
		"open_win('Board_Controller?command=board_check_pass_form&num=${togbean.num}&div_num=${togbean.div_num}','delete')">
		
		<input type="button" value="목록" class="content-btn"
		onclick="location.href='Board_Controller?command=mypet_list&div_num=${togbean.div_num}'">
	</div>
	
<script type = "text/javascript">
	function displaySet(exe, num, writer, content, cmd) {
		obj = document.fm;
		obj.num.value=num;
		obj.exe.value=exe;
		obj.writer.value=writer;
		while(content.indexOf("<br>") != -1){
			content = content.replace("<br>","\n");
		}
		obj.content.value=content;
		obj.cmd.value=cmd;
	}
</script>
<form action="Comment_Controller" method="post" name="fm">
<input type="hidden" name="num" value="${togbean.num}">
<input type="hidden" name="exe" value="1">




<table class="table table-border" style="width: 60%;">
	<tr><th colspan="4" style="text-align: center">댓글</th><tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer"></td>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>
	<tr>
		<td>내용</td>	
		<td colspan="6"><textarea rows="5" cols="71" name="content"></textarea></td>
	</tr>
	<tr>
	<td colspan="4">
	<input type="submit" value="등록" name="cmd" class="content-btn">
	<input type="reset" value="리셋" class="content-btn">
	</td>
	</tr>	
<c:forEach var="co" items="${clist }">
<div class="commentlist">
<div class="border border-light" style="width: 60%; text-align: left; border-bottom: 1px solid #bbbbbb;">
	<div>
		<ul style="list-style: none;">
			<li style="font-weight: bolder;">${co.writer }</li>	<!-- 이름 -->
			<li><c:set var="content" value="${fn:replace(content,rn,'<br>') }"></c:set></li>
		</ul>
	</div>
	<div>
		<ul style="list-style: none; color: #bbbbbb">
			<li class="reg_date" style="font-size: 11;">${co.reg_date }</li>	<!-- 날짜, 시간 -->
			<li><c:set var="content" value="${co.content }"></c:set></li>
		</ul>
		<ul style="list-style: none;">
			<li colspan="6">${content }	</li>	<!-- 내용 -->
		</ul>
	</div>
</div>

		<div class="btn content-btn" style="margin: 8px 1px;">
		<a style="color: #ffffff;"  href="javascript:displaySet(2, '${co.ref }','${co.writer }','${content }','수정')">수정</a>&nbsp;
		</div>
		<div class="btn content-btn" style="margin: 8px 1px;">
		<a style="color: #ffffff;" href="javascript:displaySet(3, '${co.ref }','${co.writer }','${content }','삭제')">삭제</a>&nbsp;
		</div>
</c:forEach>	

		
</table>
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
	</div>
</body>
</html>






