package com.coforge.controller;

import com.coforge.dao.RegisterDao;
import com.coforge.model.Login;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        Login login = new Login();
        login.setId(id);
        login.setUsername(username);
        login.setPassword(password);
        login.setUserType(userType);

        try {
            RegisterDao registerDao = new RegisterDao();
            boolean isUserRegistered = registerDao.registerUser(login);
            if (isUserRegistered) {
                req.setAttribute("message", "Registration Successful!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("message", "Registration Failed!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("message", "Database Error: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, resp);
        }
    }
}