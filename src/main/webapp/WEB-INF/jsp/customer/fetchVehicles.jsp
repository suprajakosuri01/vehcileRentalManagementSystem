<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>fetch vehicles</title>
    </head>
    <body>
        <jsp:include page="cusNavbar.jsp"/>


        <c:if test="${usrVehicles.size() >=  2}">
            <h2 style="color:red">User can reserve only two cars</h2>
        </c:if>
        <c:choose>
            <c:when test="${vehicles.size() > 0}">


                <table id="table">
                    <h2>Book Vehicles</h2>

                    <th>Image</th>
                    <th>license Plate</th>
                    <th>model</th>
                    <th>year</th>
                    <th>Rent Return Date</th>
                    <th>Rent End Date</th>
                   <th>Action</th>


                    <c:forEach items="${vehicles}" var="vehicle">
                        <tr>
                            <td>
                                <img width="150" height="150"src="/vehicle/images/${vehicle.imagePath}"/>
                            </td>
                            <td>${vehicle.licensePlate}</td>
                            <td>${vehicle.model}</td>
                            <td>${vehicle.year}</td>
                            <td>${vehicle.rentReturnDate}</td>
                            <td>${vehicle.rentEndDate}</td>
                            <td>	
                                <c:choose>
                                    <c:when test= "${vehicle.rentEndDate != null || vehicle.rentReturnDate !=null}">

                                        Booked by User
                                    </c:when>

                                    <c:when test="${usrVehicles.size() >=  2}">
                                    </c:when>
                                    <c:otherwise>

                                        <a href="reservationconfirm.htm?carId=${vehicle.carId} & usrEmail=${sessionScope.usrEmail}">Book Vehicle</a>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h1 align="center">There are no vehicles available.</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>