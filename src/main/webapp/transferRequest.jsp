<%--
  Created by IntelliJ IDEA.
  User: macintosh
  Date: 26/04/2025
  Time: 12:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="TransferServlet" method="post">
    <h3>Student Information</h3>
    Full Name: <input type="text" name="fullName" required><br>
    Student ID: <input type="text" name="studentId" required><br>

    <h3>Desired Polytechnic</h3>
    Polytechnic Name:
    <input type="text" name="desiredPoly" required><br>

    <h3>Reason for Transfer</h3>
    Reason:
    <textarea name="reason" rows="4" required></textarea><br>

    <h3>Parent/Guardian Information</h3>
    Parent/Guardian Name: <input type="text" name="guardianName" required><br>
    Contact Number: <input type="text" name="contactNumber" required><br>
    Email Address: <input type="email" name="email" required><br>

    <input type="submit" value="Submit Request">
</form>
<%
    String success = request.getParameter("success");
    if ("true".equals(success)) {
%>
<p style="color: green;">Your transfer request has been submitted successfully!</p>
<%
    }
%>
<a href="LogoutServlet">Logout</a>
</body>
</html>
