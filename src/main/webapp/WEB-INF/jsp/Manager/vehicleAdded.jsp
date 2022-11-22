<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/WEB-INF/css/layout.css"%></style>
<title>Add Success</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>

<h3 align="center">Vehicles added successfully</h3>
<br><br>
<p align="center"><a href="vehiclesadd.htm?usrEmail=${sessionScope.usrEmail}">Add another Vehicle</a></p></body>
</body>
</html>