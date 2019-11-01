package by.sam.mvc.controllers;


import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.repository.menu.MenuDao;
import by.sam.mvc.repository.menu.dish.DishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MenuControllers {

    private DishDao dishDao;
    private MenuDao menuDao;
    private List<Menu> menuList;




    @Autowired
    public void setDishDao(DishDao dishDao) { this.dishDao = dishDao;}

    @Autowired
    public void setMenuDao(MenuDao menuDao) {this.menuDao = menuDao;}



    @ModelAttribute
    public void getMenuObject(Model model){model.addAttribute("newMenu", new Menu());}

    @ModelAttribute
    public void getMenuList(Model model){
        menuList = menuDao.findAll();
        model.addAttribute("menuList", menuList);}

    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.ALL);
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("allTypesDish", DishType.ALL);
    }







    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getCookPersonalPage(){
        return "";
    }

    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public String openCreateMenuPage() {
        return "createMenu";
    }

    @RequestMapping(value = "/saveNewMenu", params = {"submitNewMenu"} )
    public String submitNewMenu(@ModelAttribute Menu newMenu, Model model) {
        menuDao.create(newMenu);
        model.addAttribute("menuList", menuDao.findAll());
        return "";
    }

    @RequestMapping(value = "/saveNewMenu", params = {"addDishRowInNewMenu"} )
    public String addDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model, HttpServletRequest request) {
        if(newMenu.getDishes() == null){
            newMenu.setDishes(new ArrayList<>());
        }
        newMenu.getDishes().add(new Dish());
        model.addAttribute("newMenu", newMenu);
        return "createMenu";
    }

    @RequestMapping(value = "/saveNewMenu", params = {"removeDishRowInNewMenu"})
    public String removeDishRowInNewMenu(@ModelAttribute Menu newMenu,  Model model, HttpServletRequest request) {
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
        newMenu.getDishes().remove(removeIndex);
        model.addAttribute("newMenu", newMenu);
        return "editMenu";
    }

    @RequestMapping(value = "/editMenu", params = {})
    public String openEditMenuPage(@ModelAttribute Menu editMenu, Model model) {
        Menu menu = menuDao.read(editMenu.getId());
        model.addAttribute( "editMenu", menu);
        return "editMenu";
    }

    @RequestMapping(value = "/editMenu", params = {"deleteMenu"})
    public String deleteMenu(@ModelAttribute Menu deleteMenu, Model model, HttpServletRequest request) {
        int deleteIndex = Integer.parseInt(request.getParameter("deleteMenu"));
        menuList.remove(deleteIndex);
        menuDao.delete(deleteMenu.getId());
        model.addAttribute( "menuList", menuList);
        return "";
    }

    @RequestMapping(value = "/saveMenu", params ={"addRow"})
    public String addDishRowInEditMenu(@ModelAttribute Menu menu, Model model){
        if(menu.getDishes() == null){
            menu.setDishes(new ArrayList<>());
        }
        menu.getDishes().add(new Dish());
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }

    @RequestMapping(value = "/saveMenu", params ={"removeRow"})
    public String removeDishRowInEditMenu(@ModelAttribute Menu menu, Model model, HttpServletRequest request){
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
        menu.getDishes().remove(removeIndex);
        model.addAttribute("editMenu", menu);
        return "editMenu";
    }

    @RequestMapping(value = "/saveMenu", params ={"submit"})
    public String submitChanges(@ModelAttribute Menu menu){
        menuDao.update(menu);
        return "";
    }

}




