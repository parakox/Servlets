<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 24.03.2020
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>

</head>
<body>
<form action="authentication" method="post">
  <table style="with: 50%">
    <tr>
      <td>Name</td>
      <td><input type="text" name="name" /></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="password" /></td>
    </tr>
    </table>
  <input type="submit" name = "action" value="Log in" />
  <input type="submit" name = "action" value="Register" /></form>
</body>
</html>
