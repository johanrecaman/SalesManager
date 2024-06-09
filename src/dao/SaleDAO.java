package src.dao;

import src.config.Database;
import src.models.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
