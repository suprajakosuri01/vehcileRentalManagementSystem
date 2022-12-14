<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin DashBoard</title>
    </head>
    <body>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
 

.text{
 display:flex;
 align-items:center;
flex-direction: row;
}

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
   <h2>Admin Dashboard</h2>
     
        <div class="text">
        <img src="https://t4.ftcdn.net/jpg/00/65/77/27/360_F_65772719_A1UV5kLi5nCEWI0BNLLiFaBPEkUbv5Fv.jpg" alt="user icon">
      
           
        

        <h3>Admin can Fetch list Of Registered Users from * listOf Users tab* </h3>
        </div>
         
        
  <!-- Footer-->

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