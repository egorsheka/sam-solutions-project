package by.sam.mvc.repository.menu.impl;
import by.sam.mvc.models.menu.*;

import by.sam.mvc.repository.menu.DishRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


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
