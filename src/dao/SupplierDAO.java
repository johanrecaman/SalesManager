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
            PreparedStatement sqlScript = connection.prepareStatement(ADD_SUPPLIER_SQL);

            sqlScript.setString(1, supplier.getName());
            sqlScript.setString(2, supplier.getEmail());
            sqlScript.setString(3, supplier.getPassword());
            sqlScript.setString(4, supplier.getCompanyName());
            sqlScript.setString(5, supplier.getTaxId());
            sqlScript.setString(6, supplier.getPhoneNumber());
            sqlScript.setString(7, supplier.getCity());
            sqlScript.setString(8, supplier.getState());
            sqlScript.setString(9, supplier.getCountry());
            sqlScript.setString(10, supplier.getAddress());
            sqlScript.setDate(11, java.sql.Date.valueOf(supplier.getRegistrationDate()));
            sqlScript.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public List<Supplier> getSuppliers(){
        List<Supplier> suppliers = new ArrayList<>();
        try {
            PreparedStatement sqlScript = connection.prepareStatement(READ_SUPPLIERS_SQL);
            ResultSet result = sqlScript.executeQuery();

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
