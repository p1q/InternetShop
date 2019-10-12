<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>

<!-- Title -->
<h3>ITEM ADDING</h3>

<!-- Item adding form -->
<form action = "add-item" method = "POST">
    <table border = "0">

        <tr>
            <td><b>Item name</b></td>
            <td><input type = "text" name = "name" size = "20"/></td>
        </tr>

        <tr>
            <td><b>Item price</b></td>
            <td><input type = "text" name = "price" size = "20"/></td>
        </tr>

        <tr>
            <td>Catalog Section</td>
            <td>
                <select name = "section">
                    <option value = "Phones">Phones</option>
                    <option value = "Tablets">Tablets</option>
                    <option value = "Watches">Watches</option>
                    <option value = "Glasses">Glasses</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan = "2"><input type = "submit" value = "Add item"/></td>
        </tr>
    </table>
</form>
<!-- End of item adding form -->

<br />
<!-- Return to main page button -->
<form action = / method = "GET">
    <input type = "submit" value = "BACK TO HOME"/>
</form>

</body>
</html>
