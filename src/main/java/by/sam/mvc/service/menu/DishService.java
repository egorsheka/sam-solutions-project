package by.sam.mvc.service.menu;

import by.sam.mvc.models.menu.Dish;

public interface DishService {
    void create(Dish dish);
    void delete(int id);
    void update(Dish dish);
    Dish read(int id);
}
