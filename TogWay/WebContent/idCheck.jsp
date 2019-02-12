<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/idCheck.css"/>
<script type="text/javascript" src="member.js"></script>
<title>중복 체크</title>
</head>

<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Nanum+Gothic+Coding|Open+Sans" rel="stylesheet"> 

<body>
	<!-- idCheckServlet.java의 doGet으로 다시 전송하여 반복적으로 중복확인을 할 수 있게 설계 했다 -->
	<!-- 여기서 만드는 페이지
	자바스크립트가 만들어놓은 프레임에 들어갈 내용이다. 이 페이지에서는 "중복체크"버튼의 용도가 아이디를 계속
	검사할 수 있어야한다 "사용"버튼을 누르면 -> 현재 input창에 있는 내용을 다시 윗글에 넣어야 한다 -->
	<div class="main" align="center">
	<form action="idCheck.do" method="get" name="frm">
	아이디 <input type="text" name="userid" value="${userid }"/>	
	
	<!-- 중복체크 버튼을 누를 때마다 체크하러 간다 -->
	<input type="submit" value="중복체크" class="btn"><br>
	<!-- 사용중인 아이디 상황 -->
	<c:if test="${result == 1}">	<!-- 아이디 존재 -->
		<script type="text/javascript" src="member.js">
		opener.document.frm.userid.value="";
		</script>
		${userid }는 이미 사용중인 아이디 입니다.
	</c:if>
	
	<!-- 사용 가능한 아이디 -->
	<c:if test="${result == -1 }">
		${userid } 사용 가능한 아이디 입니다.
		<input type="button" class="btn" value="사용" onclick="idok('${userid }')"/>
		<!-- 아이디를 사용하려고 '사용'버튼을 클릭하면 member.js의 idok 메서드가 실행된다. -->
	</c:if>
	</form>
	</div>
</body>
</html>