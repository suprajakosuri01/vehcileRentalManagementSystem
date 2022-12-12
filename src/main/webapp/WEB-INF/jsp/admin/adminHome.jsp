<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin DashBoard</title>
    </head>
    <body>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
        <div style="background-color:#FFDD33"> 
            <ul>
                <li>
                    <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a>
                </li>
                <li>
                    <a href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Listof Users</a>
                </li>
                <li style="float:right"> 
                    <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a>
                </li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} </label>
            </ul>

        </div>

        <h2 align="center">Admin Dashboard</h2>

        <h3 align="center">Admin can Fetch list Of Users from *Get listOf Users tab* </h3>
    </body>
</html>