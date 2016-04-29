<%-- 
    Document   : loginCheck
    Created on : 29.4.2016, 9:16:59
    Author     : akaren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% 
       String username=request.getParameter("username"); 
       String password=request.getParameter("password");
      
       //TODO check login
       
       session.setAttribute("username",username); 
       response.sendRedirect("outline.jsp"); 
        
       %>


    </body>
</html>
