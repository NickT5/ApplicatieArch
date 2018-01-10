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
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);
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
            margin-left: 50px;
        }
        section > div + div {
            width: 40px;
            text-align: center;
        }
        
        body{
            background: #1D8DB0;
        }
        .edit-page {
          width: 700px;
          padding: 8% 0 0;
          margin: auto;
        }
        .form {
          background: #FFFFFF;
          padding: 45px;
          text-align: center;
          box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
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
        <div class="edit-page">
            <div class="form">
                <h1>Groep ${groepnummer}</h1>
                <form method="get" action="<c:url value='Controller.do'/>">
                    <section class="container">
                        <div>
                            <select name="studentenInGroep" class="leftsection" id="leftValues" size="6" multiple>
                                <c:forEach var="g" items="${studentenInGroep}">
                                    <option>${g.gebruikerId.voornaam} ${g.gebruikerId.achternaam}</option><!-- gebruikerId is van het type Gebruikers -->
                                </c:forEach> 
                            </select>
                        </div>
                        <div>
                            <input type="button" id="btnRight" value="&gt;&gt;" />
                            <input type="button" id="btnLeft" value="&lt;&lt;" />
                        </div>
                        <div class="rightsection">
                            <select id="rightValues" size="6" multiple>
                                <c:forEach var="g" items="${alleStudenten}">
                                    <option>${g.gebruikers.voornaam} ${g.gebruikers.achternaam}</option><!-- gebruikerId is van het type Gebruikers -->
                                </c:forEach> 
                            </select>
                        </div>  
                    </section>
                    <div class="voorkeur">
                    <c:forEach var="v" items="${studentenVoorkeur}">
                        <p class="vk">${v}</p>
                    </c:forEach>    
                    </div> 
                    <div class="nietvoorkeur">
                        
                    </div>   
                    <input id="btnSave" class="knop" type="submit" value="Save"/>
                    <input type="hidden" name="from" value="edit1">
                </form>
                <form method="post" action="<c:url value='Controller.do'/>">
                        <input class="knop" type="submit" value="Bevestig"/>
                        <input type="hidden" name="from" value="edit2">
                </form>         
            </div>
            <footer>
                <jsp:include page="footer.jsp"/>
            </footer>
        </div>
        <script>          
            $("#btnLeft").click(function () {
                var selectedItem = $("#rightValues option:selected");
                $("#leftValues").append(selectedItem);

                $(".vk").each(function () {
                    var s = $(this).text();
                    var t = selectedItem.text();
                    if(s.indexOf(t) !== -1)
                    {
                       $(this).css('color','green');
                    }
                });
            });

            $("#btnRight").click(function () {
                var selectedItem = $("#leftValues option:selected");
                $("#rightValues").append(selectedItem); 
            });  
        </script>

    </body>
</html>
