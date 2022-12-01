<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<meta charset="UTF-8">
<title>Vehicles in use</title>
</head>
<body>
<jsp:include page="cusNavbar.jsp"/>

<c:choose>
<c:when test="${vehicles.size() gt 0}"> 
<table id="tablestyle" border="1">
<h2 align="center">vehicles in use</h2>
	<th></th>
	<th>licensePlate</th>
	<th>Model</th>
	<th>Year</th>
	<th>Reserved Date</th>
	<th>Reserved Until</th>
	<th>Return Date</th>	
	
	<c:forEach items="${vehicles}" var="vehicle">
	<tr>
		<td><img width="150" height="150"  src="/vehicle/images/${vehicle.imagePath}"/></td>
		<td>${vehicle.licensePlate}</td>
		<td>${vehicle.model}</td>
		<td>${vehicle.year}</td>
		<td>${vehicle.rentStartDate}</td>
		<td>${vehicle.rentEndDate}</td>
		<td>${vehicle.rentReturnDate}</td>
	</tr>
	</c:forEach>
</table>
</c:when>
<c:otherwise>
<h3 align="center">You have no vehciles in use.</h3>
</c:otherwise>
</c:choose>
</body>
</html>