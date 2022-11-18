<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Confirm Edit</title>
</head>
<body>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em"> 
    <a style="color:white;text-decoration:none" href="admin-dashboard.htm?usrEmail=${sessionScope.usrEmail}">Home</a> |
   	<a style="color:white;text-decoration:none" href="users.htm?usrEmail=${sessionScope.usrEmail}">Manage Users</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
        <label style="float:right">Hi ${sessionScope.usrEmail} |</label>
     
</div>

<h3 align="center">Edit User Details</h3>

<form action="userupdate.htm" method="POST">

<input type="hidden" name="id" value="${user.getusrId()}"/>

<table id="tablestyle" cellpadding="1" cellspacing="1" align="center">
	<tr>
	<td>email:</td>
	<td>${user.getusrEmail()}</td>
	</tr>
	<tr>
	<td>Name:</td>
	<td>${user.getName()}</td>
	</tr>
	<tr>
	<td>Address:</td>	
	<td><input type="text" name="address" value="${user.getuserAddress()}" style="font-weight: bold" required="required"/>
	<p style="color:red">${addressErr}</p>
	</td>
	</tr>
	<tr>
	<td>Contact: (10 digits)</td>
	<td><input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" maxlength="10"
	 name="contact" value="${user.getuserPhonenum()}" style="font-weight: bold" required="required"/>
	<p style="color:red">${contactErr}</p>
	</td>
	</tr>
	<tr>
	<td>Title:</td>
	<td>${user.gettitle()}</td>
	</tr>
</table>
<br>
	<p align="center"><input style="width:100px"  type="submit" value="Edit"></p>
</form>
</body>
</html>