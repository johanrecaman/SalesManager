package src.dao;

import src.config.Database;
import src.models.Product;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {
     Connection connection = Database.getConnection();

     private final String ADD_PRODUCT_SQL = "INSERT INTO Product (description, quantity, price, supplier_id) VALUES (?, ?, ?, ?)";
     private final String READ_PRODUCT_SQL = "SELECT * FROM Product";
   
     private final String READ_PRODUCT_BY_ID_SQL = "SELECT * FROM Product WHERE id = ?";
     private final String UPDATE_PRODUCT_SQL = "UPDATE Product SET description = ?, quantity = ?, price = ?, supplier_id = ? WHERE id = ?";
     private final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE id = ?";

    public void addProduct(Product product){
         try {
              PreparedStatement sqlScript = connection.prepareStatement(ADD_PRODUCT_SQL);
              sqlScript.setString(1, product.getDescription());
              sqlScript.setInt(2, product.getQuantity());
              sqlScript.setDouble(3, product.getPrice());
              sqlScript.setInt(4, product.getSupplierId());
              sqlScript.executeUpdate();
         } catch (Exception e) {
              e.printStackTrace();
         }
    }

     public List<Product> getProducts(){
          List<Product> products = new ArrayList<>();
          try {
               PreparedStatement sqlScript = connection.prepareStatement(READ_PRODUCT_SQL);
               ResultSet result = sqlScript.executeQuery();

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

     public Product getProductById(int id){
          Product product = null;
          try {
               PreparedStatement sqlScript = connection.prepareStatement(READ_PRODUCT_BY_ID_SQL);
               sqlScript.setInt(1, id);
               ResultSet result = sqlScript.executeQuery();

               if(result.next()){
                    product = new Product(
                         result.getInt("id"),
                         result.getString("description"),
                         result.getInt("quantity"),
                         result.getDouble("price"),
                         result.getInt("supplier_id")
                    );
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
          return product;
     }

     public void updateProduct(Product product){
          try {
               PreparedStatement sqlScript = connection.prepareStatement(UPDATE_PRODUCT_SQL);
               sqlScript.setString(1, product.getDescription());
               sqlScript.setInt(2, product.getQuantity());
               sqlScript.setDouble(3, product.getPrice());
               sqlScript.setInt(4, product.getSupplierId());
               sqlScript.setInt(5, product.getId());
               sqlScript.executeUpdate();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public void deleteProduct(int id){
          try {
               PreparedStatement sqlScript = connection.prepareStatement(DELETE_PRODUCT_SQL);
               sqlScript.setInt(1, id);
               sqlScript.executeUpdate();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
