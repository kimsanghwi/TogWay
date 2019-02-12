%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<body>
	<div id="weap">
		<div id="header">
			<div class="container">
				<div class="headertext">1</div>
			</div>
		</div>
		
		
		
		
		
		
		
		
		<div id="contents">
			<div class="container">
				<div class="contents-area">
				 	<div class="contents-title">
						<h3 class="title-text">${togbean.subject}</h3>
						<div class="writer-name"> </div>
							<div class="writer-time">${togbean.reg_date}</div>
					</div>
							<hr>
								<div class="content-text">
								<pre>${togbean.context}</pre>

								</div>		
									<div class="button">글 수정</div>
										<div class="buttom1">글 삭제</div>
				</div>
			</div>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		<div id="footer">
			<div class="container">
				<div class="foot-area">footer</div>
				
			</div>
		</div>
	</div>