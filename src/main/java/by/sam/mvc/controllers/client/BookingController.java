package by.sam.mvc.controllers.client;

import by.sam.mvc.dto.PersonDto;
import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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

    public BookingController(CookService cookService, MenuService menuService, DistrictService districtService,
                             TownService townService, ClientService clientService, OrderService orderService) {
        this.cookService = cookService;
        this.menuService = menuService;
        this.districtService = districtService;
        this.townService = townService;
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/viewMenu")
    public String viewMenu(@ModelAttribute("orderDto") OrderDto order, Model model) {
        model.addAttribute("menuList",  cookService.findAllMenuByOrder(order));
        model.addAttribute("order",  order);
        return "booking/viewMenu";
    }

    @PostMapping(value = "/selectMenu")
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
        model.addAttribute("user", new PersonDto());
        if(currentUser == null){
            return "login";
        }
        model.addAttribute("order",  order);
        model.addAttribute("user", clientService.read(clientService.getAuthenticationCook(currentUser).getId()));
        return "booking/confirmMenu";
    }

    @GetMapping(value = "/confirmMenu")
    public ModelAndView confirmMenu(@SessionAttribute(value = "orderDto", required = false) OrderDto orderDto, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (orderDto == null) {
            return new ModelAndView("redirect:/");
        }
        int id = clientService.getAuthenticationCook(currentUser).getId();
        orderDto.setClientId(id);
        model.addAttribute("user",  clientService.read(id));
        model.addAttribute("order",  orderDto);
        return new ModelAndView("booking/confirmMenu", "model", model);
    }

    @PostMapping(value = "/makeOrder")
    public String makeOrder(@ModelAttribute OrderDto order,  @AuthenticationPrincipal UserDetails currentUser) {
        if(currentUser != null){
            order.setClientId(clientService.getAuthenticationCook(currentUser).getId());
        }
        orderService.create(order);
        return "mainPage";
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
