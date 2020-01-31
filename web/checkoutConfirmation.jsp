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
		<p>Thank you for your purchase! The order code is: <b><%=orderId %></b></p>
		<p>We are sending to you an email with a summary of your purchase and order information</p>
		<p>Remember to periodically check your inbox for future updates relative to your order</p>
		<form action="home.jsp">
			<input type="submit" value="Ok">
		</form>
	</body>
</html>