package by.sam.mvc.controllers;


import by.sam.mvc.models.menu.*;
import by.sam.mvc.repository.menu.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class MenuController {

   // @Autowired
    //private final DishDao dishDao;
   // private final MenuDao menuDao;
    private List<Menu> menuList;




//    @Autowired
//    public MenuController(DishDao dishDao, MenuDao menuDao) {
//        this.dishDao = dishDao;
//        this.menuDao = menuDao;
//    }


    @ModelAttribute
    public void getMenuObject(Model model){model.addAttribute("newMenu", new Menu());}

    @ModelAttribute
    public void getMenuList(Model model){
        //menuList = menuDao.findAll();
        model.addAttribute("menuList", menuList);}

    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.values());
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("allTypesDish", DishType.values());
    }



    public MenuController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    @Autowired
    private final DishRepository dishRepository;

    @GetMapping(path = "/")
    public String getCookPersonalPage(){
        //dishRepository.add(new Dish("name", new Cuisine("Bel"), DishType.APPETISER));
        dishRepository.add(new Cuisine("bel2"));
        return "";
    }

    @PostMapping(value = "/createMenu")
    public String openCreateMenuPage() {
        return "createMenu";
    }

    @PostMapping(value = "/saveNewMenu", params = {"submitNewMenu"} )
    public String submitNewMenu(@ModelAttribute Menu newMenu, Model model) {
        //menuDao.create(newMenu);
       // model.addAttribute("menuList", menuDao.findAll());
        return "";
    }

    @PostMapping(value = "/saveNewMenu", params = {"addDishRowInNewMenu"} )
    public String addDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model) {
//        if(newMenu.getDishes() == null){
//            newMenu.setDishes(new ArrayList<>());
//        }
//        newMenu.getDishes().add(new Dish());
        model.addAttribute("newMenu", newMenu);
        return "createMenu";
    }

    @PostMapping(value = "/saveNewMenu", params = {"removeDishRowInNewMenu"})
    public String removeDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model, HttpServletRequest request) {
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
       // newMenu.getDishes().remove(removeIndex);
        model.addAttribute("newMenu", newMenu);
        return "editMenu";
    }

    @PostMapping(value = "/editMenu", params = {})
    public String openEditMenuPage( @ModelAttribute Menu editMenu, Model model) {
      //  Menu menu = menuDao.read(editMenu.getId());
      //  model.addAttribute( "editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/editMenu", params = {"deleteMenu"})
    public String deleteMenu(@ModelAttribute Menu deleteMenu, Model model, HttpServletRequest request) {
        int deleteIndex = Integer.parseInt(request.getParameter("deleteMenu"));
        menuList.remove(deleteIndex);
       // menuDao.delete(deleteMenu.getId());
        model.addAttribute( "menuList", menuList);
        return "";
    }

    @PostMapping(value = "/saveMenu", params ={"addRow"})
    public String addDishRowInEditMenu(@ModelAttribute Menu menu, Model model){
//        if(menu.getDishes() == null){
//            menu.setDishes(new ArrayList<>());
//        }
//        menu.getDishes().add(new Dish());
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"removeRow"})
    public String removeDishRowInEditMenu(@ModelAttribute Menu menu, Model model, HttpServletRequest request){
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
       // menu.getDishes().remove(removeIndex);
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }

    @PostMapping(value = "/saveMenu", params ={"submit"})
    public String submitChanges(@ModelAttribute Menu menu){
       // menuDao.update(menu);
        return "";
    }

}




