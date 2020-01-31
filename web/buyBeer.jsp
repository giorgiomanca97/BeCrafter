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
boolean wrongQuantity = false;
buyBeerBean.setQuantity(1);

try {
	buyBeerBean.setBeerId(request.getParameter("beerId"));
	buyBeerBean.setContainerType(ContainerType.valueOf(request.getParameter("containerType")));
	buyBeerBean.setContainerVolume(Integer.parseInt(request.getParameter("volume")));
} catch (Exception e) {
	%><jsp:forward page="home.jsp"/><%
	}

try{
	buyBeerBean.selectProductForSale();
	buyBeerBean.loadSelectedProduct();
	buyBeerBean.setQuantity(1);
	
	boolean buy = (request.getParameter("buyAction") != null && request.getParameter("buyAction").equals("1"));
	if(buy) {
		int quantity = Integer.parseUnsignedInt(request.getParameter("quantity"));
		buyBeerBean.setQuantity(quantity);
		buyBeerBean.addProductToCart();
%> <jsp:forward page="home.jsp"/> <%
	}
} catch (ProductNotFoundException | NullPointerException e){
	%><jsp:forward page="home.jsp"/><%
} catch (NumberFormatException | StorableIllegalQuantityException e){
	wrongQuantity = true;
	buyBeerBean.setQuantity(1);
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
			<h3>Buy Beer</h3>
			<br>
			<table>
				<tr>
					<td><p>Name</p></td>
					<td><%=buyBeerBean.getBeerName() %></td>
				</tr>
				<tr>
					<td><p>Type</p></td>
					<td><%=buyBeerBean.getBeerType().toString() %></td>
				</tr>
				<tr>
					<td><p>Color</p></td>
					<td><%=buyBeerBean.getBeerColor().toString() %></td>
				</tr>
				<tr>
					<td><p>Alcohol content</p></td>
					<td><%=String.valueOf(buyBeerBean.getBeerAlcohol()) + "%" %></td>
				</tr>
				<tr>
					<td><p>Filtering</p></td>
					<td><%=buyBeerBean.getBeerFiltering().toString() %></td>
				</tr>
				<tr>
					<td><p>Container</p></td>
					<td><%=buyBeerBean.getContainerType().toString() %></td>
				</tr>
				<tr>
					<td><p>Size</p></td>
					<td><%=Volume.toText(buyBeerBean.getContainerVolume()) %></td>
				</tr>
				<tr>
					<td><p>Price</p></td>
					<td><%=Price.toText(buyBeerBean.getPrice()) %> &euro;</td>
			</table>
			<br>
			<p><%=buyBeerBean.getBeerDescription()%></p>
			<br>
				<form action="buyBeer.jsp">
					<input class="text S" type="number" name="quantity" value=<%=buyBeerBean.getQuantity() %>>
					<input class="button S" type="submit" value="Buy Product">
					<input type="hidden" name="buyAction" value="1">
					<input type="hidden" name="beerId" value=<%=buyBeerBean.getBeerId() %>>
					<input type="hidden" name="containerType" value=<%=buyBeerBean.getContainerType().name() %>>
					<input type="hidden" name="volume" value=<%=buyBeerBean.getContainerVolume() %>>
				</form>
			<br>
			<% if(wrongQuantity) { %>
			<p class="error"><b>Please insert a valid quantity</b></p> 
			<% } %>
		</div>
		<div align="center">
			<form action="home.jsp">
				<input class="button M" type="submit" value="Go Back">
			</form>
		</div>
	</body>
</html>