package src.controller;
import src.view.Menu;
import java.util.Scanner;

public class MenuController {
    Scanner scanner = new Scanner(System.in); 
    Menu menu = new Menu();

    public void run(){

        while (true) {
            menu.showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleAddMenu();
                    break;
                case 2:
                    handleListMenu();
                    break;
                case 3:
                    handleDailyReport();
                    break;
                case 4:
                    handleLogout();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }     
    }
   } 
    public int getUserChoice(){
        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Invalid option");
            scanner.nextLine();
        }
        return choice;
    }
    public void handleAddMenu(){
       menu.showAddMenu();
       int choice = getUserChoice();
    }
    public void handleListMenu(){
       menu.showListMenu();
       int choice = getUserChoice();
    }
    public void handleDailyReport(){
        System.out.println("Daily report");
    }
    public void handleLogout(){
        System.out.println("logout");
        scanner.close();
        System.exit(0);
    }
}
