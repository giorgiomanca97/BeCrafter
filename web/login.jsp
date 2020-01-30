<%@page import="error.EmptyFieldException"%>
<%@page import="error.IllegalCharacterException"%>
<%@page import="error.login.LoginException"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="loginBean" scope="request" class="logic.bean.Login_Bean"/>

<%
String email = (loginBean.getEmail() != null) ? loginBean.getEmail() : "";
String password = (loginBean.getPassword() != null) ? loginBean.getPassword() : "";
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
		<title>Login</title>
	</head>
	<h5>Login</h5>
	<br>
	<h2>Be Crafter</h2>
	<body>
		<form action="login.jsp" method="POST">
		<p> email </p>
		<input type="text" id="email" name="email">
		<p> password </p>
		<input type="password" id="password" name="password">
		<br><%
		if(errorMsg != null) {
			%> <p class="error"><%=errorMsg %></p> <%
		} else {%>
			<br><%
		}%>
		<br>
		<input type="submit" value="Login">
		<input type="hidden" name="login" value="1">
		</form>
		<p>not registered ? <a href="register.jsp">click here</a></p>
	</body>
</html>