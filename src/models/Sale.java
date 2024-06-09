package src.models;

public class Sale {
    private int id;
    private int customerId;
    private int productId;
    private String paymentMethod;
    private int installments;
    private double interestRate;
    private double totalValue;
    private double price;

    public Sale(int id, int customerId, int productId, String paymentMethod, int installments, double interestRate, double price) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.paymentMethod = paymentMethod;
        this.installments = installments;
        this.interestRate = interestRate;
        this.totalValue = calculateTotalValue();
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getInstallments() {
        return installments;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double calculateTotalValue() {
        double totalValue = price * Math.pow(1 + (interestRate / 100), installments);
        return totalValue;
    }
}
