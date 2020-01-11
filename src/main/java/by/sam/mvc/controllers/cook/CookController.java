package by.sam.mvc.controllers.cook;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//todo null
@Controller
public class CookController {


    @GetMapping(path = "/cookPage")
    public String getCookPersonalPage(Model model){
        return "cook/startCook";
    }
}
