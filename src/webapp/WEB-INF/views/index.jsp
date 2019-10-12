<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Internet Shop "Rozetka Killer"</title>
</head>
<body>
<div>${registrationFinished}</div>

<table border="5" >
  <tbody>
  <tr>
    <td style="text-align: center;" colspan="2">
      <h2><span style="text-decoration: underline; color: #0000ff;">
        <strong>MAIN MENU</strong></span></h2>
    </td>
  </tr>
  <tr>
    <td style=""><strong>ADMIN FUNCTIONS</strong></td>
    <td style=""><strong>USER FUNCTIONS</strong></td>
  </tr>
  <tr>
    <td style="text-align: center;">
      <!-- Add item button -->
      <form action = /user/add-item method = "GET">
        <input type = "submit" value = "Add item"/>
      </form>
    </td>
    <td style="text-align: center;">
      <!-- Show bucket button -->
      <form action = /user/show-bucket method = "GET">
        <input type = "submit" value = "Show bucket"/>
      </form>
    </td>
  </tr>
  <tr>
    <td style="text-align: center;">
      <!-- Delete item button -->
      <form action = /user/delete-item method = "GET">
        <input type = "submit" value = "Delete item"/>
      </form>
    </td>
    <td style="text-align: center;">
      <!-- User orders button -->
      <form action = /user/show-user-orders method = "GET">
        <input type = "submit" value = "Show user orders"/>
      </form>
    </td>
  </tr>
  <tr style="height: 18px;">
    <td style="text-align: center;">
      <!-- Show all users button -->
      <form action = /user/show-all-users method = "GET">
        <input type = "submit" value = "Show all users"/>
      </form>
    </td>
    <td style="text-align: center;"></td>
  </tr>
  <tr style="height: 18px;">
    <td style="text-align: center; height: 18px;">
      <!-- Show all orders button -->
      <form action = /user/show-all-orders method = "GET">
        <input type = "submit" value = "Show all orders"/>
      </form>
    </td>
    <td style="text-align: center;"></td>
  </tr>
  <tr>
    <td style="text-align: center;" colspan="2" >
      <strong>GENERAL FUNCTIONS</strong></td>
  </tr>
  <tr>
    <td style="text-align: center;" colspan="2">
      <!-- Add registration button -->
      <form action = register method = "GET">
        <input type = "submit" value = "Register"/>
      </form>
    </td>
  </tr>
  <tr style="height: 18px;">
    <td style="text-align: center;" colspan="2">
      <!-- Show all items button -->
      <form action = /show-all-items method = "GET">
        <input type = "submit" value = "Show all items"/>
      </form>
    </td>
  </tr>
  <tr style="height: 18px;">
    <td style="text-align: center;" colspan="2">
      <!-- Login button -->
      <form action = login method = "GET">
        <input type = "submit" value = "Login"/>
      </form>
    </td>
  </tr>
  <tr>
    <td style="text-align: center;" colspan="2">
      <!-- Logout button -->
      <form action = logout method = "GET">
        <input type = "submit" value = "Logout"/>
      </form>
    </td>
  </tr>
  </tbody>
</table>
</body>
</html>
