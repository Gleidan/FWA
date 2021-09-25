<%@ page import="edu.school21.cinema.model.User" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.ResourceBundle" %>
<%@ page import="edu.school21.cinema.model.AuthenticationData" %>
<%@ page import="java.io.File" %>
<%request.setCharacterEncoding("UTF-8");%>
<style>
    <%@ include file="/css/style.css" %>
</style>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
</head>
<body>
<h1>Profile</h1>
<div class="userInfo">
    <p class="title">
        <% User user = (User) request.getSession().getAttribute("user"); %>
        <%=user.getFirstName()%> <%=user.getLastName()%>
    <br>
        <%=user.getEmail()%>
    <br>
    </p>
    <table>
        <tr>
            <th>IP</th>
            <th>Date/time</th>
        </tr>
<%
    for (AuthenticationData data : user.getAuthList()) {
%>
        <tr>
            <td><%=data.getIp() + " "%></td>
            <td><%=data.getDate()%></td>
        </tr>
<%
} %>
    </table>
</div>
<br>
<div class="userImage">
    <%
    if (request.getAttribute("images") != null) {
        File path = new File((String) request.getAttribute("images"));
%>
<ul>
    <table>
        <tr>
            <th>Image</th>
        </tr>
<%
    for (File image : path.listFiles()) {
        String imageName = image.getName();
%>
        <tr>
            <td><a href="/FWA/images/<%=imageName%>" target="_blank"><%=imageName%></a></td>
        </tr>
<%
    } %>
    </table>
<% }
%>
</ul>
<form enctype="multipart/form-data" method="POST" action="/FWA/profile">
    <p>
        <input type="file" name="image">
        <input type="submit" value="Отправить">
    </p>
</form>
</div>
</body>
</html>

