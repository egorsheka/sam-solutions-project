package by.sam.mvc.controllers.cook;


import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookController {

    private final CookService cookService;

    public CookController(CookService cookService) {
        this.cookService = cookService;
    }


    @GetMapping(path = "/cookPage")
    public String getCookPersonalPage(Model model){
        return "startCook";
    }
}
