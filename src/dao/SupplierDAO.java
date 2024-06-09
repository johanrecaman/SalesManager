package src.dao;

import src.config.Database;
import src.models.Supplier;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierDAO {
    Connection connection = Database.getConnection();
    
    private final String ADD_SUPPLIER_SQL = "INSERT INTO Supplier (name, email, password, companyName, taxId, phoneNumber, city, state, country, address, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String READ_SUPPLIERS_SQL = "SELECT * FROM Supplier";

    public void addSupplier(Supplier supplier){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUPPLIER_SQL);

            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getEmail());
            preparedStatement.setString(3, supplier.getPassword());
            preparedStatement.setString(4, supplier.getCompanyName());
            preparedStatement.setString(5, supplier.getTaxId());
            preparedStatement.setString(6, supplier.getPhoneNumber());
            preparedStatement.setString(7, supplier.getCity());
            preparedStatement.setString(8, supplier.getState());
            preparedStatement.setString(9, supplier.getCountry());
            preparedStatement.setString(10, supplier.getAddress());
            preparedStatement.setDate(11, java.sql.Date.valueOf(supplier.getRegistrationDate()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public List<Supplier> getSuppliers(){
        List<Supplier> suppliers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_SUPPLIERS_SQL);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Supplier supplier = new Supplier(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("companyName"),
                    result.getString("taxId"),
                    result.getString("phoneNumber"),
                    result.getString("city"),
                    result.getString("state"),
                    result.getString("country"),
                    result.getString("address"),
                    result.getDate("registrationDate").toLocalDate()
                );
                suppliers.add(supplier);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return suppliers;
    }
}
