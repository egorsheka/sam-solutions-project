package by.sam.mvc.controllers.client;

import by.sam.mvc.model.PersonDto;
import by.sam.mvc.model.OrderDto;
import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.service.menu.CuisineService;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// todo null
@Controller
@SessionAttributes("orderDto")
public class BookingController {

    private CookService cookService;
    private MenuService menuService;
    private DistrictService districtService;
    private TownService townService;
    private ClientService clientService;
    private OrderService orderService;
    private CuisineService cuisineService;

    public BookingController(CookService cookService, MenuService menuService, DistrictService districtService,
                             TownService townService, ClientService clientService, OrderService orderService, CuisineService cuisineService) {
        this.cookService = cookService;
        this.menuService = menuService;
        this.districtService = districtService;
        this.townService = townService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.cuisineService = cuisineService;
    }

    @PostMapping(value = "/viewMenu")
    public String viewMenu(@ModelAttribute("orderDto") OrderDto order, Model model) {
        List<Menu> menus = cookService.findAllMenuByOrder(order);
        model.addAttribute("menuList",  menus);
        List<Cuisine> cuisines = new ArrayList<>();
        cuisines.add(new Cuisine());
        cuisines.add(new Cuisine());
        cuisines.add(new Cuisine());
        cuisines.add(new Cuisine());
        cuisines.add(new Cuisine());


        order.setCuisineList(cuisines);
        model.addAttribute("order",  order);
        model.addAttribute("allCuisines", cuisineService.findAll());
        return "booking/viewMenu";
    }

    @PostMapping(value = "/selectTheMenu")
    public String selectMenu(@ModelAttribute OrderDto order, Model model) {
        District district = districtService.read(order.getDistrict().getId());
        order.getDistrict().setName(district.getName());
        order.setTown(district.getTown());
        model.addAttribute("menu",  menuService.read(order.getMenuId()));
        model.addAttribute("order",  order);
        return "booking/bookMenu";
    }

    @PostMapping(value = "/bookMenu")
    public String bookMenu(@ModelAttribute OrderDto order, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if(currentUser == null){
            model.addAttribute("loginError", false);
            model.addAttribute("user", new PersonDto());
            return "login";
        }
        model.addAttribute("order",  order);
        return "booking/confirmMenu";
    }

    @GetMapping(value = "/confirmMenu")
    public String confirmMenu(@SessionAttribute(value = "orderDto", required = false) OrderDto orderDto, Model model,
                              @AuthenticationPrincipal UserDetails currentUser) {
        if (orderDto == null) {
            return "redirect:/";
        }
        orderDto.setClient(clientService.getAuthenticationClient(currentUser));
        model.addAttribute("order",  orderDto);
        return "booking/confirmMenu";
    }

    @PostMapping(value = "/makeOrder")
    public String makeOrder(@Valid @ModelAttribute OrderDto order, BindingResult bindingResult,
                            @AuthenticationPrincipal UserDetails currentUser, Model model){
        if(currentUser != null){
            order.setClientId(clientService.getAuthenticationClient(currentUser).getId());
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("order",  order);
            return "redirect:/confirmMenu";
        }
        orderService.create(order);
        OrderDto dto = new OrderDto();
        dto.setDate(LocalDate.now());
        model.addAttribute("order", dto);
        return "redirect:/";
    }



    @ModelAttribute("townList")
    public List<Town> getTownList() {
        return townService.findAll();
    }
    @ModelAttribute("orderDto")
    public OrderDto getDtoForm() {
        return new OrderDto();
    }
    @ModelAttribute("order")
    public OrderDto getForm() {
        return new OrderDto();
    }

}
