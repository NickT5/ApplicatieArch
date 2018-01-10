<%-- 
    Document   : finaal
    Created on : 02-Jan-2018, 12:36:32
    Author     : kevin
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body{
            background: #1D8DB0;
        }
        .container{
            width: 350px;
            padding-top: 8%;
            margin: auto;
           
        }
        .inhoud{
             background: #FFFFFF;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        #afmeldknop{
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            background: #ffec7c;
            border: 0;
            padding: 15px;
            color: black;
            font-size: 14px;
            cursor: pointer;
        }
        #afmeldknop:hover{
          background: #fff3aa;
        }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="inhoud">            
                <h1><u>Groep ${groepnummer}</u></h1>
                    <ul>
                    <c:forEach var="g" items="${groepstudenten2}">
                        <li>${g}</li>
                    </c:forEach>  
                    </ul>
            </div>
            <footer>
                <jsp:include page="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
