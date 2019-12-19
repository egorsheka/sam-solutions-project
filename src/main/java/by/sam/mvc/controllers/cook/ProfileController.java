package by.sam.mvc.controllers.cook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {


    @GetMapping(path = "profile")
    public String getProfilePage() {
        return "profile";
    }
}

