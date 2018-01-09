<%-- 
    Document   : finaal
    Created on : 09-Jan-2018, 15:49:48
    Author     : Nick
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
            width: 700px;
            padding-top: 8%;
            margin: auto;
           
        }
        .inhoud{
             background: #FFFFFF;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
              
        table{
            vertical-align: top;
            display: inline-block;
        }       
        table td, table th {
            border: 1px solid #ddd;
            padding: 8px;
        }
        table tr:nth-child(even){background-color: #f2f2f2;}
        table tr:nth-child(odd){background-color: #dddddd;}
        
        table th{
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            color: white;
        }
        #table_nvk th {background-color: #DB2929;}
        #table_vk th {background-color: #4CAF50;}
      
        #table_nvk tr:hover {background-color: #f9d9d9;}
        #table_vk tr:hover {background-color: #d9f9df;}
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
                <h1><u>Uw keuze</u></h1>
                <table id="table_nvk">
                    <tr>
                        <th>Niet voorkeur</th>
                    </tr>
                    <c:forEach var="i" items="${list_nvk_names}">
                    <tr>
                        <td>${i}</td>
                    </tr>   
                    </c:forEach>
                </table>

                <table id="table_vk">
                    <tr>
                        <th>Voorkeur</th>
                    </tr>
                    <c:forEach var="i" items="${list_vk_names}">
                    <tr>
                        <td>${i}</td>
                    </tr>      
                    </c:forEach>
                </table>
            </div>
            <footer>
                <jsp:include page="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
