<%@page import="logic.entity.BeerFiltering"%>
<%@page import="logic.entity.ContainerType"%>
<%@page import="logic.entity.BeerColor"%>
<%@page import="logic.entity.BeerType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.entity.Volume"%>
<%@page import="logic.entity.Price"%>

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
		<h1>BeCrafter</h1>
		<table>
			<tr>
				<td>
					<form id="homeForm" action="home.jsp">
						<div>
							<strong>Search Beer</strong><br>
							<input type="text" name="searchName" value="<%=searchName %>"><br>
							<input type="submit" value="Search"><br>
						</div>
						<br>
						<div>
							<strong>Filter Beers</strong><br>
							<b>Type</b><br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeAle"  <%if(cbTypeAle){%>checked<%}%>>Ale<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLambic" <%if(cbTypeLambic){%>checked<%}%>>Lambic<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="TypeLager" <%if(cbTypeLager){%>checked<%}%>>Lager<br>
							<b>Color</b><br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorLight" <%if(cbColorLight){%>checked<%}%>>Light<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorAmber" <%if(cbColorAmber){%>checked<%}%>>Amber<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorRuby" <%if(cbColorRuby){%>checked<%}%>>Ruby<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ColorDark" <%if(cbColorDark){%>checked<%}%>>Dark<br>
							<b>Container</b><br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBottle" <%if(cbContBottle){%>checked<%}%>>Bottle<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerCan" <%if(cbContCan){%>checked<%}%>>Can<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="ContainerBarrel" <%if(cbContBarrel){%>checked<%}%>>Barrel<br>
							<b>Filtering</b><br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringYes" <%if(cbFilteringYes){%>checked<%}%>>Filtered<br>
							<input type="checkbox" onchange="document.getElementById('homeForm').submit()" name="FilteringNo" <%if(cbFilteringNo){%>checked<%}%>>Unfiltered<br>
						</div>
					</form>
				</td>
				<td>
					<table>
						<tr>
							<th>#</th>
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
						<tr>
							<td><%=i %></td>
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
				</td>
			</tr>
		</table>
	</body>
</html>