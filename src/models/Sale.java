package src.models;

public class Sale {
    private int id;
    private int customerId;
    private int productId;
    private String paymentMethod;
    private int installments;
    private int interestRate;
    private double price;
    private double totalValue;

    public Sale(int id, int customerId, int productId, String paymentMethod, int installments, int interestRate, double price, double totalValue) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.paymentMethod = paymentMethod;
        this.installments = installments;
        this.interestRate = interestRate;
        this.price = price;
        this.totalValue = totalValue;
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
        this.interestRate = calculateInterestRate();
        return interestRate;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalValue() {
        this.totalValue = calculateTotalValue();
        return totalValue;
    }

    private double calculateTotalValue() {
        return price + (price * calculateInterestRate() / 100);
    }

    private int calculateInterestRate(){
        if (installments > 5){
            return 5;
        }
        return 0;
    }
}
