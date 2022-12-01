<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<title>add Vehicles</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>

<form:form modelAttribute="vehicle" method="post" enctype="multipart/form-data">

<table id="table">
<h2>Add Vehicles</h2>
<h4 style="color:red">${licencsePlateError}</h4>

	<tr>
	<td>
	LicensePlate(4-8): 
	</td>
	<td>
	<form:input path="licensePlate" required="required" minlength="4" maxlength="6"/>
	</td>
	</tr>
	<tr>
	<td>
	Model: 
	</td>
	<td>
	<form:input path="model" required="required"/>
	</td>
	</tr>
	<tr>
	<td>
	Year: 
	</td>
	<td>
	<form:input path="year" required="required"/>
	</td>
	</tr>
	<tr>
	<td>
    choose Image of vehicle:
    </td>
    <td>
    <input type="file" name="imgFile" accept="image/*" required="required"/>
    </td>
    </tr>
</table>	
<input type="submit" value="Add"/>
</form:form>
</body>
</html>