package by.sam.mvc.controllers;


import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.worktime.WorkTimeRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class LocationController {

    private final TownService townService;
    private final DistrictService districtService;
    private final CookService cookService;

    public LocationController(TownService townService, DistrictService districtService, CookService cookService) {
        this.townService = townService;
        this.districtService = districtService;
        this.cookService = cookService;
    }

    private Town town = new Town();
    private List<District> districts;
    private int userId = 1;
    private Cook cook;



    @GetMapping(path ={"location"})
    public String getAvailabilitiesPage(Model model) {
        //TODO spring security
        //userId = id;
        cook = cookService.read(userId);
        districts = new ArrayList<>();
        if(!cook.getDistricts().isEmpty()){


            town.setId(cook.getDistricts().get(0).getTown().getId());
            town.setDistricts(new ArrayList<>());
            cook.getDistricts().forEach(district -> town.getDistricts().add(district));
            List<District> districts = districtService.getDistrictListByTown(town);
            this.districts = districts;
            model.addAttribute("districtList", districts);
            model.addAttribute("town", town);
            model.addAttribute("cook", cook);
            return "filledLocation";
        }else{
            model.addAttribute("cook", cook);
            model.addAttribute("town", town);
            return "location";
        }
    }



    @PostMapping(value = "/selectTown")
    public String selectTown(@ModelAttribute Town town, Model model) {
        List<District> districts = districtService.getDistrictListByTown(town);
        town.setDistricts(new ArrayList<>());
        town.getDistricts().add(new District());
        this.districts = districts;
        this.town.setId(town.getId());


        model.addAttribute("districtList", districts);
        model.addAttribute("town", town);
        return "location";
    }


    @PostMapping(value = "/selectTown", params = {"addDistrict"})
    public String addDistrictRowInNewMenu(@ModelAttribute Town town, Model model) {
        town.getDistricts().add(new District());
        model.addAttribute("town", town);
        return "location";
    }


    @PostMapping(value = "/selectTown", params = {"removeRow"})
    public String removeDishRowInEditMenu(@ModelAttribute Town town, Model model, HttpServletRequest request) {
        int removeIndex = Integer.valueOf(request.getParameter("removeRow"));
        town.getDistricts().remove(removeIndex);
        model.addAttribute("town", town);
        return "location";
    }


    @PostMapping(value = "/selectTown", params = {"save"})
    public String saveTownAndDistricts(@ModelAttribute Town town) {
        cookService.updateDistricts(userId, town.getId(), town.getDistricts());
        this.town = new Town();

        return "location";
    }


    @ModelAttribute("districtList")
    public List<District> getDistrictsList() {
        return districts;
    }

    @ModelAttribute("townList")
    public List<Town> getTownList() {
        return townService.findAll();
    }

    @ModelAttribute("cook")
    public Cook getCook() {
        return cook;
    }


}
