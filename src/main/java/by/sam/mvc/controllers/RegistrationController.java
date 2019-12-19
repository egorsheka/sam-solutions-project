package by.sam.mvc.controllers;


import by.sam.mvc.dto.CookDto;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final CookService cookService;

    public RegistrationController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping("/cook")
    public String showRegistrationForm(Model model) {
        model.addAttribute("cook", new CookDto());
        return "registration";
    }

    @PostMapping("/singUp")
    public String registerUserAccount(@ModelAttribute("user") CookDto cook) {
        cookService.create(cook);
        return "login";
    }
}