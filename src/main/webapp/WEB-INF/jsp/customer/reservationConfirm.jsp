<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<meta charset="UTF-8">
<title> confirm rsvn</title>
</head>
<body>
<jsp:include page="cusNavbar.jsp"/>


<table id="table">
<h2> Booking Confirm</h2>
	<tr>
	<td>
	license Plate: 
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
	<td>Rent End date: 
	</td>
	<td>
	${rentEndDate}
	</td>
	</tr>
	<tr>
	<td>Rent Return Date: 
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
        <br>
        <br>
<input type="submit" value="BookVehicle"> 
</form>
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