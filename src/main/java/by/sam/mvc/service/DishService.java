package by.sam.mvc.service;

import by.sam.mvc.models.menu.Dish;

public interface DishService {
    void add(Dish dish);
    void delete(int id);
    void update(Dish dish);
    void read(int id);
}
