package by.sam.mvc.controllers.cook;


import by.sam.mvc.models.menu.Cuisine;
import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.service.menu.CuisineService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//todo null
@Controller
public class MenuController {

    private final MenuService menuService;
    private final CookService cookService;
    private final CuisineService cuisineService;

    public MenuController(MenuService menuService, CookService cookService, CuisineService cuisineService) {
        this.menuService = menuService;
        this.cookService = cookService;
        this.cuisineService = cuisineService;
    }


    @GetMapping(path = "/menuPage")
    public String getCookMenuPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
        model.addAttribute("menuList",
                cookService.read(cookService.getAuthenticationCook(currentUser).getId()).getMenu());
        return "cook/menu/startMenu";
    }


    //create menu methods

    @GetMapping(value = "/createMenu")
    public String getCreateMenuPage(Model model) {
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
    public String saveNewMenu(@ModelAttribute Menu menu, Model model, @AuthenticationPrincipal UserDetails currentUser){
        int cookId = cookService.getAuthenticationCook(currentUser).getId();
        cookService.createMenuItem(cookId, menu);
        model.addAttribute( "menuList", cookService.read(cookId).getMenu());
        return "cook/menu/startMenu";
    }



    // delete one menu


    @PostMapping(value = "/selectMenu", params = {"deleteMenu"})
    public String deleteMenu(@RequestParam int deleteMenu, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        int cookId = cookService.getAuthenticationCook(currentUser).getId();
        List<Menu> menus = cookService.read(cookId).getMenu();
        menus.remove(deleteMenu);
        cookService.updateMenu(cookId, menus);
        model.addAttribute("menuList", menus);
        return "cook/menu/startMenu";
    }

    // edit one menu

    @PostMapping(value = "/selectMenu", params = {"editMenu"})
    public String getEditMenuPage(@ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/editMenu", params ={"addEditDish"})
    public String addEditDishInMenu(@ModelAttribute Menu menu, Model model){
        if(menu.getDishes() == null){menu.setDishes(new ArrayList<>());}
        menu.getDishes().add(new Dish("", DishType.APPETISER, new Cuisine()));
        model.addAttribute("editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/editMenu", params ={"removeEditDish"})
    public String removeEditDishInMenu(@ModelAttribute Menu menu, Model model, @RequestParam int removeEditDish){
        menu.getDishes().remove(removeEditDish);
        model.addAttribute("editMenu", menu);
        return "cook/menu/editMenu";
    }

    @PostMapping(value = "/editMenu", params = {"saveEditMenu"} )
    public String saveEditMenu(@ModelAttribute Menu menu, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        int cookId = cookService.getAuthenticationCook(currentUser).getId();
        cookService.updateMenuItem(cookId, menu);
        model.addAttribute( "menuList", cookService.read(cookId).getMenu());
        return "cook/menu/startMenu";
    }


    // view one menu

    @PostMapping(value = "/selectMenu", params = {"seeMore"})
    public String getViewMoreMenuPage(@ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuService.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "cook/menu/viewMoreMenu";
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




