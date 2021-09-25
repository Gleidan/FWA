<%--
  Created by IntelliJ IDEA.
  User: a18887459
  Date: 18.09.2021
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@ include file="/css/style.css" %>
</style>
<html>
<head>
    <meta charset="UTF-8">
    <title>Аутентификация</title>
</head>
<body>
<% if (request.getAttribute("error") != null) {%> <%= request.getAttribute("error")%><%}%>
<div class="form-style-6">
    <h1>Аутентификация</h1>
    <form action="/FWA/signIn" method="POST">
        <label>Email: </label>
        <input type="email" placeholder="Enter your email" name="email" required>
        <label>Password: </label>
        <input type="password" name="password" placeholder="Enter your password" required>
        <button type="submit">signIn</button>
    </form>
</div>
</body>
</html>
