package by.sam.mvc.controllers;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.repository.menu.DishDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MenuControllers {

    private DishDao dishDao;

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public ModelAndView getAllMenu(){
        List<Dish> dishes = dishDao.findAll();
        ModelAndView modelAndView = new ModelAndView("menu");
        modelAndView.addObject("dishes", dishes);
        return modelAndView;
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

}
