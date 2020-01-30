<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
String orderId = request.getParameter("orderId") != null ? request.getParameter("orderId") : "";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Checkout Confirmation</title>
	</head>
	<body>
		<h2>Checkout Confirmation</h2>
		<p>orderId</p>
		<form action="home.jsp">
			<input type="submit" value="Ok">
		</form>
	</body>
</html>