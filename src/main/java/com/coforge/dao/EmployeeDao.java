package com.coforge.dao;

import com.coforge.exception.LoginException;
import com.coforge.model.Employee;
import com.coforge.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDao {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EmployeeDao() throws SQLException {
        connection = DatabaseConnection.myConnection();
    }

    public List<Employee> getAllPlan() throws SQLException, LoginException {
        statement = connection.createStatement();
        resultSet = null;
        resultSet = statement.executeQuery("select * from insurancelist");
        List<Employee> insuranceList = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setInsuranceType(resultSet.getString(1));
            employee.setInsurancePlan(resultSet.getString(2));

            employee.setInsurancePrize(resultSet.getString(3));

            insuranceList.add(employee);
        }

        if (insuranceList.isEmpty())
            throw new LoginException("Insurance list is empty");
        else
            return insuranceList;
    }

    public Employee addUser(Employee employee) throws SQLException, LoginException {
        preparedStatement = null;
        preparedStatement = connection.prepareStatement("select * from insurancelist where insurancePlan=?");
        preparedStatement.setString(1, employee.getInsurancePlan());
        resultSet = preparedStatement.executeQuery();

//        if (resultSet.next()) {
//            throw new LoginException("plan already present");
//        } else {
            preparedStatement = connection.prepareStatement("insert into insurancelist values(?,?,?)");
            preparedStatement.setString(1, employee.getInsuranceType());
            preparedStatement.setString(2, employee.getInsurancePlan());
            preparedStatement.setString(3, employee.getInsurancePrize());
            int i = preparedStatement.executeUpdate();
//            if (i == 1)
                return employee;
//        }
//        return null;
    }
}

