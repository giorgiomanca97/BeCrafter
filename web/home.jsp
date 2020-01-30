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
String searchName = (request.getParameter("searchName") != null) ? request.getParameter("searchName") : "";
boolean cbTypeAle = (request.getParameter("TypeAle") != null);
boolean cbTypeLambic = (request.getParameter("TypeLambic") != null);
boolean cbTypeLager = (request.getParameter("TypeLager") != null);
boolean cbColorLight = (request.getParameter("ColorLight") != null);
boolean cbColorAmber = (request.getParameter("ColorAmber") != null);
boolean cbColorRuby = (request.getParameter("ColorRuby") != null);
boolean cbColorDark = (request.getParameter("ColorDark") != null);
boolean cbContBottle = (request.getParameter("ContainerBottle") != null);
boolean cbContCan = (request.getParameter("ContainerCan") != null);
boolean cbContBarrel = (request.getParameter("ContainerBarrel") != null);
boolean cbFilteringYes = (request.getParameter("FilteringYes") != null);
boolean cbFilteringNo = (request.getParameter("FilteringNo") != null);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Home</title>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<h1>BeCrafter</h1>
				</td>
				<td>
					<form action="checkoutSummary.jsp">
						<input type="submit" value="Checkout">
					</form>
				</td>
				<td>
					<form action="checkOrder.jsp">
						<input type="submit" value="Check Order">
					</form>
				</td>
				<td>
					<form action="login.jsp">
						<input type="submit" value="Login">
					</form>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="grid">
					<form id="homeForm" action="home.jsp">
						<div>
							<h5>Search Beer</h5>
							<input type="text" name="searchName" value="<%=searchName %>"><br>
							<input type="submit" value="Search"><br>
						</div>
						<br>
						<div>
							<h5>Filter Beers</h5>
							<h6>Type</h6>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeAle"  <%if(cbTypeAle){%>checked<%}%>>Ale<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLambic" <%if(cbTypeLambic){%>checked<%}%>>Lambic<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLager" <%if(cbTypeLager){%>checked<%}%>>Lager<br>
							<h6>Color</h6>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorLight" <%if(cbColorLight){%>checked<%}%>>Light<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorAmber" <%if(cbColorAmber){%>checked<%}%>>Amber<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorRuby" <%if(cbColorRuby){%>checked<%}%>>Ruby<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorDark" <%if(cbColorDark){%>checked<%}%>>Dark<br>
							<h6>Container</h6>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBottle" <%if(cbContBottle){%>checked<%}%>>Bottle<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerCan" <%if(cbContCan){%>checked<%}%>>Can<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBarrel" <%if(cbContBarrel){%>checked<%}%>>Barrel<br>
							<h6>Filtering</h6>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringYes" <%if(cbFilteringYes){%>checked<%}%>>Filtered<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringNo" <%if(cbFilteringNo){%>checked<%}%>>Unfiltered<br>
						</div>
					</form>
				</td>
				<td class="grid">
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
									<input type="submit" value="Buy">
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
				</td>
			</tr>
		</table>
	</body>
</html>