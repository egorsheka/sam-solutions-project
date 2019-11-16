package by.sam.mvc.service;

import by.sam.mvc.models.menu.Cuisine;
import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
import by.sam.mvc.repository.menu.DishRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DefaultDishServiceTest {

    @Autowired
    DishRepository dishRepository;

    @Test
    public void update() {
        Cuisine cuisine = new Cuisine(1, "Austria");
        dishRepository.update(new Dish(1, "Apfelstrudel", DishType.DESSERT, cuisine));
    }

}