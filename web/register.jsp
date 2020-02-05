<%@page import="error.login.InvalidEmailException"%>
<%@page import="error.login.UsedEmailException"%>
<%@page import="error.login.InvalidPasswordException"%>
<%@page import="error.login.PasswordMatchingException"%>
<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.WrongFieldException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="registerBean" scope="request" class="logic.bean.RegisterBean"/>
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
		<link rel="stylesheet" type="text/css" href="css/register.css">
		<title>Home</title>	
	</head>
	<body>
		<h3 align="center">Register</h3>
		<br>
		<form action="register.jsp" method="POST">
			<div align="center">
				<table class="field">
					<tr class="field">
						<td class="field"><p class="field">email</p></td>
						<td class="field"><input class="text L" type="text" id="email" name="email" value="<%=email %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p class="field">password *</p></td>
						<td class="field"><input class="text L" type="password" id="password" name="password"></td>
					</tr>
					<tr class="field">
						<td class="field"><p class="field">confirm password</p></td>
						<td class="field"><input class="text L" type="password" id="confirmPassword" name="confirmPassword"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>first name</p></td>
						<td class="field"><input class="text L" type="text" id="firstName" name="firstName" value="<%=firstName %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>last name</p></td>
						<td class="field"><input class="text L" type="text" id="lastName" name="lastName" value="<%=lastName %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>address</p></td>
						<td class="field"><input class="text L" type="text" id="address" name="address" value="<%=address %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>city</p></td>
						<td class="field"><input class="text L" type="text" id="city" name="city" value="<%=city %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>country</p></td>
						<td class="field"><input class="text L" type="text" id="country" name="country" value="<%=country %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>postal code</p></td>
						<td class="field"><input class="text L" type="text" id="postalCode" name="postalCode" value="<%=postalCode %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>phone number</p></td>
						<td class="field"><input class="text L" type="text" id="phoneNumber" name="phoneNumber" value="<%=phoneNumber %>"></td>
					</tr>
					<tr class="field">
						<td class="field"><p>credit card number **</p></td>
						<td class="field"><input class="text L" type="text" id="creditCard" name="creditCard" value="<%=creditCard %>"></td>
					</tr>
				</table>
				<div id="terms">
					<input class="checkbox" type="checkbox" name="agreement"> &nbsp; I agree with conditions and terms
				</div>
				<br>
				<% if(error != null){ %>
				<p class="error"><%=error %></p>
				<% } else { %>
				<br>
				<% } %>
				<input class="button L" type="submit" value="Register">
				<input type="hidden" name="registerAction" value="1">
			</div>
		</form>
		<br>
		<div align="center">
			<br>
			<br>
			<div align="center">
				<form action="login.jsp">
					<input class="button M" type="submit" value="Go Back">
				</form>
			</div>
			<br>
			<br>
			<p class="note">* The password must be at least eight characters long and must have at least one uppercase letter, one lowercase letter, and one number</p>
			<p class="note">** The credit card must be expressed as XXXX-XXXX-XXXX-XXXX</p>
		</div>
	</body>
</html>