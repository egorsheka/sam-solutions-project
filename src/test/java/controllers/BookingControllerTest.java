package controllers;

import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.controllers.client.BookingController;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Dish;
import by.sam.mvc.entity.menu.DishType;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.model.DistrictDto;
import by.sam.mvc.model.OrderDto;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.menu.CuisineService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThymeleafConfig.class})
@WebAppConfiguration
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CookService cookService;
    @Mock
    private MenuService menuService;
    @Mock
    private DistrictService districtService;
    @Mock
    private TownService townService;
    @Mock
    private ClientService clientService;
    @Mock
    private OrderService orderService;
    @Mock
    private CuisineService cuisineService;
    @Autowired
    FilterChainProxy springSecurityFilterChain;





    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        BookingController controller = new BookingController(cookService, menuService, districtService,
                townService, clientService, orderService, cuisineService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .apply(springSecurity(springSecurityFilterChain))
                .setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver())
                .build();
    }


    @Test
    public void viewMenu_ShouldAddMenuListToModel() throws Exception {

        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Bulgogi", DishType.DESSERT, new Cuisine("Venezuela")));
        dishes.add(new Dish("Roast Beef", DishType.MAIN_COURSE, new Cuisine("Sweden")));

        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu(1, "menu", new BigDecimal(90), dishes);
        menus.add(menu);

        Cook cook = new Cook();
        cook.setMenu(menus);
        cook.setId(1);

        when(cookService.findAllMenuByOrder(any())).thenReturn(menus);

        mockMvc.perform(post("/viewMenu")
                .param("order.district.id", "1")
                .param("order.date", "2019-12-25")
                .param("order.time", "22:00")
                .param("cuisineList[0].name", "Greece")
                .param("cuisineList[0].name", "Venezuela")
                .param("cuisineList[0].name", "Sweden")
                .param("cuisineList[0].name", "Russia"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("menuList", hasSize(1)))
                .andExpect(model().attribute("menuList", hasItem(
                        allOf(
                                hasProperty("name", is("menu")),
                                hasProperty("price", is(new BigDecimal(90))),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Bulgogi")),
                                                hasProperty("dishType", is(DishType.DESSERT)),
                                                hasProperty("cuisine", is(new Cuisine("Venezuela")))
                                        )
                                )),
                                hasProperty("dishes", hasItem(
                                        allOf(
                                                hasProperty("name", is("Roast Beef")),
                                                hasProperty("dishType", is(DishType.MAIN_COURSE)),
                                                hasProperty("cuisine", is(new Cuisine("Sweden")))
                                        )
                                ))
                        )
                )));
        verify(cookService, times(1)).findAllMenuByOrder(any());
        verifyNoMoreInteractions(cookService);
    }



    @Test
    public void bookMenu_UserDidNotAuthenticate_ShouldRedirectToLoginPage() throws Exception {

        mockMvc.perform(post("/bookMenu").with(authentication(null))
                .param("order.district.id", "1")
                .param("order.date", "2019-12-25")
                .param("order.time", "22:00")
                .param("cuisineList[0].name", "Greece")
                .param("cuisineList[1].name", "Venezuela")
                .param("cuisineList[2].name", "Sweden")
                .param("cuisineList[3].name", "Russia"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void bookMenu_UserAuthenticated_ViewNameShouldBeBookingConfirmMenu() throws Exception {

        mockMvc.perform(post("/bookMenu")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .param("order.district.id", "1")
                .param("order.date", "2019-12-25")
                .param("order.time", "22:00")
                .param("cuisineList[0].name", "Greece")
                .param("cuisineList[1].name", "Venezuela")
                .param("cuisineList[2].name", "Sweden")
                .param("cuisineList[3].name", "Russia"))
                .andExpect(status().isOk())
                .andExpect(view().name("booking/confirmMenu"));
    }

    @Test
    public void confirmMenu_SessionAttributeIsNotExist_ShouldRedirectToMainPage() throws Exception {

        mockMvc.perform(get("/confirmMenu")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>()))))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void confirmMenu_SessionAttributeIsExist_ViewNameShouldBe_BookingConfirmMenu() throws Exception {


        when(clientService.getAuthenticationClient(any())).thenReturn(new Client());

        OrderDto dto = new OrderDto();
        dto.setDistrict(new DistrictDto(1));
        dto.setDate(LocalDate.of(2019, 12, 25));
        dto.setTime("22:00");
        List<Cuisine> cuisines = new ArrayList<>(Arrays.asList(new Cuisine("Greece"),
        new Cuisine("Baklava"), new Cuisine("Sweden"), new Cuisine("Russia")));
        dto.setCuisineList(cuisines);
        dto.setTown(new Town(1, "Минск"));
        mockMvc.perform(get("/confirmMenu")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .sessionAttr("orderDto", dto))
                .andDo(print())
                .andExpect(view().name("booking/confirmMenu"));
    }


    @Test
    public void makeOrderWithWrongData_EmptyClientName() throws Exception {

        Client client = new Client();
        client.setId(1);
        when(clientService.getAuthenticationClient(any())).thenReturn(client);
        mockMvc.perform(post("/makeOrder")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .param("address", "Руссиянва 18, 540")
                .param("clientName", "")
                .param("clientSurName", "Бабкин")
                .param("clientMobile", "+375447161038"))
                .andExpect(view().name("redirect:/confirmMenu"));
    }


    @Test
    public void makeOrderWithWrongData_EmptyClientSurname() throws Exception {

        Client client = new Client();
        client.setId(1);
        when(clientService.getAuthenticationClient(any())).thenReturn(client);
        mockMvc.perform(post("/makeOrder")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .param("address", "Руссиянва 18, 540")
                .param("clientName", "Сергей")
                .param("clientSurName", "")
                .param("clientMobile", "+375447161038"))
                .andExpect(view().name("redirect:/confirmMenu"));
    }


    @Test
    public void makeOrderWithWrongData_EmptyClientMobile() throws Exception {

        Client client = new Client();
        client.setId(1);
        when(clientService.getAuthenticationClient(any())).thenReturn(client);
        mockMvc.perform(post("/makeOrder")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .param("address", "Руссиянва 18, 540")
                .param("clientName", "Сергей")
                .param("clientSurName", "Бабкин")
                .param("clientMobile", ""))
                .andExpect(view().name("redirect:/confirmMenu"));
    }


    @Test
    public void makeOrderWithWrongData_EmptyAddress() throws Exception {

        Client client = new Client();
        client.setId(1);
        when(clientService.getAuthenticationClient(any())).thenReturn(client);
        mockMvc.perform(post("/makeOrder")
                .with(user(new User("user","password", new ArrayList<GrantedAuthority>())))
                .param("address", "")
                .param("clientName", "Сергей")
                .param("clientSurName", "Бабкин")
                .param("clientMobile", "+375447161038"))
                .andExpect(view().name("redirect:/confirmMenu"));
    }
}
