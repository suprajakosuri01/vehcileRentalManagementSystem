<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<meta charset="UTF-8">
<title>Admin Home</title>
</head>
<body>
<body>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em"> 
    <a style="color:white;text-decoration:none" href="admin-dashboard.htm?usrEmail=${sessionScope.usrEmail}">Home</a> |
   	<a style="color:white;text-decoration:none" href="users.htm?usrEmail=${sessionScope.usrEmail}">Manage Users</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right">Hi ${sessionScope.usrEmail} |</label>
     
</div>

<h2 align="center">Admin Homepage</h2>


<p align="center" style="size:12">You can manage users in the Manage Users Tab</p>
</body>
</html>