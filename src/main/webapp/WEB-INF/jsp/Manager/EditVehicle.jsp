<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <title>Edit Vehicle</title>
    </head>
    <body>
        <jsp:include page="ManagerNav.jsp"/>

        <form action="editvehicle.htm" method="POST">

            <input type="hidden" name="c1" value="${vehicle.getCarId()}"/>

            <table id="table">
                <h2>Update vehicle Information</h2>

                <tr>
                    <td>
                        license Plate:
                    </td>
                    <td>
                        ${vehicle.getLicensePlate()}
                    </td>
                </tr>

                <tr>
                    <td>
                        Model:
                    </td>
                    <td>
                        <input type="text" name="model" value="${vehicle.getModel()}" required="required"/>
                    </td>
                </tr>


                <tr>
                    <td>
                        Year:
                    </td>
                    <td>
                        <input type="text" name="year" value="${vehicle.getYear()}"required="required"/>
                    </td>
                </tr>

                <tr>
                    <td>Rent start date:
                    </td>
                    <td>
                        ${vehicle.getRentStartDate()}
                    </td>
                </tr>

                <tr>
                    <td>Rent End Date:
                    </td>
                    <td>${vehicle.getRentEndDate()}</td></tr>

                <tr>
                    <td>
                        Rent Return Date:
                    </td>
                    <td>
                        ${vehicle.getRentReturnDate()}
                    </td>
                </tr>

                <tr>
                    <td>
                        Booked User:
                    </td>
                    <td>
                        ${vehicle.getReservedByUser().getUsrEmail()}

                        <input type="checkbox" id="deleteRsvrtn" name="deleteRsvrtn" value="deleteRsvrtn"> Delete Booking
                    </td>
                </tr>


            </table>
            <br>
            <br>
            <input type="submit" value="Update"> 
        </form>
        <!-- Footer-->

        <footer>
            <div class="footer">  
                <h3>Reach Me Via:
                    <a style="color: white" href="mailto:kosuri.sa@northeastern.edu">
                        Email</a>
                </h3>
                <h4>Copyright &copy; 2022 All Rights Reserved &copy Sai supraja Kosuri</h4>
            </div>
        </footer>




    </body>
</html>