<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>

<%  QueryManager manager = new QueryManager();
%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacant Spot by Type Page</title>
    </head>
    <body>
    
    <h3>Hi ${ID}</h3>
    <h4>${type_name}</h4>

    <table border = "1" width = "50%">
        <tr>
            <th>Spot Number</th>
            <th>Level</th>
            <th>Type</th>
        </tr>

        <c:forEach items="${type_list}" var="row">
            <tr>
            <td><c:out value = "${row.str1}"/></td>
            <td><c:out value = "${row.str2}"/></td>
            <td><c:out value = "${row.str3}"/></td>
            </tr>
        </c:forEach>
    </table>

    </body>
</html>