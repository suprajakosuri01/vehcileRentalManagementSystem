<style>
<%@include file="/WEB-INF/css/style.css"%>
a{
font-size:23px;
color: #ff0000;
text-decoration:none;
}
</style>
<div style="background-color: #FFDD33"> 
    <a href="managerhome.htm?usrEmail=${sessionScope.usrEmail}">Home
    </a> |   
    <a href = "vehicles.htm?usrEmail=${sessionScope.usrEmail}">Vehicles
    </a> |
       <a href = "vehiclereserve.htm?usrEmail=${sessionScope.usrEmail}">Customer Reservations
       </a> |
      <a  href = "returnvehicle.htm?usrEmail=${sessionScope.usrEmail}">Customer Returns
      </a>   
    <a style="float:right;" href="signout.htm?usrEmail=${sessionScope.usrEmail}">SignOut
    </a>
    <label style="float:right;font-size:23px;">
    Welcome ${sessionScope.usrEmail} |
    </label>
</div>