<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
<script type="text/javascript" src="Memberr.js"></script>
</head>
<body>
<c:if test="${param.x == 1}">
<script>
	alert("비밀번호 수정 성공 했습니다.");
</script>
</c:if>
<c:if test="${param.x == 0}">
<script>
	alert("비밀번호 수정 실패 했습니다.");
</script>
</c:if>
	<div id="weup">
		<!-- 전체틀 -->
		<div id="header">
			<div class="container">header</div>
		</div>
		
		
			<div id="content">
				<div class="container" align="center">
				<form action="newpasswd.do" method="post" name="frm">
					<table>
						<tr class="password">
							<td>현재 비밀번호</td>
							<td><input type="password" name="passwd" size="20" required></td>
						</tr>
						<tr class="password">
							<td>새로운 비밀번호</td>
							<td><input type="password" name="newpasswd" size="20" required></td>
						</tr>
						<tr class="password">
							<td>새로운 비밀번호 확인</td>
							<td><input type="password" name="newrepasswd" size="20" required></td>
						</tr>
						<tr class="btn">
							<!-- #31] 수정된 데이터들고 MemberUpdateServlet의 doPost로 가자 -->
							<td><p>
									<input type="submit" value="수정 완료"> 
									<input type="button" value="취소" onclick="location.href='memberdata.do'">
							</p></td>
						</tr>
					</table>
					</form>
				</div>
			</div>
			<!-- 
		</form>
		 -->

		<div id="footer">
			<div class="container">container</div>
		</div>
	</div>
</body>
</html>