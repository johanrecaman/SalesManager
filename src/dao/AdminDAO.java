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

    private final String READ_ADMIN_BY_ID_SQL = "SELECT * FROM Admin WHERE id = ?";
    private final String UPDATE_ADMIN_SQL = "UPDATE Admin SET name = ?, email = ?, password = ? WHERE id = ?";
    private final String DELETE_ADMIN_SQL = "DELETE FROM Admin WHERE id = ?";

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

    public Admin getAdminById(int id){
        Admin admin = null;
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_ADMIN_BY_ID_SQL);
            sqlScript.setInt(1, id);
            ResultSet result = sqlScript.executeQuery();

            if(result.next()){
                admin = new Admin(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void updateAdmin(Admin admin){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(UPDATE_ADMIN_SQL);
            sqlScript.setString(1, admin.getName());
            sqlScript.setString(2, admin.getEmail());
            sqlScript.setString(3, admin.getPassword());
            sqlScript.setInt(4, admin.getId());
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteAdmin(int id){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(DELETE_ADMIN_SQL);
            sqlScript.setInt(1, id);
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}