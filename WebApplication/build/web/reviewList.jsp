<%-- 
    Document   : reviewList
    Created on : 18.4.2016, 20:55:34
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="./style.css" rel="stylesheet" type="text/css">
        <title>ReviewList</title>
    </head>
    <body>
        <%@include file="head.jsp" %>
        <img src="restaurant.jpg" alt="restaurant" width="90%" height="300px"/>
         <h1><%= request.getParameter("name") %></h1> 
         <h3><%= request.getParameter("score") %></h3>
        <table>    
            <c:forEach items="${reviewList}" var="reviews">
                <tr>
                    <td><c:out value="${reviewList.user_id}"/> </td>
                    <td><c:out value="${reviewList.text}"/> 
                        <br/>
                        <c:out value="${reviewList.score}"/>
                    </td>  
                </tr>
            </c:forEach>           
        </table>
                    
        <h2>Add yours!</h2> 
            <form name="addReview" action="addReview">
            <input type="text" name="review" />
            <br/>
            Your score:
            <select name="score" value ="score" >
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
           </select>
            <br/>
            <input type="submit" value="OK" /> 
        </form>
                    
    </body>
</html>
