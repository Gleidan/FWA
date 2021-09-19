<%--
  Created by IntelliJ IDEA.
  User: a18887459
  Date: 18.09.2021
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Аутентификация</title>
</head>
<body>
    <h1>Аутентификация</h1>
    <form action="/signIn" method="post">
        <label for="email">Email:
            <input type="text" name="email" id="email">
        </label>
        <label for="password">Password:
            <input type="text" name="password" id="password">
        </label>
        <input type="submit" name="signin" value="Sign In">
    </form>
</body>
</html>
