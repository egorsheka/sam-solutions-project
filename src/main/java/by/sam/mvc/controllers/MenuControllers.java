package by.sam.mvc.controllers;


import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.models.menu.DishType;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.menu.MenuLuxury;
import by.sam.mvc.models.wrappers.HtmlMenuWrapper;
import by.sam.mvc.repository.menu.MenuDao;
import by.sam.mvc.repository.menu.dish.DishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MenuControllers {

    private DishDao dishDao;
    private MenuDao menuDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getMenu(){
        return "";
    }


    @RequestMapping(value = "/editMenu", params = {})
    public String editMenu(@ModelAttribute Menu editMenu, Model model) {
        menuDao.read(editMenu.getId());
        Menu menu = menuDao.read(editMenu.getId());

        System.out.println(menuDao);
        model.addAttribute( "editMenu", menu);
        return "editMenu";
    }

    @RequestMapping(value = "/editMenu", params ={"addRow"})
    public String addDishRow(@ModelAttribute Menu menu){
        menu.getDishes().add(new Dish());
        return "editMenu";
    }



//    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
//    public String saveMenu(@ModelAttribute Menu menu, Model model) {
//        System.out.println(menu);
//        return "";
//    }


    @ModelAttribute
    public void getMenuList(Model model){
        model.addAttribute("menuList", menuDao.findAll());
    }



    @ModelAttribute
    public void getAllLuxuryTypes(Model model){
        model.addAttribute("allTypesLuxury", MenuLuxury.ALL);
    }
    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("allTypesDish", DishType.ALL);
    }











    @Autowired
    public void setDishDao(DishDao dishDao) { this.dishDao = dishDao;}

    @Autowired
    public void setMenuDao(MenuDao menuDao) {this.menuDao = menuDao;}
}





//@RequestMapping(value = "/showDishes", method = RequestMethod.POST)
//    public String showDishes(@ModelAttribute HtmlMenuWrapper selectedMenu, Model model) {
//        System.out.println("editMenu" + selectedMenu);
//        return "";
//    }

//    public static void main(String[] args) {
//        MenuControllers menuControllers = new MenuControllers();
//        List<HtmlMenuWrapper> htmlMenuWrapperList = new ArrayList<>();
//        List<Menu> list = new ArrayList<>();
//        List<Dish> dishes = new ArrayList<>();
//
//        dishes.add(new Dish("11", "11", DishType.APPETISER));
//        list.add(new Menu(1,"1", new BigDecimal(1), MenuLuxury.SIGNATURE, dishes));
//
//        list.stream().forEach(menu -> htmlMenuWrapperList.add(new HtmlMenuWrapper(menu)));
//        System.out.println(htmlMenuWrapperList);
//    }