package by.sam.mvc.controllers;

import by.sam.mvc.models.menu.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishCreationDot {
    private List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
