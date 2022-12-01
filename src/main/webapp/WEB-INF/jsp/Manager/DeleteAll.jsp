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
<title>Delete Vehicle</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>

<table id="table">
<h2>Delete Vehicle</h2>
	<tr>
	<td>
	licensePlate: 
	</td>
	<td>
	${vehicle.getLicensePlate()}
	</td>
	</tr>
	<tr>
	<td>
	Model:
	 </td>
	<td>
	${vehicle.getModel()}
	</td>
	</tr>
	<tr>
	<td>
	Year: 
	</td>
	<td>
	${vehicle.getYear()}
	</td>
	</tr>
	<tr>
	<td>
	Rent start date:
	 </td>
	<td>
	${vehicle.getRentStartDate()}
	</td>
	</tr>
	<tr>
	<td>
	RentEndDate: 
	</td>
	<td>
	${vehicle.getRentEndDate()}
	</td>
	</tr>
	<tr>
	<td>
	Return Date: 
	</td>
	<td>
	${vehicle.getRentReturnDate()}
	</td>
	</tr>
	<tr>
	<td>
	Reserved By: 
	</td>
	<td>
	${vehicle.getReservedByUser().getUsrEmail()}
	</td>
	</tr>
	<tr>
	<td>
	Current User: 
	</td>
	<td>
	${vehicle.getxUser().getUsrEmail()}
	</td>
	</tr>
</table>
<form action="deleteall.htm" method="POST">
	<input type="hidden" name="rentStartDate" value="${vehicle.getRentStartDate()}"/>
	<input type="hidden" name="c1" value="${vehicle.getCarId()}"/>
	<input type="hidden" name="rentEndDate" value="${vehicle.getRentEndDate()}"/>
    <input type="hidden" name="licensePlate" value="${vehicle.getLicensePlate()}"/>
    	
	<input type="hidden" name="rentReturnDate" value="${vehicle.getRentReturnDate()}"/>
	<input type="hidden" name="Model" value="${vehicle.getModel()}"/>
		<input type="hidden" name="usrEmail" value="${vehicle.getReservedByUser().getUsrEmail()}"/>
	<input type="hidden" name="Year" value="${vehicle.getYear()}"/>
	<br>
<input type="submit" value="delete"> 
</form>
</body>
</html>