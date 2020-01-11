package by.sam.mvc.controllers;


import by.sam.mvc.dto.PersonDto;
import by.sam.mvc.models.user.Client;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

// todo null
@Controller
@RequestMapping("/registration")
public class RegistrationController {

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
    public String registerCook(@ModelAttribute("user") PersonDto cook) {
        cookService.create(cook);
        return "confirmRegistration";
    }


    @GetMapping("/")
    public String getRegisterClient(Model model) {
        model.addAttribute("user", new PersonDto());
        return "login";
    }

    @PostMapping(value = "/")
    public String singUp(@ModelAttribute Client client, Model model) {
        clientService.create(client);
        model.addAttribute("user", new PersonDto());
        return "login";
    }

    @GetMapping(value = "/confirm/{id}")
    public String confirmRegistration(@PathVariable String id, Model model) {
        if(userService.isVerifyUser(id)){
            UserEntity user = userService.read(id);
            user.setVerify(true);
            userService.update(user);
        }
        model.addAttribute("user", new Cook());
        return "login";
    }




}