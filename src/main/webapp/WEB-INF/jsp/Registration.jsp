
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
            .rform{

                padding-left: 400px;
                padding-right: 200px;
                padding-top: 50px;
                padding-top: 50px;

                color:black
            }
            body{
                background-color:white;
            }
        </style>
        <h1 style="background-color:#FFDD33" align="center">Vehicle Rental Management System</h1>

        <form:form modelAttribute="user" method="post">

            <div class="rform">
                <h3>User Registration</h3>
                <h3 style="color:red">${error}</h3>
                <br>
                <label>User Email:</label>
                <br>
                <form:input type="email" path="usrEmail" size="30" required="required" placeholder="Enter email id" /> 
                <br>  <br>  
                <label>User Password(6-8 chars):</label><br>
                <form:input type="password" path="usrPassword" size="30" minlength="6" maxlength="8" required="required" placeholder="password" />   
                <br> <br>  
                <label>User Name:</label><br>
                <form:input path="name" size="30" required="required" placeholder="User Name"/>    
                <br> <br>  
                <label>Home Address:</label><br>
                <form:input path="userAddress"  size="30" required="required" placeholder="Enter Home Address" />
                <br>  <br>  

                <label>Phone number(10-Digits)</label><br>
                <form:input path="userPhonenum" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" size="30"  maxlength="10" required="required" placeholder="Enter mobile number"/> 
                <br> <br>   
                <label>Title:</label><br>

                <form:radiobutton path="title" value="admin" required="required"/>Admin
                <br>
                <form:radiobutton path="title" value="employee" required="required" />Manager
                <br>

                <form:radiobutton path="title" value="customer" required="required"/>Customer
                <br><br>  
                <input align="center" type="submit" value="Sign Up" /> 

            </div>

        </form:form>
    </body>
</html>