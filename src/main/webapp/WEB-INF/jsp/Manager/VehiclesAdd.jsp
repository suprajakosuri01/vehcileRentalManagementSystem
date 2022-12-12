<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <title>Add Vehicle</title>
    </head>
    <body>
        <jsp:include page="ManagerNav.jsp"/>

        <form modelAttribute="vehicle" method="post" enctype="multipart/form-data">
            <div>
                <h2>Add Vehicles</h2>
                <h3 style="color:red">${licencsePlateError}</h3>
                <label> License Plate(4-8): </label>
                <br>
                <input path="licensePlate" required="required" minlength="4" maxlength="6"/>
                <br>
                <br>	
                <label> Model: </label>
                <br>
                <input path="model" required="required"/>
                <br>
                <br>
                <label>Year: </label>
                <br>
                <input path="year" required="required"/>
                <br><!-- comment -->
                <br>
                <label>Vehicle Picture: </label>
                <br>
                <input type="file" name="imgFile" accept="image/*" required="required"/>  
            </div>
            <br>
            <br>
            <input type="submit" value="Add"/>
        </form>
    </body>
</html>