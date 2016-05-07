<%-- 
    Document   : index
    Created on : 7.5.2016, 15:50:01
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="style.css" rel="stylesheet" type="text/css">
        <title>LoginPage</title>
    </head>
    <body>
        <img src="logo.svg" alt="logo" >  
        <div id="login">
            <h1>Login</h1>
           <form name="loginForm" action="loginCheck.jsp" method="POST">
               Username:
               <input type="text" name="username" />
               <br/>
               Password:
               <input type="password" name="password" />
               <br/>
               <input type="submit" value="OK" /> 
           </form>           
        </div>
        <%
            if(null!=request.getAttribute("errorMessage"))
            {
                out.println(request.getAttribute("errorMessage"));
            }
        %>
        
        <div id="nologin">
            <h2>You don't have an account?</h2>
             <a href="registration.jsp">Register</a>

            <h2>Or see reviews without login!</h2>
             <a href="outline.jsp">Enter</a>
        </div>
    </body>
    
</html>
