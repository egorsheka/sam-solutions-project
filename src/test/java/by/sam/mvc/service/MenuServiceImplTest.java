package by.sam.mvc.service;


import by.sam.mvc.configuration.ThymeleafConfig;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.service.menu.impl.MenuServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThymeleafConfig.class})
@WebAppConfiguration
public class MenuServiceImplTest {

    @InjectMocks
    MenuServiceImpl menuService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);


    }


    @Test
    public void filterMenuByCuisine() {

        List<Menu> menuList = new ArrayList<>(Arrays.asList(new Menu(), new Menu(), new Menu(), new Menu(),
                new Menu(), new Menu(), new Menu(), new Menu()));
        menuList.get(0).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Korea")))));
        menuList.get(1).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("England")))));
        menuList.get(2).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Russia")))));
        menuList.get(3).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Taiwan")))));
        menuList.get(4).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Sicily")))));
        menuList.get(5).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Georgia")))));
        menuList.get(6).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Austria")))));
        menuList.get(7).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Belarus")))));

        List<Cuisine> cuisineList = new ArrayList<>(Arrays.asList(new Cuisine("Korea"), new Cuisine(), new Cuisine("Taiwan"),
                new Cuisine("Georgia"), new Cuisine()));

        List<Menu> expectList = new ArrayList<>(Arrays.asList(new Menu(), new Menu(), new Menu()));
        expectList.get(0).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Korea")))));
        expectList.get(1).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Taiwan")))));
        expectList.get(2).setDishes(new ArrayList<>(Arrays.asList(new Dish("", null, new Cuisine("Georgia")))));


        assertEquals(expectList, menuService.filterMenuByCuisine(menuList, cuisineList));
    }


}
