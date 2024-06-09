package src.dao;

import src.config.Database;
import src.models.Customer;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {
    Connection connection = Database.getConnection();
    private static final String ADD_CUSTOMER_SQL = "INSERT INTO Customer (name, lastname, email, password, birthDate, phone, cpf, city, state, country, address, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_CUSTOMERS_SQL = "SELECT * FROM Customer";

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

    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_CUSTOMERS_SQL);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Customer customer = new Customer(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("lastname"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("birthDate").toLocalDate(),
                    result.getString("phone"),
                    result.getString("cpf"),
                    result.getString("city"),
                    result.getString("state"),
                    result.getString("country"),
                    result.getString("address"),
                    result.getDate("registrationDate").toLocalDate()
                );
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}