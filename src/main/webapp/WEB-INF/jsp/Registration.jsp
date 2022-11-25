<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Registartion Form</title>
</head>
<body>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">
<h1 align="center">vehicle Management System</h1>
</div>
<h2 align="center">Register a New User</h2>
<p align="center" style="color:red">${error}</p>
<form:form modelAttribute="user" method="post">

<table id="tablestyle" align="center" cellpadding="1" cellspacing="1">
<tr>

    <td>Email:</td>
    <td><form:input type="email" path="usrEmail" size="30" required="required" placeholder="Your Email" /> 
    <font color="red"><form:errors path="usrEmail"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:input type="password" path="usrPassword" size="30" minlength="6" maxlength="12" required="required" placeholder="Between 6 & 12 characters" /> 
    <font color="red"><form:errors path="usrPassword"/></font></td>
</tr>

<tr>
    <td>Name:</td>
    <td><form:input path="name" size="30" required="required"/> 
    <font color="red"><form:errors path="name"/></font></td>
</tr>

<tr>
    <td>Address:</td>
    <td><form:input path="userAddress" size="30" required="required" />
     <font color="red"><form:errors path="userAddress"/></font></td>
</tr>

<tr>
    <td>Contact No: (10 digits)</td>
    <td><form:input path="userPhonenum" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" size="30"  maxlength="10" required="required" placeholder="10 digit phone number"/> 
    <font color="red"><form:errors path="userPhonenum"/></font></td>
</tr>
<tr>
<td>Role:</td>
<td>
    <form:radiobutton path="title" value="customer" required="required"/>		Customer
    <br>

        <form:radiobutton path="title" value="employee" required="required" />    Employee
          <br>

        <form:radiobutton path="title" value="admin" required="required"/>      Admin
              <br>
</td>
</tr>
<tr>
<td></td>
<td><input  type="submit" value="Create" /></td>
</tr>
</table>

</form:form>

</body>
</html>
