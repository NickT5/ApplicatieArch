<%-- 
    Document   : menu
    Created on : Nov 28, 2017, 11:29:02 AM
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hallo <%= request.getUserPrincipal() %>!</h1>
    </body>
</html>
