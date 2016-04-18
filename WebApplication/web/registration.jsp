<%-- 
    Document   : registration
    Created on : 18.4.2016, 20:29:14
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="./style.css" rel="stylesheet" type="text/css">
        <title>Registration</title>
    </head>
    <body>
        <%@include file="head.jsp" %>
         <h1>Registration</h1>

         <div id="registration">
            <form name="Name Input Form" action="outline.jsp" method="POST">
                Username:
                <input type="text" name="username" />
                <br/>
                Password:
                <input type="password" name="password" />
                <br/>
                Employee account:
                <input type="checkbox" name="employee" value="employee">
                <br/>
                <input type="submit" value="Register" /> 
            </form>  
          </div>
         
    </body>
</html>
