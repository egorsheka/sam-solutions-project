package by.sam.mvc.config;

import by.sam.mvc.models.menu.DishType;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.repository.menu.DishConnector;

public class Main {
    public static void main(String[] args) {

        System.out.println(DishConnector.getDishDao().findAll());
    }
}
