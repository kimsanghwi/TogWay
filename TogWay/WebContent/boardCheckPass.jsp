<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<title>Insert title here</title>
<script type="text/javascript" src="board.js"></script>
<script type="text/javascript" src="board.js?ver=1"></script>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<div align="center">
		<h1>비밀번호 확인</h1>
		<form action="Board_Controller" name="frm" method="get">
			<input type="hidden" name="command" value="board_check_pass">
			<input type="hidden" name="num" value="${param.num}">
			<input type="hidden" name="div_num" value="${param.div_num}">
			<table style="width: 80%">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="passwd" size="20"></td>
				</tr>
			</table>
			<br> <input type="submit" value="확인" onclick="return passCheck()" class="content-btn"><br>
				<br>${message }	
		</form>
	</div>
</body>
</html>