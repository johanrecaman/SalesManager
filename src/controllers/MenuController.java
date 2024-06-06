package src.controllers;
import src.views.MenuView;
import java.util.Scanner;

public class MenuController {
    private Scanner scanner = new Scanner(System.in);
    private MenuView menu = new MenuView();
    private AdminController adminController = new AdminController();
    private CustomerController customerController = new CustomerController();
    private ProductController productController = new ProductController();
    private SupplierController supplierController = new SupplierController();

    public void run() {
        while (true) {
            menu.showHomeOptions();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addOptions();
                    break;
                case 2:
                    listOptions();
                    break;
                case 3:
                    handleDailyReport();
                    break;
                case 4:
                    handleLogout();
                    break;
                default:
                    menu.showInvalidOption();
                    break;
            }
        }
    }

    private int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid option");
            scanner.nextLine();
        }
        return choice;
    }

    private void addOptions() {
        menu.showAddOptions();

        int choice = getUserChoice();
        switch (choice) {
            case 1:
                adminController.addAdmin();
                break;
            case 2:

                break;
            case 3:
                customerController.addCustomer(); 
                break;
            case 4:
                supplierController.addSupplier();
                break;
            case 5:
                productController.addProduct();
                break;
            case 6:
                
                break;
            case 7:
                break;
            default:
                System.out.println("Invalidi option");
                break;
        }
    }

    private void listOptions() {
        menu.showListOptions();
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:

                break;
            case 6:
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private void handleDailyReport() {
        System.out.println("Daily report");
    }

    private void handleLogout() {
        menu.showLogout();
        System.exit(0);
    }
}
