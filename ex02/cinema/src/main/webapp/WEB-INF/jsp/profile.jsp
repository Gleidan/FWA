<%@ page import="edu.school21.cinema.model.User" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.ResourceBundle" %>
<%@ page import="edu.school21.cinema.model.AuthenticationData" %>
<%@ page import="java.io.File" %>
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
<%
    for (AuthenticationData data : user.getAuthList()) {
%>
        <%=data.getIp() + " "%>
        <%=data.getDate()%>
        <br>
<%
}
    if (request.getAttribute("images") != null) {
        File path = new File((String) request.getAttribute("images"));
%>
<ul>
<%
    for (File image : path.listFiles()) {
        String imageName = image.getName();
%>
    <li>
        <a href="/images/<%=imageName%>" target="_blank"><%=imageName%></a>
    </li>
<%
    }
}
%>
</ul>
<form enctype="multipart/form-data" method="POST" action="/profile">
    <p>
        <input type="file" name="image">
        <input type="submit" value="Отправить">
    </p>
</form>
</body>
</html>

