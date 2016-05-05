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
         <link href="style.css" rel="stylesheet" type="text/css">
        <title>Outline</title>
    </head>
    <body>
        <%@include file="head.jsp"%>
        <h1>TOP10 Restaurants</h1>
        <table>    
            <c:forEach items="${restList}" var="restaurant">
                <tr>
                    <td><img src="restaurant.jpg" alt="restaurant" width="400px" height="200px"/></td>
                
                    <td width="60%"><c:out value="${restaurant.name}"/> - <c:out value="${restaurant.score}"/>
                        <br/>
                        <c:out value="${restaurant.description}"/>
                        <br/>
                         <a href="reviewList.jsp?name=${restaurant.name}&?score=${restaurant.score}" id="restButton">See Reviews!</a> 
                    </td>  
                </tr>
            </c:forEach>           
        </table>
    </body>
</html>
