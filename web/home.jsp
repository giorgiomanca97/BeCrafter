<%@page import="logic.entity.BeerFiltering"%>
<%@page import="logic.entity.ContainerType"%>
<%@page import="logic.entity.BeerColor"%>
<%@page import="logic.entity.BeerType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>
<%@page import="error.ProductNotFoundException"%>
<%@page import="error.StorableIllegalQuantityException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="homeBean" scope="request" class="logic.bean.Home_Bean"/>

<%
boolean logoutPressed = request.getParameter("logoutPressed") != null && request.getParameter("logoutPressed").equals("1");

String searchName = (request.getParameter("searchName") != null) ? request.getParameter("searchName") : "";
boolean cbTypeAle = (request.getParameter("TypeAle") != null && request.getParameter("TypeAle").equals("on"));
boolean cbTypeLambic = (request.getParameter("TypeLambic") != null && request.getParameter("TypeLambic").equals("on"));
boolean cbTypeLager = (request.getParameter("TypeLager") != null && request.getParameter("TypeLager").equals("on"));
boolean cbColorLight = (request.getParameter("ColorLight") != null && request.getParameter("ColorLight").equals("on"));
boolean cbColorAmber = (request.getParameter("ColorAmber") != null && request.getParameter("ColorAmber").equals("on"));
boolean cbColorRuby = (request.getParameter("ColorRuby") != null && request.getParameter("ColorRuby").equals("on"));
boolean cbColorDark = (request.getParameter("ColorDark") != null && request.getParameter("ColorDark").equals("on"));
boolean cbContBottle = (request.getParameter("ContainerBottle") != null && request.getParameter("ContainerBottle").equals("on"));
boolean cbContCan = (request.getParameter("ContainerCan") != null && request.getParameter("ContainerCan").equals("on"));
boolean cbContBarrel = (request.getParameter("ContainerBarrel") != null && request.getParameter("ContainerBarrel").equals("on"));
boolean cbFilteringYes = (request.getParameter("FilteringYes") != null && request.getParameter("FilteringYes").equals("on"));
boolean cbFilteringNo = (request.getParameter("FilteringNo") != null && request.getParameter("FilteringNo").equals("on"));
String logged = homeBean.loggedCustomer();

if(logoutPressed && logged != null) {
	homeBean.logoutCustomer(logged);
	logged = null;
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/home.css">
		<title>Home</title>
	</head>
	<body>
		<h1 id="title">BeCrafter</h1>
		<div class="grid-products">
			<div id="search">
				<form action="home.jsp">
					<h5>Search Beer</h5>
					<input class="text M" type="text" name="searchName" value="<%=searchName %>"><br>
					<input class="button M" type="submit" value="Search"><br>
					<br>
					<h5>Filter Beers</h5>
					<h6>Type</h6>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeAle"  <%if(cbTypeAle){%>checked<%}%>>Ale<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLambic" <%if(cbTypeLambic){%>checked<%}%>>Lambic<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLager" <%if(cbTypeLager){%>checked<%}%>>Lager<br>
					<h6>Color</h6>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorLight" <%if(cbColorLight){%>checked<%}%>>Light<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorAmber" <%if(cbColorAmber){%>checked<%}%>>Amber<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorRuby" <%if(cbColorRuby){%>checked<%}%>>Ruby<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorDark" <%if(cbColorDark){%>checked<%}%>>Dark<br>
					<h6>Container</h6>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBottle" <%if(cbContBottle){%>checked<%}%>>Bottle<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerCan" <%if(cbContCan){%>checked<%}%>>Can<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBarrel" <%if(cbContBarrel){%>checked<%}%>>Barrel<br>
					<h6>Filtering</h6>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringYes" <%if(cbFilteringYes){%>checked<%}%>>Filtered<br>
					<input class="checkbox" type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringNo" <%if(cbFilteringNo){%>checked<%}%>>Unfiltered<br>
				</form>
			</div>
			<div id="products">
				<table class="product">
					<tr class="product">
						<th class="product">Name</th>
						<th class="product">Type</th>
						<th class="product">Color</th>
						<th class="product">Alcohol %</th>
						<th class="product">Filtering</th>
						<th class="product">Container</th>
						<th class="product">Size</th>
						<th class="product">Price</th>
						<th class="product"></th>
					</tr>
				<%
				ArrayList<BeerType> beerTypes = new ArrayList<BeerType>();
				ArrayList<BeerColor> beerColors = new ArrayList<BeerColor>();
				ArrayList<ContainerType> containerTypes = new ArrayList<ContainerType>();
				ArrayList<BeerFiltering> beerFilterings = new ArrayList<BeerFiltering>();
				
				if(cbTypeAle) beerTypes.add(BeerType.ALE);
				if(cbTypeLambic) beerTypes.add(BeerType.LAMBIC);
				if(cbTypeLager) beerTypes.add(BeerType.LAGER);

				if(cbColorLight) beerColors.add(BeerColor.LIGHT);
				if(cbColorAmber) beerColors.add(BeerColor.AMBER);
				if(cbColorRuby) beerColors.add(BeerColor.RUBY);
				if(cbColorDark) beerColors.add(BeerColor.DARK);

				if(cbContBottle) containerTypes.add(ContainerType.BOTTLE);
				if(cbContCan) containerTypes.add(ContainerType.CAN);
				if(cbContBarrel) containerTypes.add(ContainerType.BARREL);
				
				if(cbFilteringYes) beerFilterings.add(BeerFiltering.FILTERED);
				if(cbFilteringNo) beerFilterings.add(BeerFiltering.UNFILTERED);
				
				homeBean.displayProducts(beerTypes, beerColors, containerTypes, beerFilterings, searchName);
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
					<tr class="product">
						<td class="product"><%=name %></td>
						<td class="product"><%=type %></td>
						<td class="product"><%=color %></td>
						<td class="product"><%=alcohol %></td>
						<td class="product"><%=filtering %></td>
						<td class="product"><%=container %></td>
						<td class="product"><%=size %></td>
						<td class="product"><%=price %> &euro;</td>
						<td class="product">
							<form action="buyBeer.jsp">
								<input class="button XS" type="submit" value="Buy">
								<input type="hidden" name="beerId" value="<%=homeBean.getBeerId() %>">
								<input type="hidden" name="containerType" value="<%=homeBean.getContainerType().name() %>">
								<input type="hidden" name="volume" value="<%=homeBean.getContainerVolume() %>">
							</form>
						</td>
					</tr>
				<%
				}
				%>
				</table>
			</div>
			<div id="action" align="right">
				<%if(logged == null) {%>
				<br>
				<br>
				<form action="login.jsp">
					<input class="button M" type="submit" value="Login">
				</form>
				<%} else {%>
				<b><%=logged %></b><br>
				<br>
				<form action="home.jsp">
					<input class="button M" type="submit" value="Logout">
					<input type="hidden" name="logoutPressed" value="1">
				</form>
				<%} %>
				<br>
				<form action="checkOrder.jsp">
					<input class="button M" type="submit" value="Check Order">
				</form>
				<form action="checkoutSummary.jsp">
					<input class="button M" type="submit" value="Checkout">
				</form>
			</div>
		</div>
	</body>
</html>