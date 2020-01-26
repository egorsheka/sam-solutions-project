package by.sam.mvc.controllers.cook;

import by.sam.mvc.dto.CookProfileDto;
import by.sam.mvc.models.user.Client;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//todo null
@Controller
public class ProfileController {

    private CookService cookService;

    public ProfileController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping(path = "profile")
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        CookProfileDto dto =  cookService.fillCookProfileDto(cookService.getAuthenticationCook(currentUser));
        model.addAttribute("profile", dto);
        return "cook/profile";
    }

    @PostMapping(path = "filledProfile")
    public String getOrderPage(Model model, @ModelAttribute CookProfileDto dto) {
        cookService.updateProfileData(dto);
        model.addAttribute("profile", dto);
        return "cook/profile";
    }
}

