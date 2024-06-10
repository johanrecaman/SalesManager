package src.controllers;

import src.dao.UserDAO;
import src.models.User;

public class LoginController {
    public String Authenticate(String email, String password) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);
        
        if (user == null) {
            System.out.println("User not found");
            return "";
        }
        
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password");
            return "";
        }
        return user.getRole();
    }
}
