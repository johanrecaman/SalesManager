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

    private final String ADD_CUSTOMER_SQL = "INSERT INTO Customer (name, lastname, email, password, birthDate, phone, cpf, city, state, country, address, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String READ_CUSTOMERS_SQL = "SELECT * FROM Customer";
    
    private final String READ_CUSTOMER_BY_ID_SQL = "SELECT * FROM Customer WHERE id = ?";
    private final String UPDATE_CUSTOMER_SQL = "UPDATE Customer SET name = ?, lastname = ?, email = ?, password = ?, birthDate = ?, phone = ?, cpf = ?, city = ?, state = ?, country = ?, address = ?, registrationDate = ? WHERE id = ?";
    private final String DELETE_CUSTOMER_SQL = "DELETE FROM Customer WHERE id = ?";

    public void addCustomer(Customer customer){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(ADD_CUSTOMER_SQL);
            sqlScript.setString(1, customer.getName());
            sqlScript.setString(2, customer.getLastname());
            sqlScript.setString(3, customer.getEmail());
            sqlScript.setString(4, customer.getPassword());
            sqlScript.setDate(5, java.sql.Date.valueOf(customer.getBirthDate()));
            sqlScript.setString(6, customer.getPhone());
            sqlScript.setString(7, customer.getCpf());
            sqlScript.setString(8, customer.getCity());
            sqlScript.setString(9, customer.getState());
            sqlScript.setString(10, customer.getCountry());
            sqlScript.setString(11, customer.getAddress());
            sqlScript.setDate(12, java.sql.Date.valueOf(customer.getRegistrationDate()));
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_CUSTOMERS_SQL);
            ResultSet result = sqlScript.executeQuery();

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

    public Customer getCustomer(int id){
        Customer customer = null;
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_CUSTOMER_BY_ID_SQL);
            sqlScript.setInt(1, id);
            ResultSet result = sqlScript.executeQuery();

            if(result.next()){
                customer = new Customer(
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer customer){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
            sqlScript.setString(1, customer.getName());
            sqlScript.setString(2, customer.getLastname());
            sqlScript.setString(3, customer.getEmail());
            sqlScript.setString(4, customer.getPassword());
            sqlScript.setDate(5, java.sql.Date.valueOf(customer.getBirthDate()));
            sqlScript.setString(6, customer.getPhone());
            sqlScript.setString(7, customer.getCpf());
            sqlScript.setString(8, customer.getCity());
            sqlScript.setString(9, customer.getState());
            sqlScript.setString(10, customer.getCountry());
            sqlScript.setString(11, customer.getAddress());
            sqlScript.setDate(12, java.sql.Date.valueOf(customer.getRegistrationDate()));
            sqlScript.setInt(13, customer.getId());
            sqlScript.executeUpdate();

            System.out.println("Customer updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating customer.");
        }
    }

    public void deleteCustomer(int id){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(DELETE_CUSTOMER_SQL);
            sqlScript.setInt(1, id);
            sqlScript.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting customer.");
        }
    }
}