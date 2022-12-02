<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Registartion Page</title>
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
 <div style="background-color:#FFDD33"> 
<h2 align="center">Vehicle Rental Management System</h2>
</div>


<form:form modelAttribute="user" method="post">

<div class="rform">
<h3>User Registartion</h3>
<h3 style="color:red">${error}</h3>
<br>
 <label>User Email:</label><br>
  <form:input type="email" path="usrEmail" size="30" required="required" placeholder="Enter email id" /> 
    <font color="red"><form:errors path="usrEmail"/></font></td>    
<br>  <br>  

   <label>User Password:</label><br>
  <form:input type="password" path="usrPassword" size="30" minlength="6" maxlength="8" required="required" placeholder="6 to 8 chars" /> 
  
  
     
<br> <br>  

<label>User Name:</label><br>
<form:input path="name" size="30" required="required"/> 
  
     
<br> <br>  

 
     <label>Home Address:</label><br>
   <form:input path="userAddress"  size="30" required="required" />
   

<br>  <br>  

 <label>Phone number</label><br>
  <form:input path="userPhonenum" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" size="30"  maxlength="10" required="required" placeholder="10 digits number"/> 
    

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