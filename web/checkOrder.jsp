<%@page import="logic.entity.Price"%>
<%@page import="logic.designclasses.IdConverter"%>
<%@page import="error.OrderNotFoundException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="checkOrderBean" scope="request" class="logic.bean.CheckOrder_Bean"/>
<jsp:setProperty name="checkOrderBean" property="orderId" />

<%
boolean orderIdNotFound = false;

String orderId = (checkOrderBean.getOrderId() != null) ? checkOrderBean.getOrderId() : "";

boolean search = request.getParameter("search") != null && request.getParameter("search").equals("1");
if(search) {
	try{
		checkOrderBean.searchOrder(orderId);
		orderIdNotFound = false;
	} catch (OrderNotFoundException onfe) {
		orderIdNotFound = true;
	}
}
String purchaseDate = (checkOrderBean.getDate() != null) ? checkOrderBean.getDate() : "";
String overallPrice = (checkOrderBean.getPrice() != 0) ? Price.toText(checkOrderBean.getPrice()) : "";
String email = (checkOrderBean.getEmail() != null) ? checkOrderBean.getEmail() : "";
String shippingCode = (checkOrderBean.getShippingCode() != null) ? checkOrderBean.getShippingCode() : "";
String shippingCompany = (checkOrderBean.getShippingCompany() != null) ? checkOrderBean.getShippingCompany() : "";

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Check Order</title>
	</head>
	<body>
		<h5>Check Order</h5>
		<p>Insert here the order code you are looking for:</p>
		<form action="checkOrder.jsp" method="POST">
			<input type="text" id="orderId" name="orderId">
			<input type="submit" value="search Order">
			<input type="hidden" name="search" value="1">
		</form> 
			<%
				if(orderIdNotFound) {//C'è errore, scrivo la error label
					orderId = "";
					%> <p>no order found with this order code</p> <%
				} else {
					%> <br> <%
				}
			%>
			<table>
				<tr>
					<td>Order Code: </td>
					<td> <%=orderId %> </td>
				</tr>
				<tr>
					<td>Purchase Date: </td>
					<td> <%=purchaseDate %> </td>
				</tr>
				<tr>
					<td>Overall Price: </td>
					<td> <%=overallPrice %> </td>
				</tr>
				<tr>
					<td>Email: </td>
					<td> <%=email %> </td>
				</tr>
				<tr>
					<td>Shipping Code: </td>
					<td> <%=shippingCode %> </td>
				</tr>
				<tr>
					<td>Shipping Company: </td>
					<td> <%=shippingCompany %> </td>
				</tr>
			</table>
	</body>
</html>