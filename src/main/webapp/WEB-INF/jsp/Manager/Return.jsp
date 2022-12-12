
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<title>Return vehicle</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>



<table id="table">
<h1>Return vehicle</h1>
	<tr>
	<td>
	license Plate: 
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
	Rent Start Date: 
	</td>
	<td>
	${vehicle.getRentStartDate()}
	</td>
	</tr>
	<tr>
	<td>
	Rent End Date:
	</td>
	<td>
	${vehicle.getRentEndDate()}
	</td>
	</tr>
	<tr>
	<td>
	Rent Return Date: 
	</td>
	<td>
	${vehicle.getRentReturnDate()}
	</td>
	</tr>
	<tr>
	<td>
	Booked By: 
	</td>
	<td>
	${vehicle.getxUser().getUsrEmail()}
	</td>
	</tr>
</table>
<form action="return.htm" method="POST">
<input type="hidden" name="returnDate" value="${vehicle.getRentEndDate()}"/>
	<input type="hidden" name="c5" value="${vehicle.getCarId()}"/>
	<input type="hidden" name="rentStartDate" value="${vehicle.getRentStartDate()}"/>
    <input type="hidden" name="licensePlate" value="${vehicle.getLicensePlate()}"/>
    	<input type="hidden" name="rentEndDate" value="${vehicle.getRentReturnDate()}"/>
	<input type="hidden" name="Model" value="${vehicle.getModel()}"/>
		<input type="hidden" name="usrEmail" value="${vehicle.getxUser().getUsrEmail()}"/>
	<input type="hidden" name="Year" value="${vehicle.getYear()}"/>
        <br>
        <br>
<input type="submit" value="Accept"> 
</form>
</body>
</html>