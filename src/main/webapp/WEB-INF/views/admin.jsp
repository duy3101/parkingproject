<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Hello Admin</title>

    </head>

    <h3>Hello Admin!</h3>

    <body>
    
        <form action="admin_get_overdue_payments" method="post">
            <button type="submit">Check overdue payments</button>
        </form>

        <form action="admin_get_percentage_of_lot" method="post">
            <button type="submit">Check parking lot usage</button>
        </form>

        <form action="admin_get_percentage_of_type" method="post">
            <button type="submit">Check parking spot type usage</button>
        </form>

    </body>
</html>