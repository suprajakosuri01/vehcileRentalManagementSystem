<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">   
    <a style="color:white;text-decoration:none" href="customer-dashboard.htm?usrEmail=${sessionScope.usrEmail}">Home</a> |
   	<a style="color:white;text-decoration:none" href="browse-books.htm?usrEmail=${sessionScope.usrEmail}">Browse</a> | 
    <a style="color:white;text-decoration:none" href="my-books.htm?usrEmail=${sessionScope.usrEmail}">My Books</a> | 
    <a style="color:white;text-decoration:none" href="my-reservations.htm?usrEmail=${sessionScope.usrEmail}">My Reservations</a>
     <a style="color:white;text-decoration:none;float:right" href="logout.htm?usrEmail=${sessionScope.usrEmail}">Logout</a> 
     <label style="float:right">Hi ${usrEmail.username} |</label>
     
     
     
     
</div>