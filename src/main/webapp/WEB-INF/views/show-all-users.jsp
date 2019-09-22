<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<internetshop.model.User>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show All Users</title>
</head>
<body>

<h3>ALL USERS LIST:</h3>

<table border="3">

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Surname</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Login</th>
    <th><span style="color: #ff0000;"><strong>DELETE USER</strong></span></th>
</tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.userId}" />
            </td>
            <td>
                <c:out value="${user.userName}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <c:out value="${user.email}" />
            </td>
            <td>
                <c:out value="${user.phone}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td style="text-align: center;">
                <a href="/delete-user?user_id=${user.userId}">
                    <span style="color: #ff0000;">DELETE</span></a>
            </td>
        </tr>
    </c:forEach>

</table>

<br />
<!-- Return to main page button -->
<form action=/ method="GET">
    <input type="submit" value="BACK TO HOME"/>
</form>

</body>
</html>
