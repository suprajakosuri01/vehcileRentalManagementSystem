<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>
<title>Sign In</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style>
        body{
            background-color: white;
            font-family: Times New Roman, Times, serif;
        }
         .lform{

    padding-left: 400px;
    padding-right: 200px;
    padding-top: 50px;
    padding-top: 50px;

color:black
}
        
    </style>
<body>
    
<div style="background-color:#FFDD33">   

<h2 align="center">Vehicle Rental Management System
<img src="https://cdn-icons-png.flaticon.com/512/632/632682.png" alt="logo" width="28" height="28">
</h2>
</div>



<form:form modelAttribute="user" method="post">
    

<div class="lform">

<h3>Sign In</h3>
<p style="color:red">${error}</p>
    <label>Enter Email:</label><br>
    <form:input path="usrEmail" size="30" required="required"/>
    <br><br>
    <label>Enter Password:</label><br>
    <form:password path="usrPassword" size="30" required="required" />
     <br><br>
	<a href="register.htm">New user?Sign Up</a>
	<p><input type="submit" value="Sign In" /></p>
	 <br><br>
</div>
</form:form>
</body>
</html>
