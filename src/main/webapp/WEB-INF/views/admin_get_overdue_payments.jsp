<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Hello Admin</title>

    </head>
    <body>

        <button onclick="goBack()">Go Back</button>
        <script>
            function goBack() {
            window.history.back();
            }
        </script>

        <br>
        <br>

        <label for="OverduePaymentsTable"><strong>Overdue Payments Table</strong></label>
        <table border = "1" width = "50%" name = "OverduePaymentsTable">
            <tr>
                <th>License Plate</th>
                <th>Duration</th>
                <th>StartTime</th>
                <th>Status</th>
            </tr>

            <c:forEach items="${return_list}" var="row">
                <tr>
                <td><c:out value = "${row.str1}"/></td>
                <td><c:out value = "${row.str2}"/></td>
                <td><c:out value = "${row.str3}"/></td>
                <td><c:out value = "${row.str4}"/></td>
                </tr>
            </c:forEach>
        </table>
    
    </body>
</html>