package src.dao;

import src.config.Database;
import src.models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminDAO {
    private final String ADD_ADMIN_SQL = "INSERT INTO Admin (name, email, password) VALUES (?, ?, ?)";

    public void addAdmin(Admin admin){
        Connection connection = Database.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN_SQL);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
