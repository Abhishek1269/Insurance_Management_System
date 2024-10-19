package com.coforge.servlet;

import com.coforge.dao.EmployeeDao;
import com.coforge.exception.LoginException;
import com.coforge.model.Employee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//@WebServlet("/EmployeeServlet")
@WebServlet(urlPatterns = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        try {
            employeeDao=new EmployeeDao();
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
                    employeeDao.getAllPlan().forEach(a-> out.println("<br>"+a.getInsuranceType()+"\t"+a.getInsurancePlan()+"\t"+a.getInsurancePrize()));
                    break;
                case "addemp":
                    Employee employee=new Employee();
                    employee.setInsuranceType(req.getParameter("insuranceType"));
                    employee.setInsurancePlan(req.getParameter("insurancePlan"));
                    employee.setInsurancePrize(req.getParameter("insurancePrize"));
                    employeeDao.addUser(employee);
                    out.println("<br>Insurance added");
                    break;

                default:
                    out.println("wrong selection");
            }}catch (LoginException|SQLException e){
            out.println(e.getMessage());
        }
    }
}