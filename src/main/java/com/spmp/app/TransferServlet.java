package com.spmp.app;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String studentId = request.getParameter("studentId");
        String desiredPoly = request.getParameter("desiredPoly");
        String reason = request.getParameter("reason");
        String guardianName = request.getParameter("guardianName");
        String contactNumber = request.getParameter("contactNumber");
        String email = request.getParameter("email");

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://localhost:1433/SPMP;user=sa;password=YourPasswordHere");

            String sql = "INSERT INTO Transfers (student_id, full_name, desired_polytechnic, reason, " +
                    "guardian_name, contact_number, email, submission_date, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, fullName);
            ps.setString(3, desiredPoly);
            ps.setString(4, reason);
            ps.setString(5, guardianName);
            ps.setString(6, contactNumber);
            ps.setString(7, email);
            ps.setString(8, "Pending");

            ps.executeUpdate();
            conn.close();

            response.sendRedirect("trasnferRequest.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("transferRequest.jsp?error=true");
        }
    }
}
