<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <% 
 	request.getSession().invalidate(); 
 	response.sendRedirect("Controller.do");
        %>
    </body>
</html>
