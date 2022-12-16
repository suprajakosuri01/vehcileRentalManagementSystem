<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
    </head>
    <body>

        <div style="background-color: #FFDD33"> 
            <ul>
                <li>
                    <a href="managerhome.htm?usrEmail=${sessionScope.usrEmail}">Home
                    </a>  
                </li>
                <li>
                    <a href = "vehicles.htm?usrEmail=${sessionScope.usrEmail}">List Of Vehicles
                    </a> 
                </li>
                <li>
                    <a href = "vehiclereserve.htm?usrEmail=${sessionScope.usrEmail}">Customer Vehicle Bookings
                    </a> 
                </li>
                <li>
                    <a  href = "returnvehicle.htm?usrEmail=${sessionScope.usrEmail}">Customer Vehicle Returns
                    </a> 
                </li>
                <li style="float:right">
                    <a href="signout.htm?usrEmail=${sessionScope.usrEmail}">SignOut
                    </a>
                </li>

                <label style="float:right;font-size:23px;color: #ff0000;">
                    Welcome ${sessionScope.usrEmail} |
                </label>
            </ul>
        </div>

    </body>
</html>
