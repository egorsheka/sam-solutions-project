package by.sam.mvc.repository.menu;
import by.sam.mvc.models.menu.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;


@Repository
public class DishRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void add(Dish dish) {

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
    public void delete(int id){
        Dish dish = manager.find(Dish.class, id);
        manager.remove(dish);
    }

    @Transient
    public void update(Dish dish){
        Dish updateDish = manager.find(Dish.class, dish.getId());

        updateDish.setName(dish.getName());
        updateDish.setDishType(dish.getDishType());

        Cuisine cuisine = dish.getCuisine();
        cuisine.addDish(updateDish);

        System.out.println(dish.getCuisine());
        updateDish.setCuisine(dish.getCuisine());

        manager.merge(updateDish);
    }


}
