<%-- 
    Document   : menu
    Created on : Nov 28, 2017, 11:29:02 AM
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
        section{
            padding: 8px;
            background-color: #f0f0f0;
            text-align: center;             
        }

        div{
            display: inline-block;
        }
        input[type="button"]{
           display: block;
        }
        select,input[type="text"]{
            width: 160px;
        }           
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

    </head>
    <body>
        <h1>Hallo ${naamIngelogd}!</h1>
        <p>Geef aan met welke personen je wel of niet wil samen zitten in een groep.</p>

        <section>
            <div>
                <h2>Niet voorkeur</h2>
                <select multiple id="leftValues" size="5"></select>
            </div>
            <div>
                <input type="button" id="btnLeft1" value="&lt;&lt;" />
                <input type="button" id="btnRight1" value="&gt;&gt;" />
            </div>
            <div>
                <h2>Alle medestudenten</h2>
                <select multiple id="midValues" size="5">
                    <c:forEach var="naam" items="${namen}">
                        <option>${naam}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="button" id="btnLeft2" value="&lt;&lt;" />
                <input type="button" id="btnRight2" value="&gt;&gt;" />
            </div>
            <div>
                <h2>Voorkeur</h2>
                <select multiple id="rightValues" size="5"></select>
            </div>
        </section>

        <script>
            $("#btnLeft1").click(function () {
                var selectedItem = $("#midValues option:selected");
                $("#leftValues").append(selectedItem);
            });
            
            $("#btnRight1").click(function () {
                var selectedItem = $("#leftValues option:selected");
                 $("#midValues").append(selectedItem);
            });
            $("#btnLeft2").click(function () {
                var selectedItem = $("#rightValues option:selected");
                $("#midValues").append(selectedItem);
            });
            $("#btnRight2").click(function () {
                var selectedItem = $("#midValues option:selected");
                $("#rightValues").append(selectedItem);
            });
        </script>
    </body>
</html>
