<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="checkoutSummaryBean" scope="request" class="logic.bean.CheckoutSummary_Bean"/>

<%
String toRemove = request.getParameter("remove");
if(toRemove != null){
	try {
		int index = Integer.parseUnsignedInt(toRemove);
		if(checkoutSummaryBean.selectProductInCart(index)){
			checkoutSummaryBean.loadSelectedProduct();
			checkoutSummaryBean.removeProductFromCart();
		}
	} catch (NumberFormatException nfe) {
		
	}
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Checkout Summary</title>
	</head>
	<body>
		<h5>Checkout Summary</h5>
		<table class="product">
			<tr class="product">
				<th class="product">#</th>
				<th class="product">Name</th>
				<th class="product">Type</th>
				<th class="product">Color</th>
				<th class="product">Alcohol %</th>
				<th class="product">Filtering</th>
				<th class="product">Container</th>
				<th class="product">Size</th>
				<th class="product">Quantity</th>
				<th class="product">Total Volume</th>
				<th class="product">Price</th>
				<th class="product"></th>
			</tr>
		<%
		int count = checkoutSummaryBean.cartSize();
		float totalPrice = 0;
		
		for(int i = 0; i < count; i++){
			checkoutSummaryBean.selectProductInCart(i);
			checkoutSummaryBean.loadSelectedProduct();
			
			totalPrice += checkoutSummaryBean.getPrice();
			String name = checkoutSummaryBean.getBeerName();
			String type = checkoutSummaryBean.getBeerType().toString();
			String color = checkoutSummaryBean.getBeerColor().toString();
			String alcohol = String.valueOf(checkoutSummaryBean.getBeerAlcohol()) + "%";
			String filtering = checkoutSummaryBean.getBeerFiltering().toString();
			String container = checkoutSummaryBean.getContainerType().toString();
			String size = Volume.toText(checkoutSummaryBean.getContainerVolume());
			String quantity = String.valueOf(checkoutSummaryBean.getQuantity());
			String totalVolume = String.valueOf(checkoutSummaryBean.getContainerVolume() * checkoutSummaryBean.getQuantity());
			String price = Price.toText(checkoutSummaryBean.getPrice() * checkoutSummaryBean.getQuantity());%>
			<tr class="product">
				<td class="product"><%=i+1 %></td>
				<td class="product"><%=name %></td>
				<td class="product"><%=type %></td>
				<td class="product"><%=color %></td>
				<td class="product"><%=alcohol %></td>
				<td class="product"><%=filtering %></td>
				<td class="product"><%=container %></td>
				<td class="product"><%=size %></td>
				<td class="product"><%=quantity %></td>
				<td class="product"><%=totalVolume %></td>
				<td class="product"><%=price %> &euro;</td>
				<td class="product">
					<form action="checkoutSummary.jsp">
						<input type="submit" value="Remove">
						<input type="hidden" name="remove" value=<%=i %>>
					</form>
				</td>
			</tr>
		<%
		}
		%>
		</table>
		<br>
		<div>
			<p>Overall cost</p>
			<p><%=totalPrice %>  &euro;</p>
		</div>
		<form action="checkoutPayment.jsp">
			<input type="submit" value="Confirm Products">
		</form>
		<form action="home.jsp">
			<input type="submit" value="Go back to Shopping">
		</form>
	</body>
</html>