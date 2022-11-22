<style><%@include file="/WEB-INF/css/layout.css"%></style>
<div style="background-color: teal;color:white;font-family: Helvetica, sans-serif;height:40px;line-height:2.5em">

    <a style="color:white;text-decoration:none" href="employee-dashboard.htm?usrEmail=${sessionScope.usrEmail}">Home</a> |
     
    <a style="color:white;text-decoration:none" href = "books.htm?usrEmail=${sessionScope.usrEmail}">Vehicles</a> |
    
    <a style="color:white;text-decoration:none" href = "book-reservations.htm?usrEmail=${sessionScope.usrEmail}">Reservations</a> 
    | 
    <a style="color:white;text-decoration:none" href = "book-returns.htm?usrEmail=${sessionScope.usrEmail}">Returns</a>
    
    <a style="color:white;text-decoration:none;float:right" href="logout.htm?usrEmail=${sessionScope.usrEmail}">SignOut</a>
    <label style="float:right">Hi ${sessionScope.usrEmail} |</label>
</div>