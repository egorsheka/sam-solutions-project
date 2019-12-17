package by.sam.mvc.controllers.cook;


import by.sam.mvc.models.menu.*;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final CookService cookService;

    private int cookId;


    public MenuController(MenuService menuService, CookService cookService) {
        this.menuService = menuService;
        this.cookService = cookService;
    }


    @GetMapping(path = "/menuPage")
    public String getCookPersonalPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
        Cook cook =  cookService.getAuthenticationCook(currentUser);
        cookId = cookService.getAuthenticationCook(currentUser).getId();
        menuList = cookService.read(cookId).getMenu();
        model.addAttribute("menuList", menuList);
        return "startMenu";
    }

    @PostMapping(value = "/createMenu")
    public String openCreateMenuPage(Model model) {

        model.addAttribute("newMenu", new Menu());
        return "createMenu";
    }

    @PostMapping(value = "/saveNewMenu", params = {"submit"} )
    public String submitNewMenu(@ModelAttribute Menu newMenu, Model model) {
        cookService.addMenuItem(cookId, newMenu);
        menuList = cookService.read(1).getMenu();
        model.addAttribute("menuList", menuList);
        return "startMenu";
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


    @PostMapping(value = "/editMenu", params = {"editMenu"})
    public String openEditMenuPage( @ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/editMenu", params = {"seeMore"})
    public String openSeeMoreMenuPage( @ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "seeMoreMenu";
    }

    @PostMapping(value = "/editMenu", params = {"deleteMenu"})
    public String deleteMenu(@ModelAttribute Menu deleteMenu, Model model, HttpServletRequest request) {
        int deleteIndex = Integer.parseInt(request.getParameter("deleteMenu"));
        menuList.remove(deleteIndex);
        cookService.deleteMenuItem(cookId, deleteMenu.getId());
        model.addAttribute( "menuList", menuList);
        return "startMenu";
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

        cookService.updateMenuItem(cookId, menu);
        model.addAttribute( "menuList", menuList);
        return "startMenu";
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




