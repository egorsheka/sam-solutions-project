package by.sam.mvc.controllers.cook;


import by.sam.mvc.controllers.Dto;
import by.sam.mvc.dto.DistrictDto;
import by.sam.mvc.dto.LocationDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.user.CookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LocationController {

    private final TownService townService;
    private final CookService cookService;

    public LocationController(TownService townService, CookService cookService) {
        this.townService = townService;
        this.cookService = cookService;
    }

    @GetMapping(path = {"location"})
    public String getAvailabilitiesPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        System.out.println(currentUser.isEnabled());
        return "cook/location";
    }


    @PostMapping(value = "/getAllDistricts")
    @ResponseBody
    public List<DistrictDto> getDistricts(@RequestBody Town town, @AuthenticationPrincipal UserDetails currentUser) {
        return cookService.getSortedDistrictDtoListByTown(town, cookService.getAuthenticationCook(currentUser).getId());
    }


    @PostMapping(value = "/getCookDistricts")
    @ResponseBody
    public List<DistrictDto> getCookLocation(@RequestBody int i, @AuthenticationPrincipal UserDetails currentUser) {
        return cookService.readCookLocation(cookService.getAuthenticationCook(currentUser).getId());
    }


    @PostMapping(value = "/saveCookDistricts")
    public String getCookDistricts(@RequestBody List<DistrictDto> dtoList, @AuthenticationPrincipal UserDetails currentUser) {
        cookService.updateDistricts(cookService.getAuthenticationCook(currentUser).getId(), dtoList);
        return "cook/startCook";
    }

}
