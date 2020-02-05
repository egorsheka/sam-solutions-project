package by.sam.mvc.controllers;


import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.model.PersonDto;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// todo null
@Controller
@RequestMapping("/registration")
public class  RegistrationController {
    private final CookService cookService;
    private final UserService userService;
    private final ClientService clientService;

    public RegistrationController(CookService cookService, UserService userService, ClientService clientService) {
        this.cookService = cookService;
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping("/cook")
    public String showRegistrationFormForCook(Model model) {
        model.addAttribute("cook", new PersonDto());
        return "registrationCook";
    }


    @PostMapping("/singUpCook")
    public String registerCook(@Valid @ModelAttribute("cook") PersonDto cook, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationCook";
        }
        if (!cookService.create(cook)) {
            cook.setExist(true);
            return "registrationCook";
        }
        return "confirmRegistration";
    }


    @GetMapping("/")
    public String getRegisterClient(@ModelAttribute("user") PersonDto user) {

        return "login";
    }

    @PostMapping(value = "/")
    public String singUp(@Valid @ModelAttribute("user") PersonDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (!clientService.create(user)) {
            user.setExist(true);
        }
        return "login";
    }

    @GetMapping(value = "/confirm/{id}")
    public String confirmRegistration(@PathVariable String id, Model model) {
        if(userService.isVerifyUser(id)){
            userService.verifyUser(id);
        }
        model.addAttribute("user", new Cook());
        return "login";
    }

    @ModelAttribute("loginError")
    public boolean getTownList() {
        return false;
    }


}