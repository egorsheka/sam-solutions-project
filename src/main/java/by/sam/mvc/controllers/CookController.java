package by.sam.mvc.controllers;


import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CookController {

    @GetMapping(path = "/")
    public String getCookPersonalPage(Model model){
        return "startCook";
    }
}
