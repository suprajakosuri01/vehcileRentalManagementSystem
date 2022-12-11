<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<meta charset="UTF-8">
<title> My Reservations</title>
</head>
<body>
<jsp:include page="cusNavbar.jsp"/>


<table id="table">
<h2> Reservation Confirm</h2>
	<tr>
	<td>
	licensePlate: 
	</td>
	<td>
	${vehicle.licensePlate}
	</td>
	</tr>
	<tr>
	<td>Model: 
	</td>
	<td>
	${vehicle.model}
	</td>
	</tr>
	<tr>
	<td>Year:
	 </td>
	<td>
	${vehicle.year}</td>
	</tr>
	<tr>
	<td>Reserved Until: 
	</td>
	<td>
	${rentEndDate}
	</td>
	</tr>
	<tr>
	<td>Return Date: 
	</td>
	<td>${rentReturnDate}</td>
	</tr>
</table>

<form action="reservationconfirm.htm" method="POST">
	
	<input type="hidden" name="year" value="${vehicle.getYear()}"/>
	<input type="hidden" name="rentStartDate" value="${rentStartDate}"/>
	<input type="hidden" name="imagePath" value="${vehicle.getImagePath()}"/>
	<input type="hidden" name="c1" value="${vehicle.getCarId()}"/>
	<input type="hidden" name="rentReturnDate" value="${rentReturnDate}"/>
    <input type="hidden" name="licensePlate" value="${vehicle.getLicensePlate()}"/>
    <input type="hidden" name="rentEndDate" value="${rentEndDate}"/>
	<input type="hidden" name="model" value="${vehicle.getModel()}"/>
	<input type="hidden" name="usrEmail" value="${usrEmail}"/>
<p><input type="submit" value="Confirm"></p>  
</form>

</body>
</html>