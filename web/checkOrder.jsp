<%@page import="logic.entity.Price"%>
<%@page import="logic.designclasses.IdConverter"%>
<%@page import="error.OrderNotFoundException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="checkOrderBean" scope="request" class="logic.bean.CheckOrderBean"/>
<jsp:setProperty name="checkOrderBean" property="orderId" />

<%
boolean orderIdNotFound = false;

String orderId = checkOrderBean.getOrderId();

boolean search = request.getParameter("search") != null && request.getParameter("search").equals("1");
if(search && !orderId.equals("")) {
	try{
		checkOrderBean.searchOrder(orderId);
		orderIdNotFound = false;
	} catch (OrderNotFoundException onfe) {
		orderIdNotFound = true;
	}
}
String purchaseDate = checkOrderBean.getDate();
String overallPrice = checkOrderBean.getPrice() == 0f ? "" : Price.toText(checkOrderBean.getPrice());
String email = checkOrderBean.getEmail();
String shippingCode = checkOrderBean.getShippingCode();
String shippingCompany = checkOrderBean.getShippingCompany();

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/checkOrder.css">
		<title>Check Order</title>
	</head>
	<body>
		<h3 align="center">Check Order</h3>
		<br>
		<div align="center">
			<table>
				<tr>
					<td>
						<p>Insert here the order code you are looking for:</p>
					</td>
					<td>
						<form action="checkOrder.jsp" method="POST">
							<input class="text S" type="text" id="orderId" name="orderId">
							<input class="button S" type="submit" value="search order">
							<input type="hidden" name="search" value="1">
						</form> 
					</td>
				</tr>
			</table>		
			<% if(orderIdNotFound) { orderId = ""; %> 
			<p class="error">no order found with this order code</p> 
			<% } else { %> 
			<br> 
			<% } %>
		</div>
		<div class="grid">			
			<div class="left">
				<p class="field">Order Code: </p>
				<p class="field">Purchase Date: </p>
				<p class="field">Overall Price: </p>
				<p class="field">Email: </p>
				<p class="field">Shipping Code: </p>
				<p class="field">Shipping Company: </p>
			</div>
			<div class="right">
				<p class="field"> <%=orderId %> </p>
				<p class="field"> <%=purchaseDate %> </p>
				<p class="field"> <%=overallPrice %> <%if(overallPrice.length() != 0) {%> &euro; <%} %></p>
				<p class="field"> <%=email %> </p>
				<p class="field"> <%=shippingCode %> </p>
				<p class="field"> <%=shippingCompany %> </p>
			</div>
		</div>
		<br>
		<br>
		<div align="center">
			<form action="home.jsp">
				<input class="button M" type="submit" value="Go Back">
			</form>
		</div>
	</body>
</html>