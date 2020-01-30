package by.sam.mvc.controllers.cook;

import by.sam.mvc.model.CookProfileDto;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

//todo null
@Controller
public class ProfileController {

    private CookService cookService;

    public ProfileController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping(path = "profile")
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("profile",
                cookService.fillCookProfileDto(cookService.getAuthenticationCook(currentUser)));
        return "cook/profile";
    }

    @PostMapping(path = "filledProfile")
    public String getOrderPage(@Valid @ModelAttribute("profile") CookProfileDto profile, BindingResult bindingResult,
                               Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("profile",
                    cookService.fillCookProfileDto(cookService.getAuthenticationCook(currentUser)));
            return "cook/profile";
        }
        cookService.updateProfileData(profile);
        model.addAttribute("profile", profile);
        return "cook/profile";
    }
}

