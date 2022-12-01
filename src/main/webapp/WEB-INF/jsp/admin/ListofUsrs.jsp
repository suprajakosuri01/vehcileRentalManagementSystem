<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Users</title>
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
  <div style="background-color:#FFDD33"> 
    
   <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a> 
 <a  href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Get listof Users</a>
    <a style="float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} |</label>
     
</div>

<table id="table" border="1">
<h2>List of Registered Users</h2>
<br>
	<th>User ID</th>
	<th>User Email</th>
	<th>User Name</th>
	<th>Address</th>
	<th>Phone Number</th>
	<th>Title</th>
	<th>Action</th>
	
	<c:forEach items="${user}" var="user">
	<c:if test="${user.title ne 'admin'}">
	<tr>
		<td>${user.usrId}</td>
		<td>${user.usrEmail}</td>
		<td>${user.name}</td>
		<td>${user.userAddress}</td>
		<td>${user.userPhonenum}</td>
		<td>${user.title}</td>
		<td><a style="color: #00b33c"href="usermodify.htm?usrId=${user.usrId}">Edit</a> | 
		<a href="userdelete.htm?usrId=${user.usrId}">Delete</a>
		</td>
	</tr>
	</c:if>
	</c:forEach>

</table>

</body>
</html>