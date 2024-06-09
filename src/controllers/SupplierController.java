package src.controllers;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

import src.views.MenuView;

import src.models.Supplier;
import src.dao.SupplierDAO;

public class SupplierController {
    private Scanner scanner = new Scanner(System.in);
    private Supplier supplier = new Supplier(-1, "", "", "", "", "", "", "", "", "", "", null);
    private SupplierDAO supplierDAO = new SupplierDAO();
    private MenuView menu = new MenuView();
    
    public void addSupplier(){

        Class<?> supplierClass = supplier.getClass();
        Field[] fields = supplierClass.getDeclaredFields();

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
                        field.set(supplier, scanner.nextLine());
                        validInput = true;
                    }
                    else if (field.getType() == LocalDate.class){
                        String dateInput = scanner.nextLine();
                        try{
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            field.set(supplier, date);
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
        supplierDAO.addSupplier(supplier);
    }

    public List<Supplier> getSuppliers(){
        return supplierDAO.getSuppliers();
    }
}
