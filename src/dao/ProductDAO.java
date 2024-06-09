package src.dao;

import src.config.Database;
import src.models.Product;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

     public List<Product> getProducts(){
          Connection connection = Database.getConnection();
          List<Product> products = new ArrayList<>();
           try {
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product");
                 ResultSet result = preparedStatement.executeQuery();
     
                 while(result.next()){
                       Product product = new Product(
                         result.getInt("id"),
                         result.getString("description"),
                         result.getInt("quantity"),
                         result.getDouble("price"),
                         result.getInt("supplier_id")
                       );
                       products.add(product);
                 }
           } catch (Exception e) {
                 e.printStackTrace();
           }
           return products;
     }
}
