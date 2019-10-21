<%--
  Created by IntelliJ IDEA.
  User: egors
  Date: 20.10.2019
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<div>
    <table>
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach items="${dishes}" var="dish">
            <tr>
                <td>${dish.name}</td>
                <td>${dish.cuisine}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
