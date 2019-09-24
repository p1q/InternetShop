<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<internetshop.model.Order>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show User Orders</title>
</head>
<body>

<h3>USER ORDERS LIST:</h3>

<table border="3">

    <tr>
        <th>Order ID</th>
        <th>Items</th>
        <th><span style="color: #ff0000;"><strong>DELETE ORDER</strong></span></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:forEach var="item" items="${order.items}">
                    <c:out value="${item.toString()}" /><br />
                </c:forEach>
            </td>
            <td style="text-align: center;">
                <a href="/delete-order?order_id=${order.id}">
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
