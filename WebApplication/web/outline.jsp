<%-- 
    Document   : outline
    Created on : 18.4.2016, 20:34:29
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="./style.css" rel="stylesheet" type="text/css">
        <title>Outline</title>
    </head>
    <body>
        <%@include file="head.jsp" %>
        <h1>TOP10 Restaurants</h1>
        <table>    
            <c:forEach items="${restList}" var="restaurant">
                <tr>
                    <td>Obrazek</td>
                    <td><c:out value="${restaurant.name}"/> - <c:out value="${restaurant.score}"/>
                        <br/>
                        <c:out value="${restaurant.description}"/>
                        <br/>
                         <input type="button" value="See Reviews!" name="reviews" />
                    </td>  
                </tr>
            </c:forEach>           
        </table>
    </body>
</html>
