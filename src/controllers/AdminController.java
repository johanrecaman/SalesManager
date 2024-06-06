package src.controllers;

import java.lang.reflect.Field;
import java.util.Scanner;

import src.views.MenuView;

import src.models.Admin;
import src.dao.AdminDAO;

public class AdminController {
    Scanner scanner = new Scanner(System.in);

    public void addAdmin(){
        Admin admin = new Admin("", "", "");
        AdminDAO adminDAO = new AdminDAO();
        MenuView menu = new MenuView();

        Class<?> adminClass = admin.getClass();
        Field[] fields = adminClass.getDeclaredFields();

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
                    field.set(admin, scanner.nextLine());
                    validInput = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        adminDAO.addAdmin(admin);
    } 
}
