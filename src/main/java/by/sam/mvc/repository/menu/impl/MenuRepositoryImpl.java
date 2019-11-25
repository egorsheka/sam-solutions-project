package by.sam.mvc.repository.menu.impl;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.repository.menu.DishRepository;
import by.sam.mvc.repository.menu.MenuRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager manager;

    private final DishRepository dishRepository;

    //SQL
    private static String FIND_ALL_QUERY = "SELECT * FROM menus";

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



    //TODO check fetch = FetchType.EAGER or to understand Hibernate.initialize(menu.getDishes());

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

    public List<Menu> findAll() {
        return manager.createNativeQuery(FIND_ALL_QUERY, Menu.class).getResultList();
    }
}