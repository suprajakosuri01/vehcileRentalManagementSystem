
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<title>Edit Vehicle</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>

<form style="text-align:left" action="editvehicle.htm" method="POST">

<input type="hidden" name="c1" value="${vehicle.getCarId()}"/>

<table id="table">
<h2>Edit vehicle Details</h2>

	<tr>
	<td>
	licensePlate:
	</td>
	<td>
	${vehicle.getLicensePlate()}
	</td>
	</tr>
	
	<tr>
	<td>Model:
	</td>
	<td>
	<input type="text" name="model" value="${vehicle.getModel()}" required="required"/></td></tr>
	
	
	<tr>
	<td>
	Year:
	</td>
	<td>
	<input type="text" name="year" value="${vehicle.getYear()}"required="required"/>
	</td>
	</tr>
	
	<tr>
	<td>Rent start date:
	</td>
	<td>
	${vehicle.getRentStartDate()}
	</td>
	</tr>
	
	<tr>
	<td>RentEndDate:
	</td>
	<td>${vehicle.getRentEndDate()}</td></tr>
	
	<tr>
	<td>
	Return By:
	</td>
	<td>
	${vehicle.getRentReturnDate()}
	</td>
	</tr>
	
	<tr>
	<td>Reserved By:
	</td>
	<td>
	${vehicle.getReservedByUser().getUsrEmail()}
	</td>
	
	
	<td>
	<input type="checkbox" id="deleteRsvrtn" name="deleteRsvrtn" value="deleteRsvrtn"> Delete Reservation
	</td>
	</tr>
	
	<tr>
	<td>Current User:
	</td>
	<td>
	${vehicle.getxUser().getUsrEmail()}
	</td>
	
	<td>
	<input type="checkbox" id="deletecrntUser" name="deleteUsr" value="deleteUsr"> Remove Current User
	</td>
	</tr>
	
	<tr>
	<td>
	
	<input type="submit" value="Edit Vehicle"> 
	</td>
	</tr>
</table>
</form>
 



</body>
</html>