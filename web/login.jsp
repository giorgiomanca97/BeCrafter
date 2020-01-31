<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.login.LoginException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="loginBean" scope="request" class="logic.bean.Login_Bean"/>
<jsp:setProperty name="loginBean" property="*" />

<%
String email = loginBean.getEmail();
String password = loginBean.getPassword();
String errorMsg = null;

boolean loginPressed = request.getParameter("loginPressed") != null && request.getParameter("loginPressed").equals("1");

if(loginPressed) {
	try{
		loginBean.login();%>
		<jsp:forward page="home.jsp"/><%
	} catch (LoginException le) {
		errorMsg = "Email and Password do not match";
	} catch (EmptyFieldException efe) {
		errorMsg = "Please fill all the fields";
	} catch (IllegalCharacterException ice) {
		errorMsg = "Email and Password do not match";
	}
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<title>Login</title>
	</head>
	<body>
		<h3 align="center">Login</h3>
		<br>
		<div align="center">
			<form action="login.jsp" method="POST">
				<table>
					<tr>
						<td><p> email </p></td>
						<td><input class="text L" type="text" id="email" name="email"></td>
					</tr>
					<tr>
						<td><p> password </p></td>
						<td><input class="text L" type="password" id="password" name="password"></td>
					</tr>
				</table>
				<% if(errorMsg != null) { %> 
				<p class="error"><%=errorMsg %></p> 
				<% } else { %>
				<br>
				<% } %>
				<input class="button L" type="submit" value="Login">
				<input type="hidden" name="loginPressed" value="1">
			</form>
		</div>
		<p align="center">not registered ? <a class="link" href="register.jsp">click here</a></p>
		<br>
		<br>
		<br>
		<div align="center">
			<form action="home.jsp">
				<input class="button M" type="submit" value="Go Back">
			</form>
		</div>
	</body>
</html>