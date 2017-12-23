<%-- 
    Document   : aanloggen
    Created on : Nov 3, 2009, 3:09:02 PM
    Author     : joost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!--<link href="css/login.css" rel="stylesheet" type="text/css">-->
        <style>
            h1{
                color:red;
                text-align:center;
            }
            body{
                background-color:#ff6347;
            }
        </style>
    </head>
    <body>
        <h1>Welkom</h1>
        <p> Log je hier in voor de les Applicatie-Architecuren:</p>
        <form method=post action="j_security_check">
            <table>
                <tr> <td> Naam: </td> <td> <input type="text" name="j_username" /></td></tr>
                <tr> <td> Paswoord:</td><td><input type="password" name="j_password" /></td></tr>
            </table>
            <input type="submit" />
        </form>
    </body>
</html>
