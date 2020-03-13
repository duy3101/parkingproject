<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>

<%  QueryManager manager = new QueryManager();
%> 

<script type="text/javascript">
    function handleClick(evt) {
        var node = evt.target || evt.srcElement;
        if (node.name == 'edit') {
            node.value = "Modify";
        }
    }
</script>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacant Spot by Parking Lot Page</title>
    </head>
    <body>
    
    <h3>Hi ${Name}</h3>
    <h4>${lot_name}</h4>

     <table border = "1" width = "50%" onclick="handleClick(event);">
        <tr>
            <th>Spot Number</th>
            <th>Level</th>
        </tr>

        <c:forEach items="${spot_list}" var="row">
            <tr>
            <td><c:out value = "${row.str1}"/></td>
            <td><c:out value = "${row.str2}"/></td>
            <td>
               <form name="ParkCar" action="user" >
                <input type="submit" alue="Select">
               </form>
            </td>
            </tr>
        </c:forEach>
    </table>

    </body>
</html>