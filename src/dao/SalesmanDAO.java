package src.dao;

import src.config.Database;
import src.models.Salesman;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesmanDAO {
    private Connection connection = Database.getConnection();
    private final String ADD_SALESMAN_SQL = "INSERT INTO Salesman (name, lastname, email, password, birth_date, phone, cpf, city, state, country, address, registration_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String READ_SALESMAN_SQL = "SELECT * FROM Salesman";

    public void addSalesman(Salesman salesman){
        try {
            PreparedStatement sqlScript = connection.prepareStatement(ADD_SALESMAN_SQL);
            sqlScript.setString(1, salesman.getName());
            sqlScript.setString(2, salesman.getLastname());
            sqlScript.setString(3, salesman.getEmail());
            sqlScript.setString(4, salesman.getPassword());
            sqlScript.setDate(5, java.sql.Date.valueOf(salesman.getBirthDate()));
            sqlScript.setString(6, salesman.getPhone());
            sqlScript.setString(7, salesman.getCpf());
            sqlScript.setString(8, salesman.getCity());
            sqlScript.setString(9, salesman.getState());
            sqlScript.setString(10, salesman.getCountry());
            sqlScript.setString(11, salesman.getAddress());
            sqlScript.setDate(12, java.sql.Date.valueOf(salesman.getRegistrationDate()));
            sqlScript.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Salesman> getSalesmen (){
        List<Salesman> salesmans = new ArrayList<>();
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_SALESMAN_SQL);
            ResultSet result = sqlScript.executeQuery();

            while(result.next()){
                Salesman salesman = new Salesman(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("lastname"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("birth_date").toLocalDate(),
                    result.getString("phone"),
                    result.getString("cpf"),
                    result.getString("city"),
                    result.getString("state"),
                    result.getString("country"),
                    result.getString("address"),
                    result.getDate("registration_date").toLocalDate()
                );
                salesmans.add(salesman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
            return salesmans;
        }

}
