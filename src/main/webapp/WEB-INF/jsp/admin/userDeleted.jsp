<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
<title>Deleted User</title>
</head>
<body>
<div style="background-color:#FFDD33"> 
    
   *<a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a> *
 <a  href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Get listof Users</a>
    <a style="float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} |</label>   
</div>
<h2 align="center">Deleted User Success</h2>
</body>
</html>