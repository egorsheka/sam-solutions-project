package by.sam.mvc.controllers;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.repository.menu.MenuDao;
import by.sam.mvc.repository.menu.dish.DishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class MenuControllers {

    private DishDao dishDao;
    private MenuDao menuDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAllMenu(){
        return "";
    }

    @ModelAttribute
    public void getAllMenu(Model model){
        model.addAttribute("menuList", menuDao.findAll());
    }
    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.ALL);
    }

    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute Menu editMenu, Model model) {
        model.addAttribute("mmmm", editMenu);
        System.out.println(editMenu);
        return "";
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {this.menuDao = menuDao;}
}