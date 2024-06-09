package src.controllers;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.List;

import src.views.MenuView;

import src.models.Product;
import src.models.Supplier;
import src.dao.ProductDAO;

public class ProductController {
    Scanner scanner = new Scanner(System.in);
    MenuView menu = new MenuView();
    Product product = new Product(-1, "", 0, 0.0, 0);
    ProductDAO productDAO = new ProductDAO();

    public void addProduct(){

        Class<?> productClass = product.getClass();
        Field[] fields = productClass.getDeclaredFields();

        for(Field field: fields){

            field.setAccessible(true);
            boolean validInput = false;

            while(!validInput){ 
                if(field.getName().equals("id")){
                    validInput = true;
                    continue;
                }
                menu.clearScreen();
                System.out.println("Enter " + field.getName() + ": ");
                try {
                    if (field.getType() == String.class) {
                        field.set(product, scanner.nextLine());
                        validInput = true;
                    }
                    else if (field.getType() == int.class){
                        if (field.getName().equals("supplierId")) {
                            SupplierController supplierController = new SupplierController();
                            List<Supplier> suppliers = supplierController.getSuppliers();
                            
                            System.out.println("Available suppliers: ");
                            for (Supplier supplier: suppliers) {
                                System.out.println(supplier.getId() + " - " + supplier.getName());
                            }
                        }
                        field.set(product, Integer.parseInt(scanner.nextLine()));
                        validInput = true;
                    }
                    else if (field.getType() == double.class){
                        field.set(product, scanner.nextDouble());
                        validInput = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        productDAO.addProduct(product);
    }
}
