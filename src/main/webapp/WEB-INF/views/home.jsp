<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    <title></title>
    </head>
    <body>
    <ul>
        <c:forEach items="${countries}" var="item">
            <li>${item.toString()}</li>
        </c:forEach>
    </ul>
    </body>
</html>