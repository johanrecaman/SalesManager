package src.dao;

import src.config.Database;
import src.models.Salesman;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SalesmanDAO {
    private Connection connection = Database.getConnection();
    private final String ADD_SALESMAN_SQL = "INSERT INTO Salesman (name, lastname, email, password, birth_date, phone, cpf, city, state, country, address, registration_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void addSalesman(Salesman salesman){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SALESMAN_SQL);
            preparedStatement.setString(1, salesman.getName());
            preparedStatement.setString(2, salesman.getLastname());
            preparedStatement.setString(3, salesman.getEmail());
            preparedStatement.setString(4, salesman.getPassword());
            preparedStatement.setDate(5, java.sql.Date.valueOf(salesman.getBirthDate()));
            preparedStatement.setString(6, salesman.getPhone());
            preparedStatement.setString(7, salesman.getCpf());
            preparedStatement.setString(8, salesman.getCity());
            preparedStatement.setString(9, salesman.getState());
            preparedStatement.setString(10, salesman.getCountry());
            preparedStatement.setString(11, salesman.getAddress());
            preparedStatement.setDate(12, java.sql.Date.valueOf(salesman.getRegistrationDate()));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
