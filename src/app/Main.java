package src.app;
import src.controllers.MenuController;

public class Main {

    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        menuController.run();
    }
}