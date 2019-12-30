package by.sam.mvc.controllers.cook;


import by.sam.mvc.models.menu.*;
import by.sam.mvc.service.menu.CuisineService;
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

    private final MenuService menuService;
    private final CookService cookService;
    private final CuisineService cuisineService;

    private int cookId;


    public MenuController(MenuService menuService, CookService cookService, CuisineService cuisineService) {
        this.menuService = menuService;
        this.cookService = cookService;
        this.cuisineService = cuisineService;
    }


    @GetMapping(path = "/menuPage")
    public String getCookPersonalPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
        cookId = cookService.getAuthenticationCook(currentUser).getId();
        model.addAttribute("menuList", cookService.read(cookId).getMenu());
        return "cook/menu/startMenu";
    }


    //create new menu

    @GetMapping(value = "/createMenu")
    public String openCreateMenuPage(Model model) {
        model.addAttribute("newMenu", new Menu());
        return "cook/menu/createMenu";
    }


    @PostMapping(value = "/saveMenu", params ={"addDish"})
    public String addDishInMenu(@ModelAttribute Menu menu, Model model){
        if(menu.getDishes() == null){menu.setDishes(new ArrayList<>());}
        menu.getDishes().add(new Dish("", DishType.APPETISER, new Cuisine()));
        model.addAttribute("newMenu", menu);
        return "cook/menu/createMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"removeDish"})
    public String removeDishInMenu(@ModelAttribute Menu menu, Model model, @RequestParam int removeDish){
        menu.getDishes().remove(removeDish);
        model.addAttribute("newMenu", menu);
        return "cook/menu/createMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"saveNewMenu"})
    public String submitChanges(@ModelAttribute Menu menu, Model model){
        cookService.createMenuItem(cookId, menu);
        model.addAttribute( "menuList", cookService.read(cookId).getMenu());
        return "cook/menu/startMenu";
    }



    // delete one menu


    @PostMapping(value = "/editMenu", params = {"deleteMenu"})
    public String deleteMenu(@RequestParam int deleteMenu, Model model) {
        List<Menu> menus = cookService.read(cookId).getMenu();
        menus.remove(deleteMenu);
        cookService.updateMenu(cookId, menus);
        model.addAttribute("menuList", menus);
        return "cook/menu/startMenu";
    }

    // edit one menu

    @PostMapping(value = "/editMenu", params = {"editMenu"})
    public String openEditMenuPage(@ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"addEditDish"})
    public String addEditDishInMenu(@ModelAttribute Menu menu, Model model){
        if(menu.getDishes() == null){menu.setDishes(new ArrayList<>());}
        menu.getDishes().add(new Dish("", DishType.APPETISER, new Cuisine()));
        model.addAttribute("editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"removeEditDish"})
    public String removeEditDishInMenu(@ModelAttribute Menu menu, Model model, @RequestParam int removeEditDish){
        menu.getDishes().remove(removeEditDish);
        model.addAttribute("editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/saveMenu", params = {"saveEditMenu"} )
    public String submitNewMenu(@ModelAttribute Menu menu, Model model) {
        cookService.updateMenuItem(cookId, menu);
        model.addAttribute( "menuList", cookService.read(cookId).getMenu());
        return "cook/menu/startMenu";
    }



    //todo
    // see one menu

    @PostMapping(value = "/editMenu", params = {"seeMore"})
    public String openSeeMoreMenuPage(@ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "cook/seeMoreMenu";
    }






    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.values());
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("allTypesDish", DishType.values());
    }

    @ModelAttribute
    public void getAllCuisines(Model model){
        model.addAttribute("allCuisines", cuisineService.findAll());}




}




