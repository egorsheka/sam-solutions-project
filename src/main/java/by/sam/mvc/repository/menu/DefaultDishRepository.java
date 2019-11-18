package by.sam.mvc.repository.menu;
import by.sam.mvc.models.menu.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DefaultDishRepository implements DishRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void create(Dish dish) {

        Cuisine cuisine = dish.getCuisine();
        if(cuisine.getId() == 0){
         manager.persist(cuisine);
        }else{
          cuisine = manager.find(Cuisine.class, cuisine.getId());
        }
        cuisine.addDish(dish);
        dish.setCuisine(cuisine);
        manager.persist(cuisine);

    }


    @Transactional
    @Override
    public void delete(int id){
        Dish dish = manager.find(Dish.class, id);
        manager.remove(dish);
    }

    @Transactional
    @Override
    public Dish read(int id){
        Dish dish = manager.find(Dish.class, id);
        Cuisine cuisine = manager.find(Cuisine.class, dish.getCuisine().getId());
        dish.setCuisine(cuisine);
        return dish;
    }

    @Override
    public List<Dish> findAll() {
        return null;
    }


    @Transactional
    @Override
    public void update(Dish dish){
        Dish updateDish = manager.find(Dish.class, dish.getId());

        updateDish.setName(dish.getName());
        updateDish.setDishType(dish.getDishType());
        updateDish.setCuisine(dish.getCuisine());

        Cuisine cuisine = dish.getCuisine();

        if(cuisine.getId() == 0){
            manager.persist(cuisine);
        }else{
            cuisine = manager.find(Cuisine.class, cuisine.getId());
        }

        cuisine.addDish(updateDish);
        updateDish.setCuisine(cuisine);

        manager.merge(cuisine);
    }


}
