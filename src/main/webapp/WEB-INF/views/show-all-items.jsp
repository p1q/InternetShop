<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show All Items</title>
</head>
<body>
<h3>ALL ITEMS LIST:</h3>

<table border="3">

    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>ADD TO BUCKET</th>
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
                <a href="/user/add-item-to-bucket?item_id=${item.id}">
                    <span style="color: #0000ff;"><strong>ADD</strong></span></a>
            </td>
        </tr>
    </c:forEach>

</table>

<br />
<!-- Return to main page button -->
<form action =/ method="GET">
    <input type="submit" value="BACK TO HOME"/>
</form>

</body>
</html>
