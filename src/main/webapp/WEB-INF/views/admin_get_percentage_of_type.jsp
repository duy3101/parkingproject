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
    
        <label for="PercentageTypeTable"><strong>Percentage of Open Parking Spot Type</strong></label>
        <table border = "1" width = "50%" name = "PercentageTypeTable">
            <tr>
                <th>Parking Spot Type</th>
                <th>Percentage</th>
            </tr>

            <c:forEach items="${return_list}" var="row">
                <tr>
                <td><c:out value = "${row.str1}"/></td>
                <td><c:out value = "${row.str2}"/></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>