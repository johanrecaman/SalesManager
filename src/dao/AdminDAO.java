package src.dao;

import src.config.Database;
import src.models.Admin;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {
    Connection connection = Database.getConnection();

    private final String ADD_ADMIN_SQL = "INSERT INTO Admin (name, email, password) VALUES (?, ?, ?)";
    private final String READ_ADMINS_SQL = "SELECT * FROM Admin";

    public void addAdmin(Admin admin){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(ADD_ADMIN_SQL);
            sqlScript.setString(1, admin.getName());
            sqlScript.setString(2, admin.getEmail());
            sqlScript.setString(3, admin.getPassword());
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Admin> getAdmins(){
        List<Admin> admins = new ArrayList<>();
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_ADMINS_SQL);
            ResultSet result = sqlScript.executeQuery();

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