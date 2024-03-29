<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <meta charset="UTF-8">
        <title>Vehicles in use</title>
    </head>
    <body>
        <jsp:include page="cusNavbar.jsp"/>

        <c:choose>
            <c:when test="${vehicles.size() gt 0}"> 
                <table id="table">
                    <h2>Vehicles in use</h2>
                    <th>Image</th>
                    <th>license Plate</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Rent start Date</th>
                    <th>Rent End Date</th>
                    <th> Rent Return Date</th>
                        <c:forEach items="${vehicles}" var="vehicle">
                        <tr>
                            <td>
                                <img width="100" height="100"  src="/vehicle/images/${vehicle.imagePath}"/>
                            </td>
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
                <h2 align="center">You have no vehicles in use.</h2>
            </c:otherwise>
        </c:choose>
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