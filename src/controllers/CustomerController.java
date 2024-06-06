package src.controllers;

import java.util.Scanner;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import src.views.MenuView;

import src.models.Customer;
import src.dao.CustomerDAO;

public class CustomerController {
   Scanner scanner = new Scanner(System.in);
   
   public void addCustomer(){
       Customer customer = new Customer("", "", "", "", LocalDate.now(), "", "", "", "", "","", LocalDate.now());
       CustomerDAO customerDAO = new CustomerDAO();
       MenuView menu = new MenuView();

       Class<?> customerClass = customer.getClass();
       Field[] fields = customerClass.getDeclaredFields();

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
                       field.set(customer, scanner.nextLine());
                       validInput = true;
                   }
                   else if (field.getType() == LocalDate.class){
                       String dateInput = scanner.nextLine();
                       try{
                           LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                           field.set(customer, date);
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
       customerDAO.addCustomer(customer);
   }

}
