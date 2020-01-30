package by.sam.mvc.controllers;


import by.sam.mvc.model.PersonDto;
import by.sam.mvc.entity.user.Cook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSingInController {



    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/signIn")
    public String singIn(@ModelAttribute Cook user, Model model) {
        return "login";
    }

    @ModelAttribute
    public void getAllDishTypes(Model model){
        model.addAttribute("user", new PersonDto());
    }


}
