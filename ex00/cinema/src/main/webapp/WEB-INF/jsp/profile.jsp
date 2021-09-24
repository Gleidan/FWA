<%@ page import="edu.school21.cinema.model.User" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.ResourceBundle" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
</head>
<body>
<h1>User information</h1>
<% User user = (User) request.getSession().getAttribute("user"); %>
<%=user.getFirstName()%>
<br>
<%=user.getLastName()%>
<br>
<%=user.getEmail()%>
<br>
<%=user.getUser_id()%>
</body>
</html>

