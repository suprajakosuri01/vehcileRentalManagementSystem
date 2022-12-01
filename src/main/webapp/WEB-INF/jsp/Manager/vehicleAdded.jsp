<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
<%@include file="/WEB-INF/css/style.css"%><
/style>
<title> vehicles Added Successfully</title>
</head>
<body>
<jsp:include page="ManagerNav.jsp"/>

<h1 align="center">Added Vehicle Success</h1>

<h3 align="center">
<a href="vehiclesadd.htm?usrEmail=${sessionScope.usrEmail}">Add another Vehicle
</a>
</h3>
</body>
</body>
</html>