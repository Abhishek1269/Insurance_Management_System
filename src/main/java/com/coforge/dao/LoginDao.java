package com.coforge.dao;

import com.coforge.exception.LoginException;
import com.coforge.model.Login;
import com.coforge.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LoginDao() throws SQLException {
        connection = DatabaseConnection.myConnection();
    }

    public List<Login> getAllUser() throws SQLException, LoginException {
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from user");
        List<Login> loginList = new ArrayList<>();

        while (resultSet.next()) {
            Login login = new Login();
            login.setId(resultSet.getInt(1));
            login.setUsername(resultSet.getString(2));
            login.setUserType(resultSet.getString(3));
            login.setPassword(resultSet.getString(4));
            loginList.add(login);
        }

        if (loginList.isEmpty())
            throw new LoginException("list is empty");
        return loginList;
    }

    public Login addUser(Login login) throws SQLException, LoginException {
        preparedStatement = connection.prepareStatement("select * from user where user_id=?");
        preparedStatement.setInt(1, login.getId());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            throw new LoginException("id already present");
        } else {
            preparedStatement = connection.prepareStatement("insert into user (user_id, username, userType, password) values(?,?,?,?)");
            preparedStatement.setInt(1, login.getId());
            preparedStatement.setString(2, login.getUsername());
            preparedStatement.setString(3, login.getUserType());
            preparedStatement.setString(4, login.getPassword());
            int i = preparedStatement.executeUpdate();
            if (i == 1)
                return login;
        }
        return null;
    }

    public Login validateUser(int userid, String username, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE user_id = ? AND username = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userid);
        preparedStatement.setString(2, username);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String storedPassword = resultSet.getString("password");
            if (password.equals(storedPassword)) {
                Login login = new Login();
                login.setId(resultSet.getInt(1));
                login.setUsername(resultSet.getString(2));
                login.setUserType(resultSet.getString(3));
                return login;
            }
        }
        return null;
    }

    public Login searchUser(int userid) throws SQLException, LoginException {
        preparedStatement = connection.prepareStatement("select * from user where user_id=?");
        preparedStatement.setInt(1, userid);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Login login = new Login();
            login.setId(resultSet.getInt(1));
            login.setUsername(resultSet.getString(2));
            login.setUserType(resultSet.getString(3));
            login.setPassword(resultSet.getString(4));
            return login;
        } else {
            throw new LoginException("id not present");
        }
    }
}