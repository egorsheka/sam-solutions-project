package by.sam.mvc.controllers;


import by.sam.mvc.dto.CookDto;
import by.sam.mvc.mail.GmailSenderService;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final CookService cookService;
    private final GmailSenderService mail;
    private final UserService userService;

    public RegistrationController(CookService cookService, GmailSenderService mail, UserService userService) {
        this.cookService = cookService;
        this.mail = mail;
        this.userService = userService;
    }

    @GetMapping("/cook")
    public String showRegistrationForm(Model model) {
        model.addAttribute("cook", new CookDto());
        return "registration";
    }

    @PostMapping("/singUp")
    public String registerUserAccount(@ModelAttribute("user") CookDto cook) {
        cookService.create(cook);
        return "confirmRegistration";
    }

    @GetMapping("/singUp")
    public String registerUserAccount() {
        return "login";
    }

    @GetMapping(value = "/confirm/{id}")
    public String confirmRegistration(@PathVariable String id) {
        Optional<UserEntity> userEntity = userService.isVerifyUser(id);
        if(userEntity.isPresent()){
            UserEntity user = userEntity.get();
            user.setVerify(true);
            userService.update(user);
        }
        return "";
    }


}