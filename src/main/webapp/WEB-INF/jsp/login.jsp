<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Login</title>
</head>
<body>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">
<h1 align="center">Vehicle Management System</h1>
</div>
<br><br>
<h2 align="center">Login</h2>
<form:form modelAttribute="user" method="post">
<p style="color:red" align="center">${error}</p>
<table align="center">
<tr>
    <td>Email:</td>
    <td><form:input path="usrEmail" size="30" required="required" /></td>
</tr>
<tr>
    <td>Password:</td>
    <td><form:password path="usrPassword" size="30" required="required" /></td>
</tr>
<tr>
	<td><a href="register.htm">New user?</a></td>
	<td align="center"><input type="submit" value="Login" /></td>
	
</tr>
</table>
<br/>
</form:form>
</body>
</html>
