<%-- 
    Document   : head
    Created on : 18.4.2016, 22:04:38
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./style.css" rel="stylesheet" type="text/css">
        <title>Head</title>
    </head>
    <body>
        <img src="logo.svg" alt="logo" >
        <div id="head"> 
            <h3>Login user: <%= request.getParameter("username") %></h3> 
            <input type="buttonn" value="Log out" name="noLogin" />
            <h3>Number of login users: </h3>
            
        </div>
    </body>
</html>

