<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title> Delete User</title>
    </head>
    <body>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>

            .Udform{

                padding-left: 300px;
                padding-right: 200px;
                padding-top: ‒50;
                padding-top: 50px;

                color:black
            }
        </style>
        <div style="background-color:#FFDD33"> 

            <ul>
                <li><a href="adminhome.htm?usrEmail=${sessionScope.usrEmail}">Home</a></li>
                <li><a href="listofusrs.htm?usrEmail=${sessionScope.usrEmail}">Listof Users</a></li>
                <li style="float:right">  <a  href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a></li>
                <label style="float:right;color:#ff0000;font-size:23px;">Welcome ${sessionScope.usrEmail} </label>
            </ul>

        </div>

        <form  action="userdelete.htm" method="POST">

            <input type="hidden" name="usrdel" value="${user.usrId}"/>

            <div class="Udform">

                <h2>Delete User Information</h2>


                <label><b>User Email:</b></label>
                <br>
                <td>${user.usrEmail}</td>
                <br>
                <br>
                <label><b>User Name:</b></label><br>
                <td>${user.name}</td>
                <br>
                <br>	
                <label><b>User Address:</b></label><br>
                <td>${user.userAddress}</td>
                <br>
                <br>
                <label><b>User Contact:</b></label>
                <br>
                <td>${user.userPhonenum}</td>
                <br>
                <br>
                <label><b>Title:</b><label>
                            <br>
                        <td>${user.title}</td>
                            <br>

                        <input type="hidden" name="usrEmail" value="${user.usrEmail}"/>
                        <input type="hidden" name="name" value="${user.name}"/>
                        <input type="hidden" name="usrPassword" value="${user.usrPassword}"/>
                        <input type="hidden" name="userAddress" value="${user.userAddress}"/>
                        <input type="hidden" name="userPhonenum" value="${user.userPhonenum}"/>
                        <input type="hidden" name="title" value="${user.title}"/>
                        <br>
                        <br>
                        <input type="submit" value="DeleteUser"> 
                        </div>
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