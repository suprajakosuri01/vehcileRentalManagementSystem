<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
        <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <meta charset="UTF-8">
        <title>fetch vehicles</title>
    </head>
    <body>
        <jsp:include page="cusNavbar.jsp"/>


        <c:if test="${usrVehicles.size() >=  2}">
 <h2 style="color:red">user can reserve only two cars</h2>
        </c:if>
        <c:choose>
            <c:when test="${vehicles.size() > 0}">
           <p>  ${usrVehicles.size()}</p>

                <table id="table">
                    <h3>Browse Cars</h3>
                   
                    <th></th>
                    <th>license Plate</th>
                    <th>model</th>
                    <th>year</th>
                    <th>Return Date</th>
                    <th>Reserved Until</th>
                    <th>Action</th>


                    <c:forEach items="${vehicles}" var="vehicle">
                        <tr>
                            <td><img width="150" height="150"src="/vehicle/images/${vehicle.imagePath}"/></td>
                            <td>${vehicle.licensePlate}</td>
                            <td>${vehicle.model}</td>
                            <td>${vehicle.year}</td>
                            <td>${vehicle.rentReturnDate}</td>
                            <td>${vehicle.rentEndDate}</td>
                            <td>	
                                <c:choose>
                                    <c:when test= "${vehicle.rentEndDate != null || vehicle.rentReturnDate !=null}">

                                        Reserved
                                    </c:when>
                                    
                                    <c:when test="${usrVehicles.size() >=  2}">
                                    </c:when>
                                    <c:otherwise>

                                        <a href="reservationconfirm.htm?carId=${vehicle.carId} & usrEmail=${sessionScope.usrEmail}">Reserve</a>

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