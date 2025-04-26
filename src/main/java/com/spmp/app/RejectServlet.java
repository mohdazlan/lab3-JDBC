package com.spmp.app;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
public class RejectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String transferId = request.getParameter("id");

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://localhost:1433/SPMP;user=sa;password=YourPasswordHere");

            String sql = "UPDATE Transfers SET status = 'Rejected' WHERE transfer_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(transferId));
            ps.executeUpdate();

            conn.close();
            response.sendRedirect("adminDashboard.jsp"); // Back to dashboard
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminDashboard.jsp?error=approveFailed");
        }
    }
}
