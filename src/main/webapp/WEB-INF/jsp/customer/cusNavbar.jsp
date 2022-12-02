<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

</style>
</head>
<body>
<div style="background-color: #FFDD33">   
    <a  href="cusHome.htm?usrEmail=${sessionScope.usrEmail}">Home
    </a> |
   	<a href="fetchVehicles.htm?usrEmail=${sessionScope.usrEmail}">Browse Vehicles
   	</a> | 
    <a  href="orders.htm?usrEmail=${sessionScope.usrEmail}">Vehicles In Use
    </a> | 
    <a  href="bookedVehicles.htm?usrEmail=${sessionScope.usrEmail}">My Reservations
    </a>
     <a style="float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">SignOut
   </a> 
     <label style="float:right;font-size:23px;">
     Welcome${sessionScope.usrEmail} |
     </label>
   
</div>
</body>
</html>
  