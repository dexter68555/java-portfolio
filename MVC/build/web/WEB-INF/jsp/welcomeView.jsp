<%-- 
    Document   : welcomeView
    Created on : Mar 22, 2020, 5:00:29 PM
    Author     : User
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome</h1><br>
        <h1>Name: ${fullname}</h1><br>
        <h1>Company: ${company}</h1><br>
        <h1>Email Address: ${email}</h1><br>
    </body>
</html>
