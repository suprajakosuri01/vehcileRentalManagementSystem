<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<title>Vehicle Return</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>
<c:choose>
<c:when test="${vehicles.size() gt 0}">
<table id="table">
	<h2>Vehicle Returns</h2>
	<th>Image</th>
	<th>license Plate</th>
	<th>Model</th>
	<th>Year</th>
	<th>Rent Start Date</th>
	<th>Rent End Date</th>
	<th>Rent Return Date</th>
	<th>In use By</th>
	<th>Action</th>
	
	<c:forEach items="${vehicles}" var="vehicle">
	<tr>
		<td>
		<img width="150" height="150" src="/vehicle/images/${vehicle.key.imagePath}"/>
		</td>
		
		<td>
		${vehicle.key.licensePlate}
		</td>
		<td>
		${vehicle.key.model}
		</td>
		<td>
		${vehicle.key.year}
		</td>
		<td>
		${vehicle.key.rentStartDate}
		</td>
		<td>
		${vehicle.key.rentEndDate}
		</td>
		<td>
		${vehicle.key.rentReturnDate}
		</td>
		<td>
		${vehicle.value}
		</td>
		
		<td>	
 		<a style="color:black"  href="return.htm?carId=${vehicle.key.carId}&usrEmail=${vehicle.value}">Vehicle Returned
 		</a>
		</td>
	</tr>
	</c:forEach>

</table>
</c:when>
<c:otherwise>
<h2 align="center"> No Vehicles in use as of now!.</h2>
</c:otherwise>
</c:choose>
</body>
</html>