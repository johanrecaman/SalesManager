package src.controllers;
import src.views.MenuView;

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
                System.out.println("Invalidi option");
                break;
        }
    }

    private void listOptions() {
        menu.showListOptions();
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                List<Salesman> salesmen = salesmanController.getSalesmen();
                for (Salesman salesman : salesmen) {
                    System.out.println(salesman.getId() + " - " + salesman.getName());
                }
                break;
            case 2:
                List<Customer> customers = customerController.getCustomers();
                for (Customer customer : customers) {
                    System.out.println(customer.getId() + " - " + customer.getName());
                }
                break;
            case 3:
                List<Supplier> suppliers = supplierController.getSuppliers();
                for (Supplier supplier : suppliers) {
                    System.out.println(supplier.getId() + " - " + supplier.getName());
                }
                break;
            case 4:
                List<Product> products = productController.getProducts();
                for (Product product : products) {
                    System.out.println(product.getId() + " - " + product.getDescription());
                }
                break;
            case 5:
                List<Sale> sales = saleController.getSales();
                for (Sale sale : sales) {
                    System.out.println(sale.getId() + " - " + sale.getPaymentMethod());
                }
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
