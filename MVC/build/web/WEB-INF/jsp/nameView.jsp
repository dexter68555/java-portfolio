<%-- 
    Document   : nameView
    Created on : Mar 22, 2020, 5:05:35 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Name View</title>
    </head>
    <body>
        <h1>Testing Name View</h1>
        
        <spring:nestedPath path="name">
            <form action="" method="post">
                Name:
                <spring:bind path="value">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind><br>
                Company:
                <spring:bind path="company">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind><br>
                Email:
                <spring:bind path="email">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind><br>
                <input type="submit" value="OK">
            </form>
        </spring:nestedPath>
    </body>
</html>
