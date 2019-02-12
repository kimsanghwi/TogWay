// #] 회원가입 체크
// 아이디 중복 체크 - IDCheck서블릿을 불러서 db와 id가 동일한지 여부 확인
function idCheck() {
	if(document.frm.userid.vlaue=="") {
		alert('아이디를 입력하세요.');
		document.frm.userid.focus();
		
		return;
	}
	
	// 서블릿을 부르자 이때 쿼리스트링을 만들어서 값도 같이 가져가자
	var url = "idCheck.do?userid=" + document.frm.userid.value;
	
	var title="_blank_1";
	
	var frame = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200";
	
	// 서블릿에 가서 결과를 가져오자 --> idcheck.jsp에 던져주자
	// 여부 결과 페이지를 만들어서 창에 끼워 넣으면 된다
	// 스크립트에서 -> idcheck 서블릿으로 가자(id를 들고 가자)
	window.open(url, title, frame);
}
//이 함수를 부른 모글(document)에서 값을 읽어온 다음
//join.jsp(=opener)의 userid에 그 값을 넣어 줘야 한다.
//아이디 중복 확인을 위해서 숨겨져 있는 reid한테도 값을 넣어주자
//그리고 현재 열려있는 창을 닫자
// opener : 자바스크립트를 실행한 페이지를 실행한 페이지(join.jsp)
// document : 자바스크립트를 실행한 페이지(joinCheck.jsp)
// opener.frm.reid.value에 값을 넣어 줌으로써 아이디 중복체크 실행 여부를 확인할 수 있다.
function idok(userid) {
	opener.frm.userid.value = userid
	opener.frm.reid.value = document.frm.userid.value; // 값을 넣어줌으로써 아이디중복체크 실행여부를 확인 할 수 있다.
	self.close();	// window창이 종료되고 회원가입창만 남게된다
}

function newCheck() {
	if(document.frm.newpasswd.value != document.frm.newrepasswd.value) {
		alert("새로운 비밀번호가 일치하지 않습니다.");
		document.frm.newpasswd.focus();
		return false;
	}
	return true;
}

function joinCheck() {
	if(document.frm.userid.value.length<4) {
		alert("아이디는 4글자 이상 입력하세요.");
		document.frm.userid.focus();
		return false;
	}
	if(document.frm.passwd.value != document.frm.repasswd.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.frm.passwd.focus();
		return false;
	}
	if(document.frm.reid.value.length==0) {
		alert("중복 체크를 하지 않았습니다.");
		document.frm.userid.focus();
		return false;
	}
	return true;
}



