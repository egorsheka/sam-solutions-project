package by.sam.mvc.controllers;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.repository.menu.DishConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MenuControllers {

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public ModelAndView getAllMenu(){
        List<Dish> dishes = DishConnector.getDishDao().findAll();
        ModelAndView modelAndView = new ModelAndView("menu");
        modelAndView.addObject("dishes", dishes);
        System.out.println(dishes);
        return modelAndView;
    }
}
