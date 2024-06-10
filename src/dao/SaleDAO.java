package src.dao;

import src.config.Database;
import src.models.Sale;
import src.models.DailyReport;

import java.util.List;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class SaleDAO {
    Connection connection = Database.getConnection();

    private final String ADD_SALE = "INSERT INTO Sale (customer_id, product_id, payment_method, installments, interest_rate, total_value, price, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String READ_SALES = "SELECT * FROM Sale";
    private final String READ_DAILY_SALES = "SELECT * FROM Sale WHERE  = ?";

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
            sqlScript.setDate(8, java.sql.Date.valueOf(sale.getDate()));
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
                    result.getDouble("total_value"),
                    result.getDate("date").toLocalDate()
                );
                sales.add(sale);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sales;
    }

    public DailyReport getDailyReport(LocalDate date){
        DailyReport dailyReport = new DailyReport(0, 0, 0, 0, date);
        try{
            PreparedStatement sqlScript = connection.prepareStatement(READ_DAILY_SALES);
            sqlScript.setDate(1, java.sql.Date.valueOf(date));
            ResultSet result = sqlScript.executeQuery();

            int totalSales = 0;
            double totalMoneyIncome = 0;
            double totalDebitIncome = 0;
            double totalIncome = 0;

            while(result.next()){
                totalSales += 1;
                if(!result.getString("payment_method").equals("credit")){
                    totalIncome += result.getDouble("total_value");
                    if (result.getString("payment_method").equals("debit")) {
                        totalDebitIncome += result.getDouble("total_value");   
                    }
                    else {
                        totalMoneyIncome += result.getDouble("total_value");
                    }
                }   
                dailyReport.setTotalSales(totalSales);
                dailyReport.setTotalMoneyIncome(totalMoneyIncome);
                dailyReport.setTotalDebitIncome(totalDebitIncome);
                dailyReport.setTotalIncome(totalIncome);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dailyReport;
    }
}
