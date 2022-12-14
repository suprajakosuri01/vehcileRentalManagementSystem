<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>


            .Umform{

                padding-left: 400px;
                padding-right: 200px;
                padding-top: ‒50;
                padding-top: 50px;

                color:black
            }
        </style>
        <meta charset="UTF-8">
        <title>User Modify Page</title>
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

        <form action="usermodify.htm"" method="POST">

            <input type="hidden" name="uid2" value="${user.getUsrId()}"/>
            <div class="Umform">

                <h2>Update User Information </h2>

                <label>User Email:</label>

                <br>
                <td>${user.getUsrEmail()}</td>

                <br>
                <br>
                <label> User Name:</label>
                <br>
                <td>${user.getName()}</td>

                <br>
                <br>

                <label>User Address:</label><br>
                <input type="text" name="userAddress" size="40"value="${user.getUserAddress()}" 
                       required="required"/>
                <br>
                <br>

                <label>Mobile Number(10):</label><br>
                <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" maxlength="10"
                       name="userPhonenum" value="${user.getUserPhonenum()}" 
                       required="required"/>

                <br>
                <br>

                <label>Title:</label><br>
                <td>${user.getTitle()}</td>

                <br>
                <br>
               <input  type="submit" value="UpdateUser">
        </form>
                
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