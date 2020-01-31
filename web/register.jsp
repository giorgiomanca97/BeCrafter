<%@page import="error.login.InvalidEmailException"%>
<%@page import="error.login.UsedEmailException"%>
<%@page import="error.login.InvalidPasswordException"%>
<%@page import="error.login.PasswordMatchingException"%>
<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.WrongFieldException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="registerBean" scope="request" class="logic.bean.Register_Bean"/>
<jsp:setProperty name="registerBean" property="*" />

<%
boolean register = request.getParameter("registerAction") != null && request.getParameter("registerAction").equals("1");
boolean agreement = request.getParameter("agreement") != null && request.getParameter("agreement").equals("on");
String error = null;
if(register){
	if(agreement){
		try {
			registerBean.register();%>
			<jsp:forward page="registerConfirmation.jsp"/>
			<%
		} catch (InvalidEmailException iee) {
			error = "The email is not a valid mail";
		} catch (UsedEmailException uee) {
			error = "The email is already registered";
		} catch (InvalidPasswordException ipe) {
			error = "The password do not match the specifics";
		} catch (EmptyFieldException efe) {
			error = "Please fill all the fields";
		} catch (IllegalCharacterException ice) {
			error = "Please remove the ' character from the fields";
		} catch (WrongFieldException wfe) {
			error = "Some fields are not correct";
		} catch (PasswordMatchingException psm) {
			error = "The password fields do not match";
		}
	} else {
		error = "Please accept the agreement to register";
	}
}

String email = registerBean.getEmail();
String firstName = registerBean.getFirstName();
String lastName = registerBean.getLastName();
String address = registerBean.getAddress();
String city = registerBean.getCity();
String country = registerBean.getCountry();
String postalCode = registerBean.getPostalCode();
String phoneNumber = registerBean.getPhoneNumber();
String creditCard = registerBean.getCreditCard();

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<title>Home</title>	
	</head>
	<body>
		<form action="register.jsp" method="POST">
			<table>
				<tr>
					<td>email</td>
					<td><input type="text" id="email" name="email" value="<%=email %>"></td>
				</tr>
				<tr>
					<td>password *</td>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td>confirm password</td>
					<td><input type="password" id="confirmPassword" name="confirmPassword"></td>
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
					<td>credit card number *</td>
					<td><input type="text" id="creditCard" name="creditCard" value="<%=creditCard %>"></td>
				</tr>
			</table>
			<input type="checkbox" name="agreement">I agree with conditions and terms<br>
			<input type="submit" value="Register">
			<input type="hidden" name="registerAction" value="1"> 
		</form>
		<% if(error != null){ %>
		<p class="error"><%=error %></p>
		<% } else { %>
		<br>
		<% } %>
		<p>* The password must be at least eight characters long and must have at least one uppercase letter, one lowercase letter, and one number</p>
		<p>** The credit card must be expressed as XXXX-XXXX-XXXX-XXXX</p>
	</body>
</html>