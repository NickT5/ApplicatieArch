<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="post" action="<c:url value='Controller.do'/>">
    <input type="hidden" name="from" value="afmelden">
    <input type="submit" id="afmeldknop" value="Afmelden">
</form> 
<!-- response.encodeURL() dient voor als cookies af staan, dan dat de sessionID mee door gegeven wordt in de URL -->
<!-- core tag library gebruiken ipv < % out.println(response.encodeURL("Controller.do")); %>       -->
  