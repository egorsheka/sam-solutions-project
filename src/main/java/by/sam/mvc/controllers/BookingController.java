package by.sam.mvc.controllers;

import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

    private CookService cookService;
    private MenuService menuService;
    private DistrictService districtService;

    public BookingController(CookService cookService, MenuService menuService, DistrictService districtService) {
        this.cookService = cookService;
        this.menuService = menuService;
        this.districtService = districtService;
    }

    @PostMapping(value = "/viewMenu")
    public String viewMenu(@ModelAttribute OrderDto order, Model model) {
        model.addAttribute("menuList",  cookService.findAllMenuByOrder(order));
        model.addAttribute("order",  order);
        model.addAttribute("orderDto",  new OrderDto());
        return "booking/viewMenu";
    }

    @PostMapping(value = "/selectMenu")
    public String selectMenu(@ModelAttribute OrderDto order, Model model) {
        District district = districtService.read(order.getDistrict().getId());
        order.getDistrict().setName(district.getName());
        order.setTown(district.getTown());
        model.addAttribute("menu",  menuService.read(order.getMenuId()));
        model.addAttribute("order",  order);
        model.addAttribute("orderDto",  new OrderDto());
        return "booking/bookMenu";
    }

    @PostMapping(value = "/bookMenu")
    public String bookMenu(@ModelAttribute OrderDto order, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        District district = districtService.read(order.getDistrict().getId());
        order.getDistrict().setName(district.getName());
        order.setTown(district.getTown());
        model.addAttribute("order",  order);
        model.addAttribute("orderDto",  new OrderDto());
        return "booking/confirmMenu";
    }

    @PostMapping(value = "/confirmMenu")
    public String confirmMenu(@ModelAttribute Menu menu, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        // model.addAttribute("menu",  menuService.read(menu.getId()));
        return "login";
    }

}
