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
    </head>
    <body>
        <h1>Hallo ${voornaam}!</h1>        
        <form method="post" action="<c:url value='controller.do'/>">
            <table>
                <tr>
                    <td>Maak een nieuwe groep</td>
                    <td><input type="submit" value="Nieuw"/></td>
                </tr>
                <tr>
                    <td>Edit een bestaande groep</td>
                    <td>
                        <select name="Groepen">
                            <c:forEach var="g" items="${aantalGroepen}">
                                <option>${g}</option>
                            </c:forEach>  
                        </select>
                    </td>
                    <td><input type="submit" value="Edit"/></td>
                </tr>
                <c:forEach var="g" items="${groepsIndeling}">
                    <tr>
                        <td>${g.groepnummer}</td>
                        <td>${g.gebruikerId.voornaam}</td><!-- gebruikerId is van het type Gebruikers -->
                    </tr>
                </c:forEach> 
            </table>
        </form>
           
    </body>
</html>
