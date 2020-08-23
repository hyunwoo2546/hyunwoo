<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import ="com.hyunwoo.examlib.entity.Exam" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	JSP Index Page ${3+4}
	
	<%
		Exam exam = new Exam(10,20,30);
		out.print("<br>");
		out.print(exam.total());
		out.print("<br>");
		out.print(exam.avg());
	%>
</body>
</html>