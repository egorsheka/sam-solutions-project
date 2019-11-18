package by.sam.mvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookController {



    @GetMapping(path = "/hey")
    public String getCookPersonalPage(Model model){

        return "";
    }
}
