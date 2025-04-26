<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: macintosh
  Date: 26/04/2025
  Time: 11:56 am
  To change this template use File | Settings | File Templates.
--%>
<%
  HttpSession session = request.getSession(false);
  if (session == null || session.getAttribute("id") == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String role = (String) session.getAttribute("role");
  if (!"admin".equals(role)) {
    response.sendRedirect("login.jsp"); // block if not student
  }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("id") %>!</h2>
<h2>Student Transfer Requests</h2>

<table border="1" cellpadding="8" cellspacing="0">
  <tr>
    <th>Transfer ID</th>
    <th>Student ID</th>
    <th>Full Name</th>
    <th>Desired Polytechnic</th>
    <th>Reason</th>
    <th>Submission Date</th>
    <th>Status</th>
    <th>Action</th>
  </tr>

  <%
    try {
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      Connection conn = DriverManager.getConnection(
              "jdbc:jtds:sqlserver://localhost:1433/SPMP;user=sa;password=YourPasswordHere");

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Transfers ORDER BY submission_date DESC");

      while (rs.next()) {
  %>
  <tr>
    <td><%= rs.getInt("transfer_id") %></td>
    <td><%= rs.getString("student_id") %></td>
    <td><%= rs.getString("full_name") %></td>
    <td><%= rs.getString("desired_polytechnic") %></td>
    <td><%= rs.getString("reason") %></td>
    <td><%= rs.getTimestamp("submission_date") %></td>
    <td><%= rs.getString("status") %></td>
    <td>
      <% if ("Pending".equals(rs.getString("status"))) { %>
      <a href="ApproveServlet?id=<%= rs.getInt("transfer_id") %>">✅ Approve</a> |
      <a href="RejectServlet?id=<%= rs.getInt("transfer_id") %>">❌ Reject</a>
      <% } else { %>
      (No Action)
      <% } %>
    </td>
  </tr>
  <%
      }
      conn.close();
    } catch (Exception e) {
      out.println("<tr><td colspan='8'>Error loading data.</td></tr>");
      e.printStackTrace(out);
    }
  %>
</table>

<br><br>
<a href="LogoutServlet">Logout</a>

</body>
</html>
