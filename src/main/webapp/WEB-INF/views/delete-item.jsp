<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Delete Item</title>
</head>
<body>
<h3>ALL ITEMS LIST:</h3>

<table border="3">

    <tr>
        <th>Name</th>
        <th><span style="color: #ff0000;"><b>DELETE ITEM</b></span></th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td style="text-align: center;">
                <a href="/user/delete-item-commit?item_id=${item.id}">
                    <span style="color: #0000ff;">DELETE</span></a>
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
