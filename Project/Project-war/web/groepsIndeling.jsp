<%-- 
    Document   : groepsIndeling
    Created on : Nov 28, 2017, 11:31:56 AM
    Author     : kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groepsindeling</title>
                <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);
        body{
            background: #1D8DB0;
        }
        .indeling-page {
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
        select {
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
        <div class="indeling-page">
            <div class="form">
                <h1>Hallo ${voornaam}!</h1>        
                <form method="post" action="<c:url value='controller.do'/>">
                    <table>
                        <tr>
                            <td>
                                <select name="Groepen">
                                    <c:forEach var="g" items="${aantalGroepen}">
                                        <option>Groep ${g}</option>
                                    </c:forEach>  
                                </select>
                            </td>
                        </tr>
                        <tr>
                             <td><input class="knop" type="submit" value="Edit"/></td>
                        </tr>
                        <tr>
                            <td><input class="knop" type="submit" value="Nieuw"/></td>
                        </tr>
                    </table>
                </form>
            </div>        
        </div>        
    </body>
</html>
