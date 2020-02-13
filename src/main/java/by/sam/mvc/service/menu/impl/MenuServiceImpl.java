package by.sam.mvc.service.menu.impl;


import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.repository.menu.MenuRepository;
import by.sam.mvc.service.menu.DishService;
import by.sam.mvc.service.menu.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final DishService dishService;



    public MenuServiceImpl(MenuRepository menuRepository, DishService dishService) {
        this.menuRepository = menuRepository;
        this.dishService = dishService;

    }


    @Transactional
    @Override
    public void create(Menu menu) {
        for (Dish dish : menu.getDishes()) {
            dishService.create(dish);
        }
        menuRepository.create(menu);

    }

    @Transactional
    @Override
    public Menu read(int id) {
        return menuRepository.read(id);
    }

    @Override
    public List<Menu> filterMenuByCuisine(List<Menu> menus, List<Cuisine> cuisines) {
        if (cuisines == null) {
            return null;
        }
        List<Cuisine> cuisinesWithNull = new ArrayList<>(cuisines);
        for (Cuisine cuisine : cuisinesWithNull) {
            if (cuisine.getName() == null) {
                cuisines.remove(cuisine);
            }
        }
        if (cuisines.isEmpty()) {
            return null;
        }

        List<Menu> orderMenu = new ArrayList<>();
        for (Menu menu : menus) {
            nextMenu:
            for (Dish dish : menu.getDishes()) {
                for (Cuisine cuisine : cuisines) {
                    if (dish.getCuisine().getName().equals(cuisine.getName())) {
                        orderMenu.add(menu);
                        break nextMenu;
                    }
                }
            }
        }
        return orderMenu;
    }


    @Transactional
    @Override
    public void update(Menu menu) {
        Menu updateMenu = menuRepository.read(menu.getId());

        updateMenu.setName(menu.getName());
        updateMenu.setPrice(menu.getPrice());
        updateMenu.setDishes(menu.getDishes());


        for (Dish dish : menu.getDishes()) {
            if (dish.getId() == 0) {
                dishService.create(dish);
            } else {
                dishService.update(dish);
            }
        }
        menuRepository.update(menu);
    }


    @Transactional
    @Override
    public void delete(int id) {
        menuRepository.delete(id);
    }

}
