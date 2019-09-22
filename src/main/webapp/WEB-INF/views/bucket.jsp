<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h3>ITEMS IN YOUR BUCKET:</h3>

<table border="3">

    <tr>
        <th>Item Name</th>
        <th>Item Price</th>
        <th><span style="color: #ff0000;"><b>DELETE ITEM</b></span></th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td style="text-align: center;">
                <a href="/delete-item-from-bucket?item_id=${item.id}">
                    <span style="color: #ff0000;">DELETE</span></a>
            </td>
        </tr>
    </c:forEach>

</table>

<br />
<!-- Checkout button -->
<form action =/checkout method="GET">
    <input type="submit" value="CHECKOUT"/>
</form>

<br />
<!-- Return to main page button -->
<form action =/ method="GET">
    <input type="submit" value="BACK TO HOME"/>
</form>

</body>
</html>
