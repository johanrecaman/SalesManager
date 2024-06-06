package src.models;

public class Product {

    private String description;
    private int quantity;
    private double price;
    private int supplierId;

   public Product(String description, int quantity, double price, int supplierId){
         this.description = description;
         this.quantity = quantity;
         this.price = price;
         this.supplierId = supplierId;
   } 

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getSupplierId() {
        return supplierId;
    }
}
