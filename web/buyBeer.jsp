<%@page import="error.ProductNotFoundException"%>
<%@page import="logic.entity.BeerFiltering"%>
<%@page import="logic.entity.ContainerType"%>
<%@page import="logic.entity.BeerColor"%>
<%@page import="logic.entity.BeerType"%>
<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="buyBeerBean" scope="request" class="logic.bean.BuyBeer_Bean"/>

<%
buyBeerBean.setBeerId(request.getParameter("beerId"));
buyBeerBean.setContainerType(ContainerType.valueOf(request.getParameter("containerType")));
buyBeerBean.setContainerVolume(Integer.parseInt(request.getParameter("volume")));
try {
	buyBeerBean.selectForSaleProduct();
} catch (ProductNotFoundException pnfe) {
	%><jsp:forward page="home.jsp"><%
}
buyBeerBean.loadSelectedProduct();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Buy Beer</title>
	</head>
	<body>
		<table>
			<tr>
				<td>Name</td>
				<td><%=buyBeerBean.getBeerName() %></td>
			</tr>
			<tr>
				<td>Type</td>
				<td><%=buyBeerBean.getBeerType().toString() %></td>
			</tr>
			<tr>
				<td>Color</td>
				<td><%=buyBeerBean.getBeerColor().toString() %></td>
			</tr>
			<tr>
				<td>Alcohol content</td>
				<td><%=String.valueOf(buyBeerBean.getBeerAlcohol()) + "%" %></td>
			</tr>
			<tr>
				<td>Filtering</td>
				<td><%=buyBeerBean.getBeerFiltering().toString() %></td>
			</tr>
			<tr>
				<td>Container</td>
				<td><%=buyBeerBean.getContainerType().toString() %></td>
			</tr>
			<tr>
				<td>Size</td>
				<td><%=Volume.toText(buyBeerBean.getContainerVolume()) %></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><%=Price.toText(buyBeerBean.getPrice()) %> &euro;</td>
			</tr>
			<tr>
				<td>Description</td>
				<td><%=buyBeerBean.getBeerDescription()%></td>
			</tr>
		</table>
		<br>
		<form action="home.jsp">
			<input type="number" name="quantity"/>
			<input type="submit" value="Buy Product"/>
		</form>
	</body>
</html>