package src.controller;
import src.view.MenuView;
import java.util.Scanner;

public class MenuController {
    Scanner scanner = new Scanner(System.in); 
    MenuView menu = new MenuView();

    public void run(){

        while (true) {
            menu.showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    listMenu();
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
    private int getUserChoice(){
        int choice = -1;
        try{
            choice = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Invalid option");
            scanner.nextLine();
        }
        return choice;
    }
    public void addMenu(){
       menu.showAddMenu();
       int choice = getUserChoice();
       switch (choice) {
        case 1:
            System.out.println("Add admin");
            break;
        case 2:
            System.out.println("Add salesman");
            break;
        case 3:
            System.out.println("Add customer");
            break;
        case 4:
            System.out.println("Add supplier");
            break;
        case 5:
            System.out.println("Add product");
            break;
        case 6:
            System.out.println("Add sale");
            break;
        case 7:
            System.out.println("Exit");
            break;
        default:
            System.out.println("Invalid option");
            break;
       }
    }
    public void listMenu(){
       menu.showListMenu();
       int choice = getUserChoice();
         switch (choice) {
          case 1:
                System.out.println("List all salesmen");
                break;
          case 2:
                System.out.println("List all customers");
                break;
          case 3:
                System.out.println("List all suppliers");
                break;
          case 4:
                System.out.println("List all products");
                break;
          case 5:
                System.out.println("List all sales");
                break;
          case 6:
                System.out.println("Exit");
                break;
          default:
                System.out.println("Invalid option");
                break;
         }
    }
    public void handleDailyReport(){
        System.out.println("Daily report");
    }
    public void handleLogout(){
        menu.showLogout();
        scanner.close();
        System.exit(0);
    }
}
