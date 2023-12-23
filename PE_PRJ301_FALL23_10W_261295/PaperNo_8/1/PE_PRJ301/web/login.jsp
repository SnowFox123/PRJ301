<%-- 
    Document   : login
    Created on : 113
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h3>${requestScope.msg}</h3>
        <form action="MainController" method="post">
            UserID: <input type="text" name="txtUserID" value="" /></br>
            Password: <input type="password" name="txtPassword" value="" /></br>
            <input type="submit" name="action" value="LOGIN" /></br>
        </form>
    </body>
    
</html>

