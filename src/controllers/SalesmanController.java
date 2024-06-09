package src.controllers;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

import src.views.MenuView;

import src.models.Salesman;
import src.dao.SalesmanDAO;

public class SalesmanController {
    private Salesman salesman = new Salesman(-1,"", "", "", "", LocalDate.now(), "", "", "", "", "", "", LocalDate.now());
    private SalesmanDAO salesmanDAO = new SalesmanDAO();
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
        salesmanDAO.addSalesman(salesman);
    }

    public List<Salesman> getSalesmen() {
        return salesmanDAO.getSalesmen();
    }

}
