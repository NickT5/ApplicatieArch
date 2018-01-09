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
        <title>Voorkeurspagina</title>
        <style>
        body{
            background: #1D8DB0;
        }
        .container{
            width: 75%;
            padding-top: 8%;
            margin: auto;
           
        }
        .inhoud{
            background: #FFFFFF;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }
        
        
        section{
            border-radius: 15px;
            padding: 8px;
            background-color: #f7f7f7; 
            text-align: center;
            width:100%;
        }
        section > div{
            display: inline-block;
        }
        input[type="button"]{
           display: block;
        }
        select,input[type="text"]{
            width: 160px;
        }
        .knop {
          font-family: "Roboto", sans-serif;
          text-transform: uppercase;
          outline: 0;
          background: #00407A;
          width: 50%;
          border: 0;
          padding: 15px;
          color: #FFFFFF;
          font-size: 14px;
          cursor: pointer;
        }
        .knop:hover,.form button:active,.form button:focus {
          background: #00294f;
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="inhoud">
        
        <h1>Hallo ${naamIngelogd}!</h1>
        <p>Geef aan met welke personen je wel of niet wil samen zitten in een groep.</p>

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
            </section>
                <input type="submit" class="knop" name="from" value="Save"/>
                <input type="submit" class="knop" name="from" value="Bevestig"/>
            <!--<input type="hidden" name="from" value="menu">-->
        </form>
        <h2><u>Opgelet:</u> Als je op de knop bevestig klikt, kan je uw keuze <u>niet</u> meer aanpassen!</h2>
            
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
        </div>
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </div>
    </body>
</html>
