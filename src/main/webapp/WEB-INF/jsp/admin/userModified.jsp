<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>

        </style>
        <title>Edit Success</title>
    </head>
    <body>
        <div style="background-color:#FFDD33"> 

            <ul>
                <li><a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a></li>
                <li><a href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Get listof User</a></li>
                <li style="float:right">  <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a></li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} </label>
            </ul>

        </div>

        <h3 align="center">User Modified Success</h3>
    </body>
</html>