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

String loginEmail = email;
if(loginAction && !logged && !confirmPurchase){
	email = "";
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Checkout Payment</title>
	</head>
	<body>
		<h2>Checkout Summary</h2>
		<table>
			<tr>
				<td class="grid">
					<form action="checkoutPayment.jsp" method="POST">
					<table>
						<tr>
							<td>email</td>
							<td><input type="text" id="email" name="email" value="<%=email %>" <%if(logged) {%>readonly<%}%>></td>
						</tr>
						<tr>
							<td>first name</td>
							<td><input type="text" id="firstName" name="firstName" value="<%=firstName %>"></td>
						</tr>
						<tr>
							<td>last name</td>
							<td><input type="text" id="lastName" name="lastName" value="<%=lastName %>"></td>
						</tr>
						<tr>
							<td>address</td>
							<td><input type="text" id="address" name="address" value="<%=address %>"></td>
						</tr>
						<tr>
							<td>city</td>
							<td><input type="text" id="city" name="city" value="<%=city %>"></td>
						</tr>
						<tr>
							<td>country</td>
							<td><input type="text" id="country" name="country" value="<%=country %>"></td>
						</tr>
						<tr>
							<td>postal code</td>
							<td><input type="text" id="postalCode" name="postalCode" value="<%=postalCode %>"></td>
						</tr>
						<tr>
							<td>phone number</td>
							<td><input type="text" id="phoneNumber" name="phoneNumber" value="<%=phoneNumber %>"></td>
						</tr>
						<tr>
							<td>credit card number</td>
							<td><input type="text" id="creditCard" name="creditCard" value="<%=creditCard %>"></td>
						</tr>
					</table>
					<input type="submit" value="Confirm Purchase">
					<input type="hidden" name="confirmAction" value="1"> 
					</form>
					<form action="checkoutSummary.jsp">
						<input type="submit" value="Go back to Summary">
					</form>
					<% if(purchaseError != null){ %>
					<p class="error"><%=purchaseError %></p>
					<% } %>
				</td>
				<td class="grid">
					<form action="checkoutPayment.jsp" method="POST">
						<input type="text" id="email" name="email" value="<%=loginEmail%>" <%if(logged) {%>disabled<%}%>><br><br>
						<input type="password" id="password" name="password" <%if(logged) {%>disabled<%}%>><br><br>
						<input type="hidden" name="loginAction" value="1">
						<input type="submit" value="Login" <%if(logged) {%>disabled<%}%>>
					</form>
					<% if(loginError != null){ %>
					<p class="error"><%=loginError %></p>
					<% } %>
				</td>
			</tr>
		</table>
	</body>
</html>