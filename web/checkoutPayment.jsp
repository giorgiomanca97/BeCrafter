<%@page import="error.EmptyCartException"%>
<%@page import="error.login.InvalidEmailException"%>
<%@page import="error.login.UsedEmailException"%>
<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.WrongFieldException"%>
<%@page import="error.PaymentRefusedException"%>
<%@page import="error.id.IdException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="checkoutPaymentBean" scope="request" class="logic.bean.CheckoutPayment_Bean"/>
<jsp:setProperty name="checkoutPaymentBean" property="*" />

<%
String email = (checkoutPaymentBean.getEmail() != null) ? checkoutPaymentBean.getEmail() : "";
String firstName = (checkoutPaymentBean.getFirstName() != null) ? checkoutPaymentBean.getFirstName() : "";
String lastName = (checkoutPaymentBean.getLastName() != null) ? checkoutPaymentBean.getLastName() : "";
String address = (checkoutPaymentBean.getAddress() != null) ? checkoutPaymentBean.getAddress() : "";
String city = (checkoutPaymentBean.getCity() != null) ? checkoutPaymentBean.getCity() : "";
String country = (checkoutPaymentBean.getCountry() != null) ? checkoutPaymentBean.getCountry() : "";
String postalCode = (checkoutPaymentBean.getPostalCode() != null) ? checkoutPaymentBean.getPostalCode() : "";
String phoneNumber = (checkoutPaymentBean.getPhoneNumber() != null) ? checkoutPaymentBean.getPhoneNumber() : "";
String creditCard = (checkoutPaymentBean.getCreditCard() != null) ? checkoutPaymentBean.getCreditCard() : "";

boolean confirmPurchase = (request.getParameter("confirmAction") != null && request.getParameter("confirmAction").equals("1"));
String error = null;
String orderId = null;
if(confirmPurchase){
	try {
		orderId = checkoutPaymentBean.confirmPurchase();%>
		<jsp:forward page="checkoutConfirmation.jsp">
			<jsp:param name="orderId" value="<%=orderId %>"/>
		</jsp:forward>
		<% 
	} catch(InvalidEmailException iee) {
		error = "Wrong email format";
	} catch(UsedEmailException uee) {
		error = "This email is already registered";
	} catch(EmptyFieldException uee) {
		error = "Please fill all the empty fields";
	} catch (IllegalCharacterException ice) {
		error = "Please remove the ' character from the fields";
	} catch (WrongFieldException wfe) {
		error = "Some fields are not correct";
	} catch (PaymentRefusedException pre) {
		error ="Payment refused. Please retry";
	} catch (EmptyCartException ece) {
		error = "The cart is empty. Please choose a product first";
	} catch (IdException ie) {
		error = "Unexpected Error. Please retry";
	}
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
		<form action="checkoutPayment.jsp" method="POST">
			<table>
				<tr>
					<td>email</td>
					<td><input type="text" id="email" name="email" value="<%=email %>"></td>
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
		<% if(error != null){ %>
		<p class="error"><%=error %></p>
		<% } %>
	</body>
</html>