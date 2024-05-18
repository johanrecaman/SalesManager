package src.app;
import src.view.Menu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in); 
       Menu menu = new Menu();

       while (true) {
           menu.showMenu();
           int choice = scanner.nextInt();
           
            switch (choice) {
            case 1:
                    menu.showAddMenu();
                    choice = scanner.nextInt();
                    break;
            case 2:
                    menu.showListMenu();
                    choice = scanner.nextInt();
                    break;
            case 3:
                    System.out.println("Daily report");
                    choice = scanner.nextInt();
                    break;
            case 4:
                    System.out.println("logout");
                    scanner.close();
                    System.exit(0);
                    break;
            default:
                    System.out.println("Invalid option");
                    break;
            }
       }
    }
}
