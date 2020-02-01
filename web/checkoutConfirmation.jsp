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
		<h3 align="center">Checkout Confirmation</h3>
		<br><br><br>
		<p class="confirmation" align="center">Thank you for your purchase! The order code is: <%=orderId %></p>
		<p class="confirmation" align="center">We are sending to you an email with a summary of your purchase and order information</p>
		<p class="confirmation" align="center">Remember to periodically check your inbox for future updates relative to your order</p>
		<div align="center">
			<br><br>
			<form action="home.jsp">
				<input class="button S" type="submit" value="Ok">
			</form>
		</div>
	</body>
</html>