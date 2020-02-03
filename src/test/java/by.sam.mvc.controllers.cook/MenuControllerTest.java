package by.sam.mvc.controllers.cook;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.DishType;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.service.menu.CuisineService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    private WebApplicationContext wac;

    @Autowired
    FilterChainProxy springSecurityFilterChain;



    //todo либо без мокито, либо не работает аунтификация.
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        //не работает аунтификация
        MenuController controller = new MenuController(menuService, cookService, cuisineService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .apply(springSecurity(springSecurityFilterChain))
            .setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver())
            .build();



        //не работает мокито
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();


    }

    @Test
    @WithMockUser(username = "egorsheka@gmail.com")
    public void getCookMenu_ShouldAddMenuListToModelAnd() throws Exception {
        List<Menu> menus = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("dish", DishType.APPETISER, new Cuisine("Belarus")));
        Menu menu = new Menu(1, "menu", new BigDecimal(50), dishes);

        when(menuService.read(menu.getId())).thenReturn(menu);

        mockMvc.perform(get("/menuPage")) // 1) org.springframework.web.util.NestedServletException
                .andExpect(status().isOk())
                .andExpect(model().attribute("menuList", hasSize(1)))
                .andExpect(model().attribute("menuList", hasItem(
                        allOf(
                                hasProperty("name", is("menu"))
                        )
                )));

    }


    @Test
    public void getCookMenu_ShouldAddMenuListToModelAndRenderMenuListView() throws Exception {
        List<Menu> menus = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("dish", DishType.APPETISER, new Cuisine("Belarus")));
        Menu menu = new Menu(1, "menu", new BigDecimal(50), dishes);

        when(menuService.read(menu.getId())).thenReturn(menu);

        mockMvc.perform(post("/selectMenu")
                .param("seeMore", "seeMore")
                .contentType(MediaType.ALL)
                .param("id", "1")
                .sessionAttr("editMenu", new Menu())
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("editMenu", hasProperty("name", is("menu"))));

    }


}

    //метод тестируемого контрелера
//    @GetMapping(path = "/menuPage")
//    public String getCookMenuPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
//
//        model.addAttribute("menuList",
//                cookService.read(cookService.getAuthenticationCook(currentUser).getId()).getMenu());
//        return "cook/menu/startMenu";
//    }






