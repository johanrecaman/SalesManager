package src.controllers;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

import src.views.MenuView;

import src.models.Salesman;
import src.models.User;

import src.dao.SalesmanDAO;
import src.dao.UserDAO;

public class SalesmanController {
    private Salesman salesman = new Salesman(-1,"", "", "", "", LocalDate.now(), "", "", "", "", "", "", LocalDate.now());

    private SalesmanDAO salesmanDAO = new SalesmanDAO();
    private UserDAO userDAO = new UserDAO();

    private MenuView menuView = new MenuView();
    private Scanner scanner = new Scanner(System.in);

    public void addSalesman() {
        Class<?> salesmanClass = Salesman.class;
        Field[] fields = salesmanClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            boolean validInput = false;

            while (!validInput) {
                if (field.getName().equals("id")) {
                    validInput = true;
                    continue;
                }
                menuView.clearScreen();
                System.out.println("Enter " + field.getName() + ": ");
                try {
                    if (field.getType() == String.class) {
                        field.set(salesman, scanner.nextLine());
                        validInput = true;
                    } else if (field.getType() == LocalDate.class) {
                        System.out.println("Enter date format (dd-MM-yyyy):");
                        String dateInput = scanner.nextLine();
                        try {
                            LocalDate date = LocalDate.parse(dateInput, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            field.set(salesman, date);
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        User user = new User(-1, salesman.getEmail(), salesman.getPassword(), "salesman");

        salesmanDAO.addSalesman(salesman);
        userDAO.addUser(user);
    }

    public void updateSalesman(int id){
        Salesman salesman = salesmanDAO.getSalesman(id);

        if(salesman == null){
            System.out.println("Salesman not found");
            return;
        }

        User user = userDAO.getUserByEmail(salesman.getEmail());

        Class<?> salesmanClass = Salesman.class;
        Field[] fields = salesmanClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            boolean validInput = false;

            while (!validInput) {
                if (field.getName().equals("id")) {
                    validInput = true;
                    continue;
                }
                menuView.clearScreen();
                try {
                    System.out.println("Enter " + field.getName() + " [" + field.get(salesman) + "]: ");
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    if (field.getType() == String.class) {
                        String input = scanner.nextLine();
                        if(!input.isEmpty()){
                            field.set(salesman, input);
                        }
                        validInput = true;
                    } else if (field.getType() == LocalDate.class) {
                        String dateInput = scanner.nextLine();
                        if(!dateInput.isEmpty()){
                            try {
                                LocalDate date = LocalDate.parse(dateInput, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                field.set(salesman, date);
                                validInput = true;
                            } catch (Exception e) {
                                System.out.println("Invalid date format. Please use yyyy-MM-dd");
                            }
                        } else {
                            validInput = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        user.setEmail(salesman.getEmail());
        user.setPassword(salesman.getPassword());

        userDAO.updateUser(user);
        salesmanDAO.updateSalesman(salesman);
    }

    public List<Salesman> getSalesmen() {
        return salesmanDAO.getSalesmen();
    }

    public void deleteSalesman(int id){
        salesmanDAO.deleteSalesman(id);
    }

}
