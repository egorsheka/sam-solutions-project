package by.sam.mvc.repository.menu.impl;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.repository.menu.DishRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class DishRepositoryImpl implements DishRepository {


    @PersistenceContext
    private EntityManager manager;


    @Override
    public void create(Dish dish) {
        manager.persist(dish);
    }



    @Override
    public Dish read(int id){
        return manager.find(Dish.class, id);
    }


    @Override
    public void update(Dish dish){
        manager.merge(dish);
    }


    @Override
    public void delete(int id){
        Dish dish = manager.find(Dish.class, id);
        manager.remove(dish);
    }




}
