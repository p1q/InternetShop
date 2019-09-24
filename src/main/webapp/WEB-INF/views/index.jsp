<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Internet Shop "Rozetka Killer"</title>
  </head>
  <body>
    <h3>MAIN MENU</h3>

    <!-- Add registration button -->
    <form action = register method = "GET">
      <input type = "submit" value = "Register"/>
    </form>

    <!-- Add item forward button -->
    <form action = /user/add-item method = "GET">
      <input type = "submit" value = "Add item"/>
    </form>

    <!-- Add user button -->
    <form action = /user/add-user method = "GET">
      <input type = "submit" value = "Add user"/>
    </form>

    <!-- Show all items button -->
    <form action = /user/show-all-items method = "GET">
      <input type = "submit" value = "Show all items"/>
    </form>

    <!-- Show all users button -->
    <form action = /user/show-all-users method = "GET">
      <input type = "submit" value = "Show all users"/>
    </form>

    <!-- Show bucket button -->
    <form action = /user/show-bucket method = "GET">
      <input type = "submit" value = "Show bucket"/>
    </form>

    <!-- Show all orders button -->
    <form action = /user/show-all-orders method = "GET">
      <input type = "submit" value = "Show all orders"/>
    </form>

  <hr>

    <!-- Login button -->
    <form action = login method = "GET">
      <input type = "submit" value = "Login"/>
    </form>

    <!-- User orders button -->
    <form action = /user/show-user-orders method = "GET">
      <input type = "submit" value = "Show user orders"/>
    </form>

  </body>
</html>
