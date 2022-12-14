<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <title>Add/View Vehicle</title>
    </head>
    <body>
        <jsp:include page="ManagerNav.jsp"/>
        <c:choose>
            <c:when test="${vehicle.size() >= 0}">
                <table id="table">

                    <h2>Vehicles Information</h2>

                    <h2><a style="color: #00b33c" href="vehiclesadd.htm">Add Vehicles</a></h2>
                    <br>
                    <br>

                    <th>Image</th>
                    <th>license Plate</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Rent Start Date</th>
                    <th>Rent End Date</th>
                    <th>Rent Return Date</th>
                    <th>Booked By</th>
                    <th>In Use By</th>
                    <th>Action</th>
                
                    <c:forEach items="${vehicle}" var="v1">
                        <tr>
                            <td>
                                <img width="150" height="150" src="/vehicle/images/${v1.imagePath}"/>
                            </td>
                            <td>
                                ${v1.licensePlate}
                            </td>
                            <td>
                                ${v1.model}
                            </td>
                            <td>
                                ${v1.year}
                            </td>
                            <td>
                                ${v1.rentStartDate}
                            </td>
                            <td>
                                ${v1.rentEndDate}
                            </td>
                            <td>
                                ${v1.rentReturnDate}
                            </td>
                            <td>
                                ${v1.getReservedByUser().getUsrEmail()}
                            </td>
                            <td>
                                ${v1.getxUser().getUsrEmail()}
                            </td>


                            <td>
                                <a style="color:black" href="editvehicle.htm?carId=${v1.carId}">Update|
                                </a> 

                                <a style="color:black"  href="deleteall.htm?carId=${v1.carId}">
                                    Delete</a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2 align="center">There are no Vehicles added as of now.
                </h2>
            </c:otherwise>
        </c:choose>
    </body>
</html>