package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DefaultMenuRepository implements MenuRepository {

    @PersistenceContext
    private EntityManager manager;

    private final DishRepository dishRepository;

    //SQL
    private static String FIND_ALL_QUERY = "SELECT * FROM menus";

    public DefaultMenuRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    @Transactional
    @Override
    public void create(Menu menu){
        for(Dish dish: menu.getDishes()){
            dishRepository.create(dish);
        }
        manager.persist(menu);
    }



    //TODO check fetch = FetchType.EAGER or to understand Hibernate.initialize(menu.getDishes());
    @Transactional
    @Override
    public Menu read(int id){
        Menu menu = manager.find(Menu.class, id);
        Hibernate.initialize(menu.getDishes());
        return menu;
    }


    @Transactional
    @Override
    public void update(Menu menu){
        Menu updateMenu = manager.find(Menu.class, menu.getId());

        updateMenu.setName(menu.getName());
        updateMenu.setPrice(menu.getPrice());
        updateMenu.setLuxury(menu.getLuxury());
        updateMenu.setDishes(menu.getDishes());

        for(Dish dish: menu.getDishes()){
            if(dish.getId() == 0){
                dishRepository.create(dish);
            }else {
                dishRepository.update(dish);
            }
        }
        manager.merge(updateMenu);
    }

    @Transactional
    @Override
    public void delete(int id){
        Menu menu = manager.find(Menu.class, id);
        manager.remove(menu);
        for(Dish dish: menu.getDishes()){
            dishRepository.delete(dish.getId());
        }
    }


    @Transactional
    @Override
    public List<Menu> findAll() {
        return manager.createNativeQuery(FIND_ALL_QUERY, Menu.class).getResultList();
    }
}