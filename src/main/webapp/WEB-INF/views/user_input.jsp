<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>





<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Refresh" content="4; url=http://localhost:8080/" />

        <title>User Submission</title>
    </head>
    <body>
    

    <h3>Hi ${Name}</h3>
    <h3>Current log in plate: ${Plate}</h3>
    <h3>Status: ${Status}</h3>

    <p>Please follow <a href="http://localhost:8080/">this link to go back to login page</a>.</p>


    </body>
</html>