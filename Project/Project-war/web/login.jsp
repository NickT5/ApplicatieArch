<%-- 
    Document   : login
    Created on : Nov 28, 2017, 11:31:56 AM
    Author     : kevin
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
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);
        body{
            background: #1D8DB0;
        }
        .login-page {
          width: 360px;
          padding: 8% 0 0;
          margin: auto;
        }
        .form {
          background: #FFFFFF;
          padding: 45px;
          text-align: center;
          box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        table{
            width: 100%;
        }
        .input {
          font-family: "Roboto", sans-serif;
          outline: 0;
          background: #f2f2f2;
          width: 100%;
          border: 0;
          margin: 0 0 15px;
          padding: 15px;
          box-sizing: border-box;
          font-size: 14px;
        }
        .knop {
          font-family: "Roboto", sans-serif;
          text-transform: uppercase;
          outline: 0;
          background: #00407A;
          width: 100%;
          border: 0;
          padding: 15px;
          color: #FFFFFF;
          font-size: 14px;
          cursor: pointer;
        }
        .knop:hover,.form button:active,.form button:focus {
          background: #00294f;
        }
        h1 {
          font-family: "Roboto", sans-serif;    
        }
        </style>
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <h1>Welkom</h1>
                <form method=post action="j_security_check" class="login-form">
                    <table>
                        <tr><td> <input class="input" type="text" name="j_username" placeholder="Naam"/></td></tr>
                        <tr><td> <input class="input" type="password" name="j_password" placeholder="Paswoord"/></td></tr>
                    </table>
                    <input class="knop" type="submit" value="login"/>
                    <input type="hidden" name="from" value="login">
                </form>
            </div>    
        </div>    
    </body>
</html>
