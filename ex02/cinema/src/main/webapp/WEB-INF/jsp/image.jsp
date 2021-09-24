<%--
  Created by IntelliJ IDEA.
  User: a18887064
  Date: 23.09.2021
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>image</title>
</head>
<body>
<img src="data:image/png;base64,<%=request.getAttribute("image")%>" alt="Profile from image" height="800" width="1200">
</body>
</html>
