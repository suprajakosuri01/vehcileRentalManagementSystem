<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%>

a{
font-size:23px;
color: #ff0000;
text-decoration:none;
}
.Udform{

    padding-left: 300px;
    padding-right: 200px;
    padding-top: ‒50;
    padding-top: 50px;

color:black
}
</style>
<title> Delete User</title>
</head>
<body>
<div style="background-color:#FFDD33"> 
    
   <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a> 
 <a  href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Get listof Users</a>
    <a style="float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} |</label>
     
</div>
<form style="text-align:center" action="userdelete.htm" method="POST">


<input type="hidden" name="usrdel" value="${user.usrId}"/>

<div class="Udform">
<h3>Delete User </h3>

	
	<label>User Email:</label><br>
	<td>${user.usrEmail}</td>
	<br><br>
		<label>User Name:</label><br>
	<td>${user.name}</td>
		<br><br>	
	<label>User Address:</label><br>
	<td>${user.userAddress}</td>
	<br><br>
	<label>User Contact:</label><br>
	<td>${user.userPhonenum}</td>
<br><br>
<label>Title:<label>
	<td>${user.title}</td>
	
<input type="hidden" name="usrEmail" value="${user.usrEmail}"/>
<input type="hidden" name="name" value="${user.name}"/>
<input type="hidden" name="usrPassword" value="${user.usrPassword}"/>
<input type="hidden" name="userAddress" value="${user.userAddress}"/>
<input type="hidden" name="userPhonenum" value="${user.userPhonenum}"/>
<input type="hidden" name="title" value="${user.title}"/>
<br><br>
<input type="submit" value="Delete"> 
</form>
</body>
</html>