<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page import="uwb.parkingproject.service.*"%>

<%  QueryManager manager = new QueryManager();
    System.out.println(manager.test());
%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home Page</title>
    </head>
    <body>
    
    <h3>Hi ${Name}</h3>
        <label for="ParkedCarTable">Current Parked Car Table</label>
        <table border = "1" width = "50%" name = "ParkedCarTable">
            <tr>
                <th>Start Time</th>
                <th>Duration</th>
                <th>Status</th>
            </tr>

            <c:forEach items="${parked_list}" var="row">
                <tr>
                <td><c:out value = "${row.str1}"/></td>
                <td><c:out value = "${row.str2}"/></td>
                <td><c:out value = "${row.str3}"/></td>
                </tr>
            </c:forEach>
        </table>

        <form action="leave_spot" method="post">
         <button type="submit">Click here to leave spot</button>
        </form>

        <br>
        <br>

        <form action="vacantspot" method="post">
        <label for="ParkingLotName">Find available spot from garage:</label>
        <select name="ParkingLotName", id="ParkingLotName">
            <option value="North">North</option>
            <option value="South">South</option>
            <option value="Discovery">Discovery</option>
        </select>
        <input type="submit" name="submit" value="Select Garage"/>
        </form>

        <form action="vacantspotbytype" method="post">
        <label for="ParkingSpotType">Find available spot from type:</label>
        <select name="ParkingSpotType", id="ParkingSpotType">
            <option value="Normal">Normal</option>
            <option value="Carpool">Carpool</option>
            <option value="Handicapped">Handicapped</option>
        </select>
        <input type="submit" name="submit" value="Select Type"/>
        </form>
        
    </body>
</html>