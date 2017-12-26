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
        <title>Edit</title>
        <style>
        select, input[type="text"] {
            width: 160px;
            box-sizing: border-box;
        }
        section {
            padding: 8px;
            background-color: #ffffff;
            overflow: auto;
        }
        section > div {
            float: left;
            padding: 4px;
        }
        section > div + div {
            width: 40px;
            text-align: center;
        }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="edit-page">
            <div class="form">
                <h1>Groep ${groepnummer}</h1>
                    <section class="container">
                        <div>
                            <select id="leftValues" size="6" multiple>
                                <c:forEach var="g" items="${studentenInGroep}">
                                    <option>${g.gebruikerId.voornaam} ${g.gebruikerId.achternaam}</option><!-- gebruikerId is van het type Gebruikers -->
                                </c:forEach> 
                            </select>
                        </div>
                        <div>
                            <input type="button" id="btnRight" value="&gt;&gt;" />
                            <input type="button" id="btnLeft" value="&lt;&lt;" />
                        </div>
                        <div>
                            <select id="rightValues" size="6" multiple>
                                <c:forEach var="g" items="${alleStudenten}">
                                    <option>${g.gebruikers.voornaam} ${g.gebruikers.achternaam}</option><!-- gebruikerId is van het type Gebruikers -->
                                </c:forEach> 
                            </select>
                        </div>  
                    </section>
            </div>
            <div class="voorkeur">
                <p></p>   
            </div> 
            <div class="nietvoorkeur">
                <p></p>        
            </div> 
        </div>
        <script>
            $("#btnLeft").click(function () {
            var selectedItem = $("#rightValues option:selected");
            $("#leftValues").append(selectedItem);
            });

            $("#btnRight").click(function () {
             var selectedItem = $("#leftValues option:selected");
             $("#rightValues").append(selectedItem);
            });
        </script>
    </body>
</html>