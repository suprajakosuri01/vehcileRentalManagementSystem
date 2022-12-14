<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
            .Udform{

                padding-left: 300px;
                padding-right: 200px;
                padding-top: ‒50;
                padding-top: 50px;

                color:black
            }
        </style>
        <title>Del Vehicle</title>
    </head>
    <body>
        <jsp:include page="ManagerNav.jsp"/>

   
          
           
             <div class="Udform">
               <h2>Delete Vehicle Information</h2>
                    <label><b> license Plate:</b> </label>
                    <br>
                <td>
                    ${vehicle.getLicensePlate()}
                </td>
               <br>  
                <br>
                   <label> <b>Model:</b> </label>
                      <br>
              
                <td>
                    ${vehicle.getModel()}
                </td>
                   <br>
                      <br>
           
                     <label>  <b>    Year: </b>  </label>
                        <br>
                   <td>
                    ${vehicle.getYear()}
                </td>
               <br>
                  <br>
               
                    <label> <b>  Rent start date:</b></label>
                <br>
                <td>
                    ${vehicle.getRentStartDate()}
                </td>
              <br>
                 <br>
               
                 <label>  <b>  Rent End Date:</b> </label>
               <br>
                <td>
                    ${vehicle.getRentEndDate()}
                </td>
               <br>
                  <br>
             
               <label><b>Rent Return Date: </b> </label>
                  <br>
                </td>
                <td>
                    ${vehicle.getRentReturnDate()}
                </td>
           
              <br>  
               <br>
                 <label> <b>    Booked By:</b> </label>
                    <br>
                <td>
                    ${vehicle.getReservedByUser().getUsrEmail()}
                </td>
              <br> 
                <br>
             
                 <label><b>Current User: </b></label>
              <br>
                <td>
                    ${vehicle.getxUser().getUsrEmail()}
                </td>
             
    
        <form action="deleteall.htm" method="POST">
            <input type="hidden" name="rentStartDate" value="${vehicle.getRentStartDate()}"/>
            <input type="hidden" name="c1" value="${vehicle.getCarId()}"/>
            <input type="hidden" name="rentEndDate" value="${vehicle.getRentEndDate()}"/>
            <input type="hidden" name="licensePlate" value="${vehicle.getLicensePlate()}"/>
            <input type="hidden" name="rentReturnDate" value="${vehicle.getRentReturnDate()}"/>
            <input type="hidden" name="Model" value="${vehicle.getModel()}"/>
            <input type="hidden" name="usrEmail" value="${vehicle.getReservedByUser().getUsrEmail()}"/>
            <input type="hidden" name="Year" value="${vehicle.getYear()}"/>
            <br>
            <br>
            <input type="submit" value="Delete"> 
            </div>
        </form>
        
    </body>
</html>