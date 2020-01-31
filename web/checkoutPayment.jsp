<%@page import="error.EmptyCartException"%>
<%@page import="error.login.InvalidEmailException"%>
<%@page import="error.login.UsedEmailException"%>
<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.WrongFieldException"%>
<%@page import="error.PaymentRefusedException"%>
<%@page import="error.id.IdException"%>
<%@page import="error.login.InexistentEmailException"%>
<%@page import="error.login.WrongPasswordException"%>
<%@page import="error.login.LoginException"%>


<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="checkoutPaymentBean" scope="request" class="logic.bean.CheckoutPayment_Bean"/>
<jsp:setProperty name="checkoutPaymentBean" property="*" />

<%

String preEmail = checkoutPaymentBean.loggedCustomer();
boolean preLogged = preEmail != null;

boolean loginAction = (request.getParameter("loginAction") != null && request.getParameter("loginAction").equals("1"));
boolean logged = false;
String loginError = null;
if(loginAction){
	try {
		checkoutPaymentBean.login();
		logged = true;
	} catch (InexistentEmailException | WrongPasswordException | IllegalCharacterException e) {
		loginError = "Email and Password do not match";
	} catch (EmptyFieldException efe) {
		loginError = "Please fill all the fields";
	} catch (LoginException le) {
		loginError = "Something unexpected happen. Please retry";
	}
}

if(logged){
	checkoutPaymentBean.loadLoggedCustomer();
}

String email = checkoutPaymentBean.getEmail();
String firstName = checkoutPaymentBean.getFirstName();
String lastName = checkoutPaymentBean.getLastName();
String address = checkoutPaymentBean.getAddress();
String city = checkoutPaymentBean.getCity();
String country = checkoutPaymentBean.getCountry();
String postalCode = checkoutPaymentBean.getPostalCode();
String phoneNumber = checkoutPaymentBean.getPhoneNumber();
String creditCard = checkoutPaymentBean.getCreditCard();

boolean confirmPurchase = (request.getParameter("confirmAction") != null && request.getParameter("confirmAction").equals("1"));
String purchaseError = null;
String orderId = null;
if(confirmPurchase){
	try {
		orderId = checkoutPaymentBean.confirmPurchase();%>
		<jsp:forward page="checkoutConfirmation.jsp">
			<jsp:param name="orderId" value="<%=orderId %>"/>
		</jsp:forward>
		<% 
	} catch(InvalidEmailException iee) {
		purchaseError = "Wrong email format";
	} catch(UsedEmailException uee) {
		purchaseError = "This email is already registered";
	} catch(EmptyFieldException uee) {
		purchaseError = "Please fill all the empty fields";
	} catch (IllegalCharacterException ice) {
		purchaseError = "Please remove the ' character from the fields";
	} catch (WrongFieldException wfe) {
		purchaseError = "Some fields are not correct";
	} catch (PaymentRefusedException pre) {
		purchaseError ="Payment refused. Please retry";
	} catch (EmptyCartException ece) {
		purchaseError = "The cart is empty. Please choose a product first";
	} catch (IdException ie) {
		purchaseError = "Unexpected Error. Please retry";
	}
}

if(preLogged){
	email = preEmail;
}

String loginEmail = email;
if(loginAction && !preLogged && !logged && !confirmPurchase){
	email = "";
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/checkoutPayment.css">
		<title>Checkout Payment</title>
	</head>
	<body>
		<h3 align="center">Checkout Summary</h3>
		<br>
		<div class="grid">
			<div></div>
			<div class="grid-data">
				<div>
					<div align="center">
						<form action="checkoutPayment.jsp" method="POST">
							<table>
								<tr>
									<td><p>email</p></td>
									<td><input class="text L" type="text" id="email" name="email" value="<%=email %>" <%if(logged || preLogged) {%>readonly<%}%>></td>
								</tr>
								<tr>
									<td><p>first name</p></td>
									<td><input class="text L" type="text" id="firstName" name="firstName" value="<%=firstName %>"></td>
								</tr>
								<tr>
									<td><p>last name</p></td>
									<td><input class="text L" type="text" id="lastName" name="lastName" value="<%=lastName %>"></td>
								</tr>
								<tr>
									<td><p>address</p></td>
									<td><input class="text L" type="text" id="address" name="address" value="<%=address %>"></td>
								</tr>
								<tr>
									<td><p>city</p></td>
									<td><input class="text L" type="text" id="city" name="city" value="<%=city %>"></td>
								</tr>
								<tr>
									<td><p>country</p></td>
									<td><input class="text L" type="text" id="country" name="country" value="<%=country %>"></td>
								</tr>
								<tr>
									<td><p>postal code</p></td>
									<td><input class="text L" type="text" id="postalCode" name="postalCode" value="<%=postalCode %>"></td>
								</tr>
								<tr>
									<td><p>phone number</p></td>
									<td><input class="text L" type="text" id="phoneNumber" name="phoneNumber" value="<%=phoneNumber %>"></td>
								</tr>
								<tr>
									<td><p>credit card number *</p></td>
									<td><input class="text L" type="text" id="creditCard" name="creditCard" value="<%=creditCard %>"></td>
								</tr>
							</table>
							<br>
							<input class="button L" type="submit" value="Confirm Purchase">
							<input type="hidden" name="confirmAction" value="1"> 
						</form>
						<% if(purchaseError != null){ %>
						<p class="error"><%=purchaseError %></p>
						<% } else {%>
						<br>
						<% } %>
						<p id="info">* The credit card must be expressed as XXXX-XXXX-XXXX-XXXX</p>
					</div>
				</div>
				<div>
					<form action="checkoutPayment.jsp" method="POST">
						<p>email</p>
						<input class="text L" type="text" id="email" name="email" value="<%=loginEmail%>" <%if(logged || preLogged) {%>readonly<%}%>><br><br>
						<p>password</p>
						<input class="text L" type="password" id="password" name="password" <%if(logged) {%>disabled<%}%>><br><br>
						<input type="hidden" name="loginAction" value="1">
						<br>
						<input class="button M" type="submit" value="Login" <%if(logged) {%>disabled<%}%>>
					</form>
					<% if (logged) { %>
					<p>Logged as <%=checkoutPaymentBean.getEmail() %></p>
					<% } else if(preLogged) { %>
					<p>Please insert the password to continue</p>
					<% } else { %>
					<p>Please login to auto-fill your billing details</p>
					<% } %>
					<% if(loginError != null){ %>
					<p class="error"><%=loginError %></p>
					<% } else {%>
					<br>
					<% } %>
				</div>
			</div>
			<div></div>
			
			<div></div>
			<div align="center">
				<br><br><br>
				<form action="checkoutSummary.jsp">
					<input class="button M" type="submit" value="Go back to Summary">
				</form>
			</div>
			<div></div>
		</div>
	</body>
</html>