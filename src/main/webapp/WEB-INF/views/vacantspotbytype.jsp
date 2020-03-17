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
    
    <h3>Hi ${Name}</h3>
    <h3>Current log in plate: ${Plate}</h3>

    <h4>Current selected type: ${type_name}</h4>
    

    <button onclick="goBack()">Go Back</button>
    <script>
        function goBack() {
        window.history.back();
        }
    </script>
    
    <br> 
    <br>

    <label for="UserInputForm">Input your desired parking here</label>
    <form action="/user_input" method="post" name="UserInputForm">
        <label for="ParkingLotName">Parking Lot Name:</label>
        <input type="text" name="ParkingLotName"><br> 
        <label for="Level">Level:</label>
        <input type="text" name="Level"><br> 
        <label for="SpotNumber">SpotNumber:</label>
        <input type="text" name="SpotNumber"><br> 
        <input type="submit" name="submit"/>
    </form>


    <table border = "1" width = "50%">
        <tr>
            <th>Spot Number</th>
            <th>Level</th>
            <th>Type</th>
            <th>Parking Lot Name</th>
        </tr>

        <c:forEach items="${type_list}" var="row">
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