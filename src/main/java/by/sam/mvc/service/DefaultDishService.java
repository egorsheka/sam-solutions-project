package by.sam.mvc.service;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.repository.menu.DishRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultDishService implements DishService {

    private DishRepository repository;

    public DefaultDishService(DishRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {

    }

    @Override
    public void add(Dish dish) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Dish dish) {

    }

    @Override
    public void read(int id) {

    }




}
