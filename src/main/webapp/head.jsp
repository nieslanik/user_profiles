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
        <script>
            document.onmousemove = checkLogin();
             
             setInterval(checkLogin(), 180 * 1000); // 180 * 1000 milsec
             
             function checkLogin(){
                     // posli update 
                 var time = new Date().getMinutes();
                 if(sessionStorage.getItem('time') === null){
                    oldTime = sessionStorage.setItem('time',  time);
                 }
                 else {
                     if(time - oldTime > 3){
                         var x = document.getElementById("logOutButton");
                         x.click();
                     }
                 }
              }
        </script>
    </head>
    <body>
        <img src="logo.svg" alt="logo">
        <div id="head"> 
            <h3>Login user: ${sessionScope.username}</h3> 
             <a href="index.html" id="logButton">Log out</a>
            <h3>Number of login users: ${sessionScope.logins} </h3>
            
        </div>
    </body>
</html>

