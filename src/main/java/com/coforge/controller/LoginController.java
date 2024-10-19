package com.coforge.controller;

import com.coforge.dao.LoginDao;
import com.coforge.model.Login;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    int id;
    String username;
    String userType;
    String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("id"));
        username = req.getParameter("username");
        userType = req.getParameter("userType");
        password = req.getParameter("password"); // Get password from request

        Login login = new Login();
        login.setId(id);
        login.setUsername(username);
        login.setUserType(userType);
        login.setPassword(password);
        LoginDao loginDao = null;
        try {
            loginDao = new LoginDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Login dbUser = loginDao.validateUser(id, username, password);
            if (dbUser != null) {
                HttpSession session = req.getSession();
                session.setAttribute("login", dbUser);
                switch (userType) {
                    case "admin":
                        req.getRequestDispatcher("Admin.html").forward(req, resp);
                        break;
                    case "employee":
                        req.getRequestDispatcher("Employee.html").forward(req, resp);
                        break;
                    case "user":
                        req.getRequestDispatcher("User.html").forward(req, resp);
                        break;
                    default:
                        resp.sendRedirect("LoginForm.jsp");
                        break;
                }
            } else {
                resp.sendRedirect("LoginForm.jsp?error=invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("LoginForm.jsp?error=db");
        }
    }
}