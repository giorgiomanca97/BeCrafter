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

<jsp:useBean id="buyBeerBean" scope="request" class="logic.bean.BuyBeerBean"/>

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
			<table class="field">
				<tr class="field">
					<td class="field"><p>Name</p></td>
					<td class="field"><p><%=buyBeerBean.getBeerName() %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Type</p></td>
					<td class="field"><p><%=buyBeerBean.getBeerType().toString() %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Color</p></td>
					<td class="field"><p><%=buyBeerBean.getBeerColor().toString() %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Alcohol content</p></td>
					<td class="field"><p><%=String.valueOf(buyBeerBean.getBeerAlcohol()) + "%" %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Filtering</p></td>
					<td class="field"><p><%=buyBeerBean.getBeerFiltering().toString() %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Container</p></td>
					<td class="field"><p><%=buyBeerBean.getContainerType().toString() %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Size</p></td>
					<td class="field"><p><%=Volume.toText(buyBeerBean.getContainerVolume()) %></p></td>
				</tr>
				<tr class="field">
					<td class="field"><p>Price</p></td>
					<td class="field"><p><%=Price.toText(buyBeerBean.getPrice()) %> &euro;</p></td>
			</table>
			<br>
			<p class="description"><%=buyBeerBean.getBeerDescription()%></p>
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