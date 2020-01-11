package by.sam.mvc.controllers;


import by.sam.mvc.dto.PersonDto;
import by.sam.mvc.models.user.Cook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSingInController {



    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/signIn")
    public String singIn(@ModelAttribute Cook user, Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("user", new PersonDto());
    }


}
