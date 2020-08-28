<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content ="width.device-width", initial-scale="1">
<link rel ="stylesheet" href ="css/bootstrap.css">					<!-- bootstrap -->
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
	%>
	<nav class="navbar navbar-default">	<!-- 네비 -->	
		<div class ="navbar-header">	<!-- 네비에 헤더부분 -->
		<!-- 우측 상단에 줄 세개 있는 버튼 -->   <!-- ++아직 bootstrap이나 페이지 기반 언어 잘 모르겠음 -->
			<button type = "button" class = "navbar-toggle collapsed" 
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>		
		<!-- 우측상단 End  -->
		<a class="navbar-brand" href="main.jsp">Hyunwoo 게시판</a>	<!-- 좌측 메인 로고 -->
		</div>
		<div class = "collapse navbar-collapse" id = "bs-example-navber-collapse-1">
			<ul class="nav navbar-nav">
				<li class = "active"><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			<!-- 접속하기 버튼 -->
			<%
				if(userID == null) {		// 로그인이 되어있지 않다면
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<!-- 접속하기 End -->
			<!-- ------------------ -->
			<!-- 회원관리 버튼 -->
			<%
			} else {			// 로그인이 되어있다면
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="loginAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
			<!-- 회원관리 End -->
		</div>
	</nav>
	<!-- *** 메인 양식 *** -->
	
	<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>	<!-- jquery -->
	<script src = "js/bootstrap.js"></script>								<!-- bootstrap -->
</body>
</html>