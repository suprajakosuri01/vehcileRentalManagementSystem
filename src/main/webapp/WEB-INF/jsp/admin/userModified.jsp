<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/WEB-INF/css/style.css"%>

        </style>
        <title>User Modified</title>
    </head>
    <body>
        <div style="background-color:#FFDD33"> 

            <ul>
                <li>
                    <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a>
                </li>
                <li>
                    <a href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Listof User</a>
                </li>
                <li style="float:right"> 
                    <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a>
                </li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} </label>
            </ul>

        </div>

        <h2 align="center">User Modified Success</h2>
    </body>
</html>