package by.sam.mvc.repository.menu.impl;

import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.repository.menu.DishRepository;
import by.sam.mvc.repository.menu.MenuRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager manager;

    private final DishRepository dishRepository;



    public MenuRepositoryImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }



    @Override
    public void create(Menu menu){
        for(Dish dish: menu.getDishes()){
            dishRepository.create(dish);
        }
        manager.persist(menu);
    }



    @Override
    public Menu read(int id){
        Menu menu = manager.find(Menu.class, id);
        Hibernate.initialize(menu.getDishes());
        return menu;
    }



    @Override
    public void update(Menu menu){
        manager.merge(menu);
    }


    @Override
    public void delete(int id){
        Menu menu = manager.find(Menu.class, id);
        manager.remove(menu);
    }


}