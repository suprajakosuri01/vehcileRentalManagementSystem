<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
             body{
            background-color: white;
            font-family: Times New Roman, Times, serif;
        }
        .lform{

            padding-left: 400px;
            padding-right: 200px;
            padding-top: 50px;
            padding-top: 50px;

        }
        </style>
        <title>add Vehicles</title>
    </head>
    <body>
        <jsp:include page="ManagerNav.jsp"/>

        <form:form modelAttribute="vehicle" method="post" enctype="multipart/form-data">

             <div class="lform">
                <h2>Add New Vehicle Information</h2>
                <h3 style="color:red">${licencsePlateError}</h3>

                <label>License Plate(4-6):  </label>
                <br>
              
                        <form:input path="licensePlate" required="required" minlength="4" maxlength="6"/>
                        <br>
                        <br>
                        
                        <label>Model:</label>  
                         <br>
                   
                        <form:input path="model" required="required" minlength="4" maxlength="20"/>
                          <br>
                          <br>
                    
                      <label>Year: </label>  
                   <br>
                        <form:input path="year" required="required" minlength="4" maxlength="4"/>
                        <br>
                        <br>
                        
                        <label>  Vehicle Picture:</label> 
                    <br>
                        <input type="file" name="imgFile" accept="image/*" required="required"/>
                        <br>
                        <br>
                        <input type="submit" value="Add"/> 
             </div>	
           
        </form:form>
    </body>
</html>