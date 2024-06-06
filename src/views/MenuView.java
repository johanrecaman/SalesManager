package src.views;

public class MenuView {

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

   public void showLogout(){
          clearScreen();
          System.out.println("logging out...");
   }
}