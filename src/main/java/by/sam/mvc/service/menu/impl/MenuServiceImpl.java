package by.sam.mvc.service.menu.impl;


import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.menu.MenuRepository;
import by.sam.mvc.service.menu.DishService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    @Transactional
    @Override
    public void update(Menu menu) {

        Menu updateMenu = menuRepository.read(menu.getId());

        updateMenu.setName(menu.getName());
        updateMenu.setPrice(menu.getPrice());
        updateMenu.setLuxury(menu.getLuxury());
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


    @Override
    public void delete(int id) {
        menuRepository.delete(id);
    }



}
