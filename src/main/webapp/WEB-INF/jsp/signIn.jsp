<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
    </head>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
        body{
            background-color: white;
            font-family: Times New Roman, Times, serif;
        }
        .lform{

            padding-left: 400px;
            padding-right: 200px;
            padding-top: 50px;
            padding-top: 50px;

            color:black
        }

    </style>
    <body>

        <h1 style="background-color:#FFDD33" align="center">Vehicle Rental Management System
        </h1>
        <form:form modelAttribute="user" method="post">

            <div class="lform">

                <h3>Sign In</h3>
                <h3 style="color:red">${error}</h3>
                <label>Enter Email:</label><br>
                <form:input path="usrEmail" size="30" required="required"/>
                <br><br>
                <label>Enter Password:</label><br>
                <form:password path="usrPassword" size="30" required="required" />
                <br><br>
                <a href="register.htm">SignUp</a>
                <input type="submit" value="SignIn" />
                <br><br>
            </div>
        </form:form>
        <footer>
  <div class="footer">  
<h3>Reach Me Via:
        <a style="color: white" href="mailto:kosuri.sa@northeastern.edu">
         Email</a>
</h3>
<h4>Copyright &copy; 2022 All Rights Reserved &copy Sai supraja Kosuri</h4>
 </div>
    </body>
</html>
