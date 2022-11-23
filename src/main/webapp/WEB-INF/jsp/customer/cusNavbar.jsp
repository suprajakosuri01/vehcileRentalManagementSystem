<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">   
    <a style="color:white;text-decoration:none" href="cusHome.htm?usrEmail=${sessionScope.usrEmail}">Home</a> |
   	<a style="color:white;text-decoration:none" href="fetchVehicles.htm?usrEmail=${sessionScope.usrEmail}">Browse vehicles</a> | 
    <a style="color:white;text-decoration:none" href="orders.htm?usrEmail=${sessionScope.usrEmail}">orders</a> | 
    <a style="color:white;text-decoration:none" href="bookedVehicles.htm?usrEmail=${sessionScope.usrEmail}">My Bookings</a>
     <a style="color:white;text-decoration:none;float:right" href="signout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right">Hi ${sessionScope.usrEmail} |</label>
     
     
     
     
</div>