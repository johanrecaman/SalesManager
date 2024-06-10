package src.controllers;

import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import src.views.MenuView;

import src.models.Customer;
import src.dao.CustomerDAO;

public class CustomerController {
   private Scanner scanner = new Scanner(System.in);
   private Customer customer = new Customer(-1,"", "", "", "", LocalDate.now(), "", "", "", "", "","", LocalDate.now());
   private CustomerDAO customerDAO = new CustomerDAO();
   private MenuView menu = new MenuView();
   
   public void addCustomer(){

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
                            menu.clearScreen();
                            System.out.println("Enter date format (dd-MM-yyyy):");
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            field.set(customer, date);
                            validInput = true;
                       }
                       catch(Exception e){
                           System.out.println("Invalid date format.");
                       }
                   }
                } catch (Exception e) {
                   e.printStackTrace();
                }
           }
       }
       customerDAO.addCustomer(customer);
   }

   public List<Customer> getCustomers(){
       return customerDAO.getCustomers();
   }
}
