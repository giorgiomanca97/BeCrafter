<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="homeBean" scope="request" class="logic.bean.Home_Bean"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Home</title>
	</head>
	<body>
		<table>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Color</th>
				<th>Alcohol %</th>
				<th>Filtering</th>
				<th>Container</th>
				<th>Size</th>
				<th>Price</th>
			</tr>
		<%
		homeBean.displayProducts(null, null, null, null, null);
		int count = homeBean.countDisplayedProducts();
		
		for(int i = 0; i < count; i++){
			homeBean.loadDisplayedProductAt(i);
			String name = homeBean.getBeerName();
			String type = homeBean.getBeerType().toString();
			String color = homeBean.getBeerColor().toString();
			String alcohol = String.valueOf(homeBean.getBeerAlcohol()) + "%";
			String filtering = homeBean.getBeerFiltering().toString();
			String container = homeBean.getContainerType().toString();
			String size = Volume.toText(homeBean.getContainerVolume());
			String price = Price.toText(homeBean.getPrice());%>
			<tr>
				<td><%=name %></td>
				<td><%=type %></td>
				<td><%=color %></td>
				<td><%=alcohol %></td>
				<td><%=filtering %></td>
				<td><%=container %></td>
				<td><%=size %></td>
				<td><%=price %> &euro;</td>
			</tr>
		<%
		}
		%>
		</table>
	</body>
</html>