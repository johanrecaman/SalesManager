package src.dao;

import src.config.Database;
import src.models.Sale;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SaleDAO {
    private static final String ADD_SALE = "INSERT INTO sales (customer_id, product_id, payment_method, installments, interest_rate, total_value, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

    Connection connection = Database.getConnection();

    public void addCustomer(Sale sale){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SALE);
            preparedStatement.setInt(1, sale.getCustomerId());
            preparedStatement.setInt(2, sale.getProductId());
            preparedStatement.setString(3, sale.getPaymentMethod());
            preparedStatement.setInt(4, sale.getInstallments());
            preparedStatement.setDouble(5, sale.getInterestRate());
            preparedStatement.setDouble(6, sale.getTotalValue());
            preparedStatement.setDouble(7, sale.getPrice());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Sale> getSales(){
        List<Sale> sales = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sales");
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Sale sale = new Sale(
                    result.getInt("id"),
                    result.getInt("customer_id"),
                    result.getInt("product_id"),
                    result.getString("payment_method"),
                    result.getInt("installments"),
                    result.getDouble("interest_rate"),
                    result.getDouble("price")
                );
                sales.add(sale);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sales;
    }
}
