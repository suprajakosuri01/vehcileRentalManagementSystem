<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reserved vehicles</title>
</head>
<body>
<jsp:include page="cusNavbar.jsp"/>

<c:choose>
<c:when test="${vehicles.size() gt 0}">
<table id="table">
<h2>MY Bookings</h2>
	<th>Image</th>
	<th>license Plate</th>
	<th>model</th>
	<th>Year</th>
	<th>Rent start Date</th>
	<th>Rent End Date</th>
	<th> Rent Return Date</th>		
	
	<c:forEach items="${vehicles}" var="vehicle">
	<tr>
		<td><img width="150" height="150" src="/vehicle/images/${vehicle.imagePath}"/></td>
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
	<h1 align="center">No Bookings available at this moment</h1>
	</c:otherwise>
</c:choose>
 <!-- Footer-->

<footer>
  <div class="footer">  
<h3>Reach Me Via:
        <a style="color: white" href="mailto:kosuri.sa@northeastern.edu">
         Email</a>
</h3>
<h4>Copyright &copy; 2022 All Rights Reserved &copy Sai supraja Kosuri</h4>
 </div>
</body>
</html>