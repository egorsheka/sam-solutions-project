package by.sam.mvc.controllers.user;


import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    private CookService cookService;
    private TownService townService;
    private MenuService menuService;
    private OrderDto user;

    public MainPageController(TownService townService, MenuService menuService, CookService cookService) {
        this.cookService = cookService;
        this.townService = townService;

        this.menuService = menuService;
    }

    @GetMapping(path = "/")
    public String getMainPage(){
        return "mainPage";
    }


    @PostMapping(value = "/viewMenu")
    public String viewMenu(@ModelAttribute OrderDto user, Model model) {
        cookService.findAllMenuByOrder(user);
        model.addAttribute("menuList");
        return "mainPage";
    }

    @PostMapping(value = "/selectMenu")
    public String selectMenu(@ModelAttribute OrderDto user) {
        return "mainPage";
    }

    @PostMapping(value = "/bookMenu")
    public String bookMenu(@ModelAttribute OrderDto user) {
        return "mainPage";
    }



    // sing in или просто заказ
    // остальыне личные данные








    @ModelAttribute("time")
    public String[] getTime() {
        return new String[]{"00:00","01:00","02:00","03:00","04:00","05:00",
                "06:00","07:00","08:00","09:00","10:00","11:00",
                "12:00","13:00","14:00","15:00","16:00","17:00",
                "18:00","19:00","20:00","21:00","22:00","23:00"};
    }

    @ModelAttribute("order")
    public OrderDto getOrder() {
        return new OrderDto();
    }

    @ModelAttribute("townList")
    public List<Town> getTownList() {
        return townService.findAll();
    }

    @ModelAttribute("districtList")
    public List<District> getDistrictList() {
        return new ArrayList<>();
    }
}
