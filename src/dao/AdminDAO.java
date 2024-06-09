package src.dao;

import src.config.Database;
import src.models.Admin;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public List<Admin> getAdmins(){
        Connection connection = Database.getConnection();
        List<Admin> admins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Admin");
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Admin admin = new Admin(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password")
                );
                admins.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
    return admins;
    }
}