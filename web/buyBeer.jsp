<%@page import="error.ProductNotFoundException"%>
<%@page import="logic.entity.BeerFiltering"%>
<%@page import="logic.entity.ContainerType"%>
<%@page import="logic.entity.BeerColor"%>
<%@page import="logic.entity.BeerType"%>
<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>
<%@page import="error.ProductNotFoundException"%>
<%@page import="error.StorableIllegalQuantityException"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="buyBeerBean" scope="request" class="logic.bean.BuyBeer_Bean"/>

<%
int quantity = 1;
boolean fault = false;
boolean buy = (request.getParameter("buyAction") != null && request.getParameter("buyAction").equals("1"));
if(buy){
	String q = request.getParameter("quantity");
	if(q != null) {
		try{
			quantity = Integer.parseUnsignedInt(q);
			buyBeerBean.setQuantity(quantity);
		} catch (NumberFormatException nfe){
			quantity = 1;
			fault = true;
		}
	}
}

buyBeerBean.setBeerId(request.getParameter("beerId"));
buyBeerBean.setContainerType(ContainerType.valueOf(request.getParameter("containerType")));
buyBeerBean.setContainerVolume(Integer.parseInt(request.getParameter("volume")));

if(buy && !fault){
	buyBeerBean.addProductToCart();
	%> <jsp:forward page="home.jsp"/> <%
} else {
	try {
		buyBeerBean.selectForSaleProduct();
	} catch (ProductNotFoundException pnfe) {
		%><jsp:forward page="home.jsp"/><%
	}
	buyBeerBean.loadSelectedProduct();
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/buyBeer.css">
		<title>Buy Beer</title>
	</head>
	<body>
	<div align="center">
		<br><br><br><br><br>
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
		</table>
		<br>
		<p><%=buyBeerBean.getBeerDescription()%></p>
		<br>
			<form action="buyBeer.jsp">
				<input class="text S" type="number" name="quantity" value=<%=quantity %>>
				<input class="Button M" type="submit" value="Buy Product">
				<input type="hidden" name="buyAction" value="1">
				<input type="hidden" name="beerId" value=<%=buyBeerBean.getBeerId() %>>
				<input type="hidden" name="containerType" value=<%=buyBeerBean.getContainerType().name() %>>
				<input type="hidden" name="volume" value=<%=buyBeerBean.getContainerVolume() %>>
			</form>
		<br>
		<%
		if(fault) { 
			%><p class="error"><b>Please insert a valid quantity</b></p> <% 
		}
		%>
	</div>
	</body>
</html>