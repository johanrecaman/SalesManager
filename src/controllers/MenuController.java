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

    private AdminController adminController = new AdminController();
    private CustomerController customerController = new CustomerController();
    private ProductController productController = new ProductController();
    private SupplierController supplierController = new SupplierController();
    private SalesmanController salesmanController = new SalesmanController();
    private SaleController saleController = new SaleController();

    private Boolean isAdmin = false;

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
                if (isAdmin) {
                    adminController.addAdmin();
                }
                else{
                    menu.showPermissionDenied();
                }
                break;
            case 2:
                salesmanController.addSalesman();
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
                saleController.addSale(); 
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
        switch (choice) {
            case 1:
                listSalesman();
                String editChoice = scanner.nextLine();
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
                menu.showEditOptions();
                break;
            case 3:
                listSuppliers();
                menu.showEditOptions();
                break;
            case 4:
                listProducts();
                menu.showEditOptions();
                break;
            case 5:
                listSales();
                menu.showEditOptions();
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

        menu.clearScreen();

        for (Sale sale : sales) {
            System.out.println(sale.getId() + " - " + sale.getPaymentMethod());
        }
        menu.showExit();
    }

    private void editSalesman(String input){
        int id = Integer.parseInt(input);
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                salesmanController.updateSalesman(id);
                break;
            case 2:
                salesmanController.deleteSalesman(id);
                break;
            default:
                break;
        }
    }

    private void editCustomer(){
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                //customerController.editCustomer();
                break;
            case 2:
                //customerController.deleteCustomer();
                break;
            default:
                break;
        }
    }

    private void editSupplier(){
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                //supplierController.editSupplier();
                break;
            case 2:
                //supplierController.deleteSupplier();
                break;
            default:
                break;
        }
    }

    private void editProduct(){
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                //productController.editProduct();
                break;
            case 2:
                //productController.deleteProduct();
                break;
            default:
                break;
        }
    }

    private void handleLogout() {
        menu.showLogout();
        System.exit(0);
    }
}
