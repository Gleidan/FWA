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
    <h1>Регистрация</h1>
    <form action="${pageContext.request.contextPath}/signUp" method="post">
        <label for="firstName">Имя :
            <input type="text" name="firstName" id="firstName" value="${firstName}">
        </label>
        <label for="lastName">Фамилия:
            <input type="text" name="lastName" id="lastName" value="${lastName}">
        </label>
        <label for="email">Email:
            <input type="text" name="email" id="email" value="${email}">
        </label>
        <label for="password">Password:
            <input type="text" name="password" id="password" value="${password}">
        </label>
            <input type="submit" name="signup" value="Sign Up">
    </form>
</body>
</html>
