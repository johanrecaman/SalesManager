package src.models;

import java.time.LocalDate;

public class DailyReport {
    private int totalSales;
    private double totalMoneyIncome;
    private double totalDebitIncome;
    private double totalIncome;
    private LocalDate date;
    
    public DailyReport(int totalSales, double totalMoneyIncome, double totalDebitIncome, double totalIncome, LocalDate date) {
        this.totalSales = totalSales;
        this.totalMoneyIncome = totalMoneyIncome;
        this.totalDebitIncome = totalDebitIncome;
        this.totalIncome = totalIncome;
        this.date = date;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public double getTotalMoneyIncome() {
        return totalMoneyIncome;
    }

    public double getTotalDebitIncome() {
        return totalDebitIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotalMoneyIncome(double totalMoneyIncome){
        this.totalMoneyIncome = totalMoneyIncome;
    }

    public void setTotalDebitIncome(double totalDebitIncome){
        this.totalDebitIncome = totalDebitIncome;
    }

    public void setTotalIncome(double totalIncome){
        this.totalIncome = totalIncome;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }
}
