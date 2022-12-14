
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Users</title>

        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
    </head>
    <body>
        <div style="background-color:#FFDD33"> 

            <ul>
                <li>
                    <a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a>
                </li>
                <li>
                    <a href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Registered Users</a>
                </li>
                <li style="float:right">  <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">SignOut</a></li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} </label>
            </ul>

        </div>
        <table id="table" border="1">
            <h2>List of Registered Users</h2>
            <br>
            <th>User ID</th>
            <th>User Email</th>
            <th>User Name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Title</th>
            <th>Action</th>

            <c:forEach items="${user}" var="user">
                <c:if test="${user.title ne 'admin'}">
                    <tr>
                        <td>${user.usrId}</td>
                        <td>${user.usrEmail}</td>
                        <td>${user.name}</td>
                        <td>${user.userAddress}</td>
                        <td>${user.userPhonenum}</td>
                        <td>${user.title}</td>
                        <td>
                            <a style="color: #00b33c"href="usermodify.htm?usrId=${user.usrId}">Update</a>|
                            <a href="userdelete.htm?usrId=${user.usrId}">Delete</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>

        </table>

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