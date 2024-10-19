package com.coforge.dao;

import com.coforge.model.Login;
import com.coforge.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private Connection connection;

    public RegisterDao() throws SQLException {
        connection = DatabaseConnection.myConnection();
    }

    public boolean registerUser(Login login) throws SQLException {
        String sql = "INSERT INTO user (user_id, username, password, userType) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, login.getId());
            preparedStatement.setString(2, login.getUsername());
            preparedStatement.setString(3, login.getPassword());
            preparedStatement.setString(4, login.getUserType());

            int result = preparedStatement.executeUpdate();

            return result > 0;
        }
    }
}