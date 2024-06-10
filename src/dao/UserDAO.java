package src.dao;

import src.config.Database;
import src.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    Connection connection = Database.getConnection();

    private final String ADD_USER_SQL = "INSERT INTO User (email, password, role) VALUES (?, ?, ?)";

    public void addUser(User user){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(ADD_USER_SQL);
            sqlScript.setString(1, user.getEmail());
            sqlScript.setString(2, user.getPassword());
            sqlScript.setString(3, user.getRole());
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        try {
            PreparedStatement sqlScript = connection.prepareStatement("UPDATE User SET email = ?, password = ?, role = ? WHERE id = ?");
            sqlScript.setString(1, user.getEmail());
            sqlScript.setString(2, user.getPassword());
            sqlScript.setString(3, user.getRole());
            sqlScript.setInt(4, user.getId());
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email){
        User user = null;
        try {
            PreparedStatement sqlScript = connection.prepareStatement("SELECT * FROM User WHERE email = ?");
            sqlScript.setString(1, email);
            ResultSet result = sqlScript.executeQuery();
            if(result.next()){
                 user = new User(
                    result.getInt("id"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(int id){
        try {
            PreparedStatement sqlScript = connection.prepareStatement("DELETE FROM User WHERE id = ?");
            sqlScript.setInt(1, id);
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
