package src.models;

public class Product {

    private String description;
    private int quantity;
    private double price;
    private Supplier supplier;

   public Product(String description, int quantity, double price, Supplier supplier){
         this.description = description;
         this.quantity = quantity;
         this.price = price;
         this.supplier = supplier;
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

    public Supplier getSupplier() {
        return supplier;
    }
}
