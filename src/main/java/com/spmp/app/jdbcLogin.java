package com.spmp.app;

public class jdbcLogin {
    import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

    @WebServlet("/LoginServlet")
    public class LoginServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            // Step 1: Get parameters from login form (id, password, role)
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            try {
                // Step 2: Load jTDS driver class
                Class.forName("net.sourceforge.jtds.jdbc.Driver");

                // Step 3: Connect to SQL Server database
                Connection conn = DriverManager.getConnection(
                        "jdbc:jtds:sqlserver://localhost:1433/SPMP;user=sa;password=YourPasswordHere");

                // Step 4: Prepare SQL query to check if user exists with correct id, password and role
                String sql = "SELECT * FROM Users WHERE user_id = ? AND password = ? AND role = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                // Step 5: Set query parameters
                ps.setString(1, id);
                ps.setString(2, password);
                ps.setString(3, role);

                // Step 6: Execute the query
                ResultSet rs = ps.executeQuery();

                // Step 7: Check if a user was found
                if (rs.next()) {
                    // Step 8: Create a new session
                    HttpSession session = request.getSession();
                    session.setAttribute("id", id);
                    session.setAttribute("role", role);

                    // Step 9: Redirect user based on their role
                    if ("student".equals(role)) {
                        response.sendRedirect("transferRequest.jsp");
                    } else if ("admin".equals(role)) {
                        response.sendRedirect("adminDashboard.jsp");
                    }
                } else {
                    // Step 10: Login failed, redirect back with error
                    response.sendRedirect("login.jsp?error=Invalid credentials");
                }

                // Step 11: Close database connection
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Step 12: Handle server/database errors
                response.sendRedirect("login.jsp?error=serverError");
            }
        }
    }

}
