package src.controllers;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import src.views.MenuView;

import src.models.Sale;
import src.models.Product;
import src.models.Customer;
import src.models.DailyReport;
import src.dao.SaleDAO;


public class SaleController {
    Scanner scanner = new Scanner(System.in);
    MenuView menu = new MenuView();

    Sale sale = new Sale(-1, -1, -1, "", 1, -1, -1, -1, LocalDate.now());
    SaleDAO saleDAO = new SaleDAO();

    public void addSale(){
            
        Class<?> saleClass = sale.getClass();
        Field[] fields = saleClass.getDeclaredFields();

        List<String> paymentMethods = List.of("Cash", "Credit Card", "Debit Card");

        List<String> skipNames = new ArrayList<>(List.of("id", "interestRate", "totalValue", "installments"));

        for(Field field: fields){
            field.setAccessible(true);
            boolean validInput = false;

            while (!validInput) {
                if (skipNames.contains(field.getName())) {
                    validInput = true;
                    continue;
                }
                menu.clearScreen();
                System.out.println("Enter " + field.getName() + ": ");
                try {
                    if (field.getName().equals("paymentMethod")) {
                        System.out.println("Available payment methods: ");

                        int i = 0;
                        for (String paymentMethod: paymentMethods) {
                            System.out.println(i + " - " + paymentMethod);
                            i++;
                        }
                        
                        int payChoice = Integer.parseInt(scanner.nextLine());

                        if(payChoice == 1){
                            skipNames.remove(3);
                        }

                        field.set(sale, paymentMethods.get(payChoice));
                        validInput = true;
                    }
                    else if (field.getType() == int.class){
                        List<Integer> ids = new ArrayList<>();                       
                        if (field.getName().equals("productId")) {
                            ProductController productController = new ProductController();
                            List<Product> products = productController.getProducts();
                            
                            System.out.println("Available products: ");
                            for (Product product: products) {
                                System.out.println(product.getId() + " - " + product.getDescription());
                                ids.add(product.getId());
                            }
                        }
                        else if (field.getName().equals("customerId")) {
                            CustomerController customerController = new CustomerController();
                            List<Customer> customers = customerController.getCustomers();
                            
                            System.out.println("Available customers: ");
                            for (Customer customer: customers) {
                                System.out.println(customer.getId() + " - " + customer.getName());
                                ids.add(customer.getId());
                            }
                            int choice = Integer.parseInt(scanner.nextLine());
                            if(ids.contains(choice)){
                                field.set(sale, choice);
                                validInput = true;
                            }
                        }
                        else{
                            field.set(sale, Integer.parseInt(scanner.nextLine()));
                            validInput = true;
                        }
                    }
                    else if (field.getType() == double.class){
                        field.set(sale, Double.parseDouble(scanner.nextLine()));
                        validInput = true;
                    }
                    else if (field.getType() == LocalDate.class){
                        menu.clearScreen();
                        System.out.println("Enter date (dd-MM-yyyy): ");
                        String dateInput = scanner.nextLine();
                        try{
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            field.set(sale, date);
                            validInput = true;
                        }
                        catch(Exception e){
                            System.out.println("Invalid date format. Please use dd-MM-yyyy");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }                
            }
        }
        saleDAO.addSale(sale);   
    }

    public void updateSale(int id){
        Sale existingSale = saleDAO.getSaleById(id);
        if (existingSale == null) {
            System.out.println("Sale not found.");
            return;
        }

        Class<?> saleClass = sale.getClass();
        Field[] fields = saleClass.getDeclaredFields();

        List<String> paymentMethods = List.of("Cash", "Credit Card", "Debit Card");

        List<String> skipNames = new ArrayList<>(List.of("id", "interestRate", "totalValue", "installments"));

        for (Field field : fields) {
            field.setAccessible(true);
            boolean validInput = false;

            while (!validInput) {
            if (skipNames.contains(field.getName())) {
                validInput = true;
                continue;
            }
            menu.clearScreen();
            System.out.println("Enter " + field.getName() + " (leave empty to skip): ");
            try {
                if (field.getName().equals("paymentMethod")) {
                System.out.println("Available payment methods: ");

                int i = 0;
                for (String paymentMethod : paymentMethods) {
                    System.out.println(i + " - " + paymentMethod);
                    i++;
                }

                int payChoice = Integer.parseInt(scanner.nextLine());

                if (payChoice == 1) {
                    skipNames.remove(3);
                }

                if (!scanner.nextLine().isEmpty()) {
                    field.set(sale, paymentMethods.get(payChoice));
                }
                validInput = true;
                } else if (field.getType() == int.class) {
                List<Integer> ids = new ArrayList<>();
                if (field.getName().equals("productId")) {
                    ProductController productController = new ProductController();
                    List<Product> products = productController.getProducts();

                    System.out.println("Available products: ");
                    for (Product product : products) {
                    System.out.println(product.getId() + " - " + product.getDescription());
                    ids.add(product.getId());
                    }
                } else if (field.getName().equals("customerId")) {
                    CustomerController customerController = new CustomerController();
                    List<Customer> customers = customerController.getCustomers();

                    System.out.println("Available customers: ");
                    for (Customer customer : customers) {
                    System.out.println(customer.getId() + " - " + customer.getName());
                    ids.add(customer.getId());
                    }
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (ids.contains(choice)) {
                    field.set(sale, choice);
                    }
                    validInput = true;
                } else {
                    String input = scanner.nextLine();
                    if (!input.isEmpty()) {
                    field.set(sale, Integer.parseInt(input));
                    }
                    validInput = true;
                }
                } else if (field.getType() == double.class) {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    field.set(sale, Double.parseDouble(input));
                }
                validInput = true;
                } else if (field.getType() == LocalDate.class) {
                menu.clearScreen();
                System.out.println("Enter date (dd-MM-yyyy) (leave empty to skip): ");
                String dateInput = scanner.nextLine();
                if (!dateInput.isEmpty()) {
                    try {
                    LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    field.set(sale, date);
                    } catch (Exception e) {
                    System.out.println("Invalid date format. Please use dd-MM-yyyy");
                    }
                }
                validInput = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }
        saleDAO.updateSale(sale);
    }

    public void deleteSale(int id){
        saleDAO.deleteSale(id);
    }

    public List<Sale> getSales(){
        return saleDAO.getSales();
    }

    public DailyReport getDailyReport(LocalDate date){
        return saleDAO.getDailyReport(date);
    }
}
