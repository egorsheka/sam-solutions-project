package controllers;


import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.controllers.cook.MenuController;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.DishType;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.service.menu.CuisineService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = {ThymeleafConfig.class})
    @WebAppConfiguration
    public class MenuControllerTest {

        private MockMvc mockMvc;

        @Mock
        private MenuService menuService;
        @Mock
        private CuisineService cuisineService;
        @Mock
        private CookService cookService;

        @Autowired
        FilterChainProxy springSecurityFilterChain;


        @Before
        public void setup() {
            MockitoAnnotations.initMocks(this);


            MenuController controller = new MenuController(menuService, cookService, cuisineService);

            mockMvc = MockMvcBuilders.standaloneSetup(controller)
                    .apply(springSecurity(springSecurityFilterChain))
                    .setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver())
                    .build();


        }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void getCookMenu_ShouldAddMenuListToModel() throws Exception {

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Baklava", DishType.DESSERT, new Cuisine("Belarus")));
        dishes.add(new Dish("Blini", DishType.MAIN_COURSE, new Cuisine("Italy")));

        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu(1, "menu", new BigDecimal(50), dishes);
        menus.add(menu);

        Cook cook = new Cook();
        cook.setMenu(menus);
        cook.setId(1);

        User user = new User("egorsheka@gmail.com", "", new ArrayList<>());

        when(cookService.getAuthenticationCook(user)).thenReturn(cook);

        mockMvc.perform(get("/menuPage")) // 1) org.springframework.web.util.NestedServletException
                .andExpect(status().isOk())
                .andExpect(model().attribute("menuList", hasSize(1)))
                .andExpect(model().attribute("menuList", hasItem(
                        allOf(
                                hasProperty("name", is("menu")),
                                hasProperty("price", is(new BigDecimal(50))),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Baklava")),
                                                hasProperty("dishType", is( DishType.DESSERT)),
                                                hasProperty("cuisine", is(new Cuisine("Belarus")))
                                        )
                                )),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Blini")),
                                                hasProperty("dishType", is( DishType.MAIN_COURSE)),
                                                hasProperty("cuisine", is(new Cuisine("Italy")))
                                        )
                                ))
                        )
                )));
        verify(cookService, times(1)).getAuthenticationCook(user);
        verifyNoMoreInteractions(cookService);

    }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void getViewMoreMenuPage_ShouldAddMenuToModel() throws Exception {

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Kinkali", DishType.MAIN_COURSE, new Cuisine("Georgia")));

        Menu menu = new Menu(1, "Touch Of Indulgence", new BigDecimal(95), dishes);

        when(menuService.read(anyInt())).thenReturn(menu);

        mockMvc.perform(post("/selectMenu")
                .param("seeMore", "seeMore")
                .contentType(MediaType.ALL)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("editMenu", hasProperty("name", is("Touch Of Indulgence"))))
                .andExpect(model().attribute("editMenu",  hasProperty("price", is(new BigDecimal(95)))))
                .andExpect(model().attribute("editMenu", hasProperty("dishes", hasItem(
                        allOf(
                                hasProperty("name", is("Kinkali")),
                                hasProperty("dishType", is( DishType.MAIN_COURSE)),
                                hasProperty("cuisine", is(new Cuisine("Georgia")))
                        )
                ))));

    }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void saveNewMenuWithWrongData_NameIsEmpty() throws Exception {

        mockMvc.perform(post("/saveMenu")
                .contentType(MediaType.ALL)
                .param("saveNewMenu", "saveNewMenu")
                .param("name", "")
                .param("price", "50")
                .param("dishes[0].dishType", "APPETISER")
                .param("dishes[0].name", "Baklava")
                .param("dishes[0].id", "1")
                .param("dishes[0].cuisine.id", "1"))
                    .andExpect(view().name("redirect:/createMenu"));
    }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void saveNewMenuWithWrongData_PriceMoreThan3Digit() throws Exception {

        mockMvc.perform(post("/saveMenu")
                .contentType(MediaType.ALL)
                .param("saveNewMenu", "saveNewMenu")
                .param("name", "menu")
                .param("price", "500000")
                .param("dishes[0].dishType", "APPETISER")
                .param("dishes[0].name", "Baklava")
                .param("dishes[0].id", "1")
                .param("dishes[0].cuisine.id", "1"))
                .andExpect(view().name("redirect:/createMenu"));
    }


    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void saveNewMenuWithWrongData_EmptyDishList() throws Exception {

        mockMvc.perform(post("/saveMenu")
                .contentType(MediaType.ALL)
                .param("saveNewMenu", "saveNewMenu")
                .param("name", "menu")
                .param("price", "50"))
                .andExpect(view().name("redirect:/createMenu"));
    }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void saveNewMenu_ShouldAddMenuListToModel() throws Exception {

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Baklava", DishType.DESSERT, new Cuisine("Belarus")));
        dishes.add(new Dish("Blini", DishType.MAIN_COURSE, new Cuisine("Italy")));

        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu(1, "menu", new BigDecimal(50), dishes);
        menus.add(menu);

        Cook cook = new Cook();
        cook.setMenu(menus);
        cook.setId(1);

        User user = new User("egorsheka@gmail.com", "", new ArrayList<>());

        when(cookService.getAuthenticationCook(user)).thenReturn(cook);
        when(cookService.read(anyInt())).thenReturn(cook);

        mockMvc.perform(post("/saveMenu")
                .contentType(MediaType.ALL)
                .param("saveNewMenu", "saveNewMenu")
                .param("name", "menu")
                .param("price", "50")
                .param("dishes[0].dishType", "APPETISER")
                .param("dishes[0].name", "Baklava")
                .param("dishes[0].id", "1")
                .param("dishes[0].cuisine.id", "1")
                .sessionAttr("newMenu", new Menu()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("menuList", hasItem(
                        allOf(
                                hasProperty("name", is("menu")),
                                hasProperty("price", is(new BigDecimal(50))),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Baklava")),
                                                hasProperty("dishType", is( DishType.DESSERT)),
                                                hasProperty("cuisine", is(new Cuisine("Belarus")))
                                        )
                                )),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Blini")),
                                                hasProperty("dishType", is( DishType.MAIN_COURSE)),
                                                hasProperty("cuisine", is(new Cuisine("Italy")))
                                        )
                                ))
                        )
                )))
                .andExpect(view().name("cook/menu/startMenu"));
    }
}