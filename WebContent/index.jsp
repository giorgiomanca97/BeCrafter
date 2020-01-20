<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		int a=1;
		int b=2;
		if(a<b){
			%><h1>Hello World !</h1><%
		}
		else{
			%><h1>I'm a jsp page !</h1><%
		}
	%>
</body>
</html>