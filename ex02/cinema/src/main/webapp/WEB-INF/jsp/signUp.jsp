<%--
  Created by IntelliJ IDEA.
  User: a18887459
  Date: 18.09.2021
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@ include file="/css/style.css" %>
</style>


<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<%--<% if (request.getAttribute("error") != null) {%> <%= request.getAttribute("error")%><%}%>--%>

<div class="form-style-6">
    <h1>Регистрация</h1>
    <form action="/FWA/signUp" method="POST">
        <label>FirstName:
            <input type="text" placeholder="Enter user first name" name="firstName" required>
        </label>
        <label>LastName:
            <input type="text" placeholder="Enter user last name" name="lastName" required>
        </label>
        <label>Email:
            <input type="email" placeholder="Enter user email" name="email" required>
        </label>

        <label>Password:
            <input type="password" placeholder="Enter password" name="password" required>
        </label>
        <button type="submit">signUp</button>
    </form>
</div>
</body>
</html>
