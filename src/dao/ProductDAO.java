package src.dao;

import src.config.Database;
import src.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO {
   private static final String ADD_PRODUCT_SQL = "INSERT INTO Product (description, quantity, price, supplier_id) VALUES (?, ?, ?, ?)";
   
    public void addProduct(Product product){
         Connection connection = Database.getConnection();
         try {
              PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_SQL);
              preparedStatement.setString(1, product.getDescription());
              preparedStatement.setInt(2, product.getQuantity());
              preparedStatement.setDouble(3, product.getPrice());
              preparedStatement.setInt(4, product.getSupplierId());
              preparedStatement.executeUpdate();
         } catch (Exception e) {
              e.printStackTrace();
         }
    }
}
