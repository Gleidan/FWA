<%--
  Created by IntelliJ IDEA.
  User: a18887459
  Date: 18.09.2021
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<% if (request.getAttribute("error") != null) {%> <%= request.getAttribute("error")%><%}%>
    <h1>Регистрация</h1>
    <form action="/signUp" method="POST">
        <label>FirstName: </label>
        <input type="text" placeholder="Enter user first name" name="firstName" required>
        <label>LastName: </label>
        <input type="text" placeholder="Enter user last name" name="lastName" required>
        <label>Email: </label>
        <input type="email" placeholder="Enter user email" name="email" required>
        <label>Password: </label>
        <input type="password" placeholder="Enter password" name="password" required>
        <button type="submit">signUp</button>
    </form>
</body>
</html>
