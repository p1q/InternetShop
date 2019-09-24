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
    <form action = add-item method = "GET">
      <input type = "submit" value = "Add item"/>
    </form>

    <!-- Add user button -->
    <form action = add-user method = "GET">
      <input type = "submit" value = "Add user"/>
    </form>

    <!-- Show all items button -->
    <form action = "show-all-items" method = "POST">
      <input type = "submit" value = "Show all items"/>
    </form>

    <!-- Show all users button -->
    <form action = "show-all-users" method = "GET">
      <input type = "submit" value = "Show all users"/>
    </form>

    <!-- Show bucket button -->
    <form action = "show-bucket" method = "GET">
      <input type = "submit" value = "Show bucket"/>
    </form>

    <!-- Show all orders button -->
    <form action = "show-all-orders" method = "GET">
      <input type = "submit" value = "Show all orders"/>
    </form>

  </body>
</html>
