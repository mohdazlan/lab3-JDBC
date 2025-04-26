package com.spmp.app;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean loginSuccess = false;

        if ("student".equals(role)) {
            // Validate student credentials (use JDBC check later)
            if ("s123".equals(id) && "pass123".equals(password)) {
                loginSuccess = true;
            }
        } else if ("admin".equals(role)) {
            // Validate admin credentials
            if ("admin1".equals(id) && "adminpass".equals(password)) {
                loginSuccess = true;
            }
        }

        if (loginSuccess) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("role", role);

            if ("student".equals(role)) {
                response.sendRedirect("transferRequest.jsp");
            } else {
                response.sendRedirect("adminDashboard.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
    }
}
