<jsp:useBean id="loginValue" scope="request" type="java.lang.String"/>
<jsp:useBean id="passwordValue" scope="request" type="java.lang.String"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div>${errorMsg}</div>
<form action="login" method="POST">
    <div class="container">
        <h1>Login</h1>
        <p>Please fill in this form to login.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" value="${loginValue}" name="login" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" value="${passwordValue}" name="password" required>

        <button type="submit" class="registerbtn">Login</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="register">Register</a>.</p>
    </div>
</form>

</body>
</html>
