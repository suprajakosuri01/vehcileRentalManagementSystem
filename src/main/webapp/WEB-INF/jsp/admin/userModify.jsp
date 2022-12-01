<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="/WEB-INF/css/style.css"%>
a{
font-size:23px;
color: #ff0000;
text-decoration:none;
}

.Umform{

    padding-left: 400px;
    padding-right: 200px;
    padding-top: ‒50;
    padding-top: 50px;

color:black
}
</style>
<meta charset="UTF-8">
<title>User Modify Page</title>
</head>
<body>
<div style="background-color:#FFDD33"> 
    
   <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a> 
 <a  href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Get listof Users</a>
    <a style="float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} |</label>
     
</div>

<form action="usermodify.htm"" method="POST">

<input type="hidden" name="uid2" value="${user.getUsrId()}"/>
<div class="Umform">

<h2>Modify User </h2>

<label>User Email:</label><br>
	<td>${user.getUsrEmail()}</td>

<br><br>
	<label> User Name:</label><br>
	<td>${user.getName()}</td>

<br><br>

	<label>User Address:</label><br>
	<input type="text" name="userAddress" size="40"value="${user.getUserAddress()}" 
     required="required"/>
<br><br>

	<label>Mobile Number(10):</label><br>
	<input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" maxlength="10"
	 name="userPhonenum" value="${user.getUserPhonenum()}" 
          required="required"/>
	
	<br><br>
	
		<label>Title:</label><br>
	<td>${user.getTitle()}</td>
	
<br><br>
	<p><input  type="submit" value="Edit"></p>
</form>
</body>
</html>