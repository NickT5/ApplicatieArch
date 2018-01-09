<%-- 
    Document   : voorlopigeKeuze
    Created on : 25-Dec-2017, 15:38:11
    Author     : Nick
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voorlopige keuze</title>
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
        <h1>Dit is uw voorlopige keuze</h1>
    <form method="post" action="<c:url value='Controller.do'/>">
        <section>
            <div>
                <h2>Niet voorkeur</h2>
                <select multiple id="leftValues" size="10" name="nietvoorkeur">
                    <c:forEach var="i" items="${list_nvk_names}">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="button" id="btnLeft1" value="&lt;&lt;" />
                <input type="button" id="btnRight1" value="&gt;&gt;" />
            </div>
            <div>
                <h2>Alle medestudenten</h2>
                <select multiple id="midValues" size="10">
                    <c:forEach var="i" items="${lijstNamen_van_studenten}">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input type="button" id="btnLeft2" value="&lt;&lt;" />
                <input type="button" id="btnRight2" value="&gt;&gt;" />
            </div>
            <div>
                <h2>Voorkeur</h2>
                <select multiple id="rightValues" size="10" name="voorkeur">
                    <c:forEach var="i" items="${list_vk_names}">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Bevestig"/>
        </section>
        <input type="hidden" name="from" value="voorlopigeKeuze">
    </form>
        <h2>Opgelet: na bevestiging kan je uw keuze niet meer aanpassen!</h2>
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
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
