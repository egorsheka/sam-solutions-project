package by.sam.mvc.controllers;


import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.*;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.menu.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MenuController {


    private List<Menu> menuList;



    private final MenuService menuService;
    private final CookRepository cookRepository;


    public MenuController(MenuService menuService, CookRepository cookRepository) {
        this.menuService = menuService;
        this.cookRepository = cookRepository;
    }



    @GetMapping(path = "/")
    public String getCookPersonalPage(Model model){
        menuList = menuService.findAll();
        model.addAttribute("menuList", menuList);
        return "";
    }

    @PostMapping(value = "/createMenu")
    public String openCreateMenuPage(Model model) {
        cookRepository.update(new Cook(3, "Pety", new Town(3,  "Gomel")));

        model.addAttribute("newMenu", new Menu());
        return "createMenu";
    }

    @PostMapping(value = "/saveNewMenu", params = {"submit"} )
    public String submitNewMenu(@ModelAttribute Menu newMenu, Model model) {
        menuService.create(newMenu);
        menuList = menuService.findAll();
        model.addAttribute("menuList", menuList);
        return "";
    }

    @PostMapping(value = "/saveNewMenu", params = {"addRow"} )
    public String addDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model) {
        if(newMenu.getDishes() == null){
            newMenu.setDishes(new ArrayList<>());
        }
        newMenu.getDishes().add(new Dish());
        model.addAttribute("newMenu", newMenu);
        return "createMenu";
    }



    @PostMapping(value = "/saveNewMenu", params = {"removeRow"})
    public String removeDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model, HttpServletRequest request) {
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
        newMenu.getDishes().remove(removeIndex);
        model.addAttribute("newMenu", newMenu);
        return "editMenu";
    }


    @PostMapping(value = "/editMenu", params = {})
    public String openEditMenuPage( @ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/editMenu", params = {"deleteMenu"})
    public String deleteMenu(@ModelAttribute Menu deleteMenu, Model model, HttpServletRequest request) {
        int deleteIndex = Integer.parseInt(request.getParameter("deleteMenu"));
        menuList.remove(deleteIndex);
        menuService.delete(deleteMenu.getId());
        model.addAttribute( "menuList", menuList);
        return "";
    }

    @PostMapping(value = "/saveMenu", params ={"addRow"})
    public String addDishRowInEditMenu(@ModelAttribute Menu menu, Model model){
        if(menu.getDishes() == null){
            menu.setDishes(new ArrayList<>());
        }
        menu.getDishes().add(new Dish());
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }


    @PostMapping(value = "/saveMenu", params ={"removeRow"})
    public String removeDishRowInEditMenu(@ModelAttribute Menu menu, Model model, HttpServletRequest request){
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
        menu.getDishes().remove(removeIndex);
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"submit"})
    public String submitChanges(@ModelAttribute Menu menu, Model model){

        menuService.update(menu);
        model.addAttribute( "menuList", menuList);
        return "";
    }





    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.values());
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("allTypesDish", DishType.values());
    }




}




