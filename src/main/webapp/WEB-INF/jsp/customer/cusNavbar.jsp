<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
    </head>
    <body>
        <div style="background-color:#FFDD33"> 

            <ul>
                <li>
                    <a  href="cusHome.htm?usrEmail=${sessionScope.usrEmail}">Home
                    </a>
                </li>
                <li>
                    <a href="fetchVehicles.htm?usrEmail=${sessionScope.usrEmail}">Browse Vehicles
                    </a>
                    </li>
                     <li>
                    <a  href="orders.htm?usrEmail=${sessionScope.usrEmail}">Vehicles In Use
    </a> 
    </li>
    
    <li>
    <a  href="bookedVehicles.htm?usrEmail=${sessionScope.usrEmail}">My Reservations
    </a>
                </li>
                
                <li style="float:right">
                    <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a>
                </li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} 
                </label>
            </ul>

        </div>

    </body>
</html>
