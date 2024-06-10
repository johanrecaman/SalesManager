package src.controllers;
import src.views.MenuView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import src.models.*;

public class MenuController {
    private Scanner scanner = new Scanner(System.in);
    private MenuView menu = new MenuView();

    private LoginController login = new LoginController();
    private AdminController adminController = new AdminController();
    private CustomerController customerController = new CustomerController();
    private ProductController productController = new ProductController();
    private SupplierController supplierController = new SupplierController();
    private SalesmanController salesmanController = new SalesmanController();
    private SaleController saleController = new SaleController();

    private Boolean isLoggedIn = false;
    private Boolean isAdmin = false;

    public void run() {
        while (!isLoggedIn) {
            System.out.println("Enter your email:");
            String email = scanner.nextLine();
            menu.clearScreen();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            String role = login.Authenticate(email, password);
            
            if (role.equals("admin")) {
                isLoggedIn = true;
                isAdmin = true;
                home();
            }
            else if (role.equals("salesman")) {
                isLoggedIn = true;
                isAdmin = false;
                home();
            }
            else{
                System.out.println("Press enter to try again...");
                scanner.nextLine();
                menu.clearScreen();
            }
        }
    }
    
    public void home(){
        while (isLoggedIn) {
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
                if (isAdmin) {
                    adminController.addAdmin();
                    scanner.nextLine();
                    menu.showExit();
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            case 2:
                salesmanController.addSalesman();
                scanner.nextLine();
                menu.showExit();
                break;
            case 3:
                customerController.addCustomer(); 
                scanner.nextLine();
                menu.showExit();
                break;
            case 4:
                supplierController.addSupplier();
                scanner.nextLine();
                menu.showExit();
                break;
            case 5:
                productController.addProduct();
                scanner.nextLine();
                menu.showExit();
                break;
            case 6:
                if (isAdmin) {
                    saleController.addSale();
                    scanner.nextLine();
                    menu.showExit(); 
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            case 7:
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private void listOptions() {
        menu.showListOptions();
        int choice = getUserChoice();
        String editChoice = "";
        switch (choice) {
            case 1:
                listSalesman();
                editChoice = scanner.nextLine();
                if(editChoice == ""){
                    break;
                }
                else{
                    menu.showEditOptions();
                    editSalesman(editChoice);
                }
                break;
            case 2:
                listCustomers();
                editChoice = scanner.nextLine();
                if (editChoice == "") {
                    break;
                }
                else{
                    menu.showEditOptions();
                    editCustomer(editChoice);
                }
                break;
            case 3:
                listSuppliers();
                editChoice = scanner.nextLine();
                if (editChoice == "") {
                    break;
                }
                else{
                    menu.showEditOptions();
                    editSupplier(editChoice);
                }
                break;
            case 4:
                listProducts();
                editChoice = scanner.nextLine();
                if (editChoice == "") {
                    break;
                }
                else{
                    menu.showEditOptions();
                    editProduct(editChoice);
                }
                break;
            case 5:
                listSales();
                scanner.nextLine();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
        
    private void handleDailyReport() {
        menu.clearScreen();
        System.out.println("Enter the date (dd-MM-yyyy):");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        DailyReport dailyReport = saleController.getDailyReport(date);
        
        menu.showDailyReport(dailyReport);
        scanner.nextLine();
        menu.showExit();
    }
        
    private void listSalesman(){
        List<Salesman> salesmen = salesmanController.getSalesmen();

        menu.clearScreen();

        System.out.println("Select a salesman:");
        for (Salesman salesman : salesmen) {
            System.out.println(salesman.getId() + " - " + salesman.getName());
        }
        menu.showExit();
    }

    private void listCustomers(){
        List<Customer> customers = customerController.getCustomers();

        menu.clearScreen();

        System.out.println("Select a customer:");
        for (Customer customer : customers) {
            System.out.println(customer.getId() + " - " + customer.getName());
        }
        menu.showExit();
    }

    private void listSuppliers(){
        List<Supplier> suppliers = supplierController.getSuppliers();

        menu.clearScreen();

        System.out.println("Select a supplier:");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.getId() + " - " + supplier.getName());
        }
        menu.showExit();
    }

    private void listProducts(){
        List<Product> products = productController.getProducts();

        menu.clearScreen();

        System.out.println("Select a product:");
        for (Product product : products) {
            System.out.println(product.getId() + " - " + product.getDescription());
        }
        menu.showExit();
    }

    private void listSales(){
        List<Sale> sales = saleController.getSales();
        List<Product> products = productController.getProducts();

        menu.clearScreen();

        for (Sale sale : sales) {
            String productName = products.get(sale.getProductId()).getDescription();

            System.out.println(sale.getId() + " - " + productName + " - " + sale.getDate());
        }
        menu.showExit();
    }

    private void editSalesman(String input){
        int id = Integer.parseInt(input);
        int choice = getUserChoice();
        menu.clearScreen();
        switch (choice) {
            case 1:
                salesmanController.updateSalesman(id);
                menu.showExit();
                scanner.nextLine();
                break;
            case 2:
                if (isAdmin) {
                    salesmanController.deleteSalesman(id);
                    menu.showExit();
                    scanner.nextLine();
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            default:
                break;
        }
    }

    private void editCustomer(String input){
        int id = Integer.parseInt(input);
        int choice = getUserChoice();
        menu.clearScreen();
        switch (choice) {
            case 1:
                customerController.updateCustomer(id);
                menu.showExit();
                scanner.nextLine();
                break;
            case 2:
                if (isAdmin) {
                    customerController.deleteCustomer(id);
                    menu.showExit();
                    scanner.nextLine();
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            default:
                break;
        }
    }

    private void editSupplier(String input){
        int id = Integer.parseInt(input);
        int choice = getUserChoice();
        menu.clearScreen();
        switch (choice) {
            case 1:
                supplierController.updateSupplier(id);
                menu.showExit();
                scanner.nextLine();
                break;
            case 2:
                if (isAdmin) {
                    supplierController.deleteSupplier(id);
                    menu.showExit();
                    scanner.nextLine();
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            default:
                break;
        }
    }

    private void editProduct(String input){
        int id = Integer.parseInt(input);
        int choice = getUserChoice();
        menu.clearScreen();
        switch (choice) {
            case 1:
                productController.updateProduct(id);
                menu.showExit();
                scanner.nextLine();
                break;
            case 2:
                if (isAdmin) {
                    productController.deleteProduct(id);
                    menu.showExit();
                    scanner.nextLine();
                }
                else{
                    menu.showPermissionDenied();
                    scanner.nextLine();
                }
                break;
            default:
                break;
        }
    }

    private void handleLogout() {
        menu.clearScreen();
        isLoggedIn = false;
    }
}
