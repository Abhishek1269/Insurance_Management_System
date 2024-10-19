package com.coforge.servlet;

import com.coforge.dao.LoginDao;
import com.coforge.exception.LoginException;
import com.coforge.model.Login;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//@WebServlet("/EmployeeServlet")
@WebServlet(urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    LoginDao loginDao;

    @Override
    public void init() throws ServletException {
        try {
            loginDao=new LoginDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        try{
            String operation=req.getParameter("submit");
            switch (operation){
                case "allemp":
                    loginDao.getAllUser().forEach(a-> out.println("<br>"+a.getId()+"\t"+a.getUsername()+"\t"+a.getUserType()));
                    break;
                case "addemp":
                    Login login=new Login();
                    login.setId(Integer.parseInt(req.getParameter("userid")));
                    login.setUsername(req.getParameter("username"));
                    login.setUserType(req.getParameter("userType"));
                    loginDao.addUser(login);
                    out.println("<br>Record added");
                    break;
                case "searchemp":
                    out.println(loginDao.searchUser(Integer.parseInt(req.getParameter("userid"))));
                    break;
                default:
                    out.println("wrong selection");
            }}catch (LoginException|SQLException e){
            out.println(e.getMessage());
        }
    }
}