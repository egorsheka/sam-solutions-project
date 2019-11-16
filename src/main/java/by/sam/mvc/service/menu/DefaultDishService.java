package by.sam.mvc.service.menu;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.repository.menu.DishRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultDishService implements DishService {

    private DishRepository repository;

    public DefaultDishService(DishRepository repository) {
        this.repository = repository;
    }



    @Override
    public void add(Dish dish) {
        repository.add(dish);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Dish dish) {
        repository.update(dish);
    }

    @Override
    public Dish read(int id) {
        return repository.read(id);
    }




}
