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
    
    <h3>Hi ${ID}</h3>
        <table border = "1" width = "50%">
            <tr>
                <th>Fruit name</th>
                <th>Quanity</th>
            </tr>

            <c:forEach items="${fruitList}" var="fruit">
                <tr>
                <td><c:out value = "${fruit.str2}"/></td>
                <td><c:out value = "${fruit.str3}"/></td>
                </tr>
            </c:forEach>
        </table>

        <form name="f1" method="post">
        <select name="clr">
           <option>Red</option>
           <option>Blue</option>   
           <option>Green</option>
           <option>Pink</option>
        </select>
        <input type="submit" name="submit" value="Select Color"/>
        </form>
        <%-- To display selected value from dropdown list. --%>
        <% 
            String s = request.getParameter("clr");
            if (s !=null )
            {
                System.out.println("Selected Color is : "+s);
            }
        %>

    </body>
</html>