<%--
  Created by IntelliJ IDEA.
  User: macintosh
  Date: 26/04/2025
  Time: 12:13 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SPMP | login</title>
</head>
<body>
ID: <input type="text" name="id" required><br>
Password: <input type="password" name="password" required><br>
Role:
<select name="role">
    <option value="student">Student</option>
    <option value="admin">Admin</option>
</select><br>
<input type="submit" value="Login">
</body>
</html>
