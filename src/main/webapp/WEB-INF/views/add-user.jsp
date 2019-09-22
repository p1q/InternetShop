<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>

<!-- Title -->
<h3>User ADDING</h3>

<!-- Item adding form -->
<form action = "add-user" method = "POST">
    <table border = "0">

        <tr>
            <td><b>User name</b></td>
            <td><input type = "text" name = "name" size = "20"/></td>
        </tr>

        <tr>
            <td colspan = "2"><input type = "submit" value = "Add user"/></td>
        </tr>
    </table>
</form>
<!-- End of user adding form -->

<br />
<!-- Return to main page button -->
<form action = / method = "GET">
    <input type = "submit" value = "BACK TO HOME"/>
</form>

</body>
</html>
