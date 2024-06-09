package src.dao;

import src.config.Database;
import src.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAO {
    private static final String ADD_CUSTOMER_SQL = 
    "INSERT INTO Customer (name, lastname, email, password, birthDate, phone, cpf, city, state, country, address, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Connection connection = Database.getConnection();
    
    public void addCustomer(Customer customer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER_SQL);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(customer.getBirthDate()));
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getCpf());
            preparedStatement.setString(8, customer.getCity());
            preparedStatement.setString(9, customer.getState());
            preparedStatement.setString(10, customer.getCountry());
            preparedStatement.setString(11, customer.getAddress());
            preparedStatement.setDate(12, java.sql.Date.valueOf(customer.getRegistrationDate()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}