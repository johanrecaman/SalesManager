package src.views;

import java.util.Scanner;

import src.models.DailyReport;

public class MenuView {

       private Scanner scanner = new Scanner(System.in);

       public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
       }

       public void showHomeOptions(){
          clearScreen();
          System.out.println("Add [1]");
          System.out.println("List [2]");
          System.out.println("Daily report [3]");
          System.out.println("logout [4]");
       }
       public void showInvalidOption(){
          clearScreen();
          System.out.println("Invalid option");
       }

       public void showAddOptions(){
          clearScreen(); 
          System.out.println("Add admin [1]");
          System.out.println("Add salesman [2]");
          System.out.println("Add customer [3]");
          System.out.println("Add supplier [4]");
          System.out.println("Add product [5]");
          System.out.println("Add sale [6]");
          System.out.println("Exit [7]");
   }
       public void showListOptions(){
          clearScreen();
          System.out.println("List all salesmen [1]");
          System.out.println("List all customers [2]");
          System.out.println("List all suppliers [3]");
          System.out.println("List all products [4]");
          System.out.println("List all sales [5]");
          System.out.println("Exit [6]");
       }

       public void showEditOptions(){
               clearScreen();
               System.out.println("Edit [1]");
               System.out.println("Delete [2]");
       }

       public void showExit(){
              System.out.println("Press enter to exit...");
       }

       public void showDailyReport(DailyReport dailyReport){
              clearScreen();
              System.out.println("Daily report");

              System.out.println("Date: " + dailyReport.getDate());

              System.out.println("Total sales: " + dailyReport.getTotalSales());

              System.out.println("Total money income: " + dailyReport.getTotalMoneyIncome());

              System.out.println("Total debit income: " + dailyReport.getTotalDebitIncome());

              System.out.println("Total income: " + dailyReport.getTotalIncome());

              showExit();
       }

        public void showLogout(){
          clearScreen();
          System.out.println("logging out...");
       }
}