<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content ="width.device-width", initial-scale="1">
<link rel ="stylesheet" href ="css/bootstrap.css">					<!-- bootstrap -->
<title>JSP 게시판 웹 사이트</title>
<style type="text/css">
	a, a :hover {
		color : #000000;
		text-decoration:none;
	}
</style>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
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
				<li><a href="main.jsp">메인</a></li>
				<li class = "active"><a href="bbs.jsp">게시판</a></li>
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
	<!-- *** 게시판 양식 *** -->
	<div class ="container">
		<div class="row">		
			<table class= "table table-striped" style="text-align:center; border:1px solid #dddddd">
				<thead>
					<tr>	<!-- tr은 하나의 행을 말한다. -->
						<th style="background-color : #eeeeee; text-align:center;">번호</th>		<!-- th는 하나의 열을 말한다. -->
						<th style="background-color : #eeeeee; text-align:center;">제목</th>
						<th style="background-color : #eeeeee; text-align:center;">작성자</th>
						<th style="background-color : #eeeeee; text-align:center;">작성일</th>
					</tr>
				</thead>
				<tbody>
				<%
					BbsDAO bbsDAO = new BbsDAO();
					ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
					for(int i =0; i < list.size(); i++) {
				%>
					<tr>
						<td><%=list.get(i).getBbsID() %></td>
						<td><a href = "view.jsp?bbsID=<%= list.get(i).getBbsID()%>"><%=list.get(i).getBbsTitle() %></a></td>
						<td><%=list.get(i).getUserID() %></td>
						<td><%=list.get(i).getBbsDate().substring(0,11) + list.get(i).getBbsDate().substring(11,13) + "시" + list.get(i).getBbsDate().substring(14,16) + "분" %></td>
					</tr>
				<%		
					}
				%>
					
				</tbody>
			</table>
			<%
				if(pageNumber != 1) {
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn -arraw-left">이전</a>
			<%
				} if(bbsDAO.nextPage(pageNumber + 1)) {
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn -arraw-left">다음</a>
			<%
				}
			%>
			<a href="write.jsp" class = "btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>	<!-- jquery -->
	<script src = "js/bootstrap.js"></script>								<!-- bootstrap -->
</body>
</html>