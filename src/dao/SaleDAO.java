package src.dao;

import src.config.Database;
import src.models.Sale;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SaleDAO {
    Connection connection = Database.getConnection();

    private final String ADD_SALE = "INSERT INTO Sale (customer_id, product_id, payment_method, installments, interest_rate, total_value, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String READ_SALES = "SELECT * FROM Sale";

    public void addSale(Sale sale){
        try{
            PreparedStatement sqlScript = connection.prepareStatement(ADD_SALE);
            sqlScript.setInt(1, sale.getCustomerId());
            sqlScript.setInt(2, sale.getProductId());
            sqlScript.setString(3, sale.getPaymentMethod());
            sqlScript.setInt(4, sale.getInstallments());
            sqlScript.setDouble(5, sale.getInterestRate());
            sqlScript.setDouble(6, sale.getTotalValue());
            sqlScript.setDouble(7, sale.getPrice());
            sqlScript.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Sale> getSales(){
        List<Sale> sales = new ArrayList<>();
        try{
            PreparedStatement sqlScript = connection.prepareStatement(READ_SALES);
            ResultSet result = sqlScript.executeQuery();

            while(result.next()){
                Sale sale = new Sale(
                    result.getInt("id"),
                    result.getInt("customer_id"),
                    result.getInt("product_id"),
                    result.getString("payment_method"),
                    result.getInt("installments"),
                    result.getInt("interest_rate"),
                    result.getDouble("price"),
                    result.getDouble("total_value")
                );
                sales.add(sale);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sales;
    }
}
