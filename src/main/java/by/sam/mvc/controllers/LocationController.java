package by.sam.mvc.controllers;


import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.repository.worktime.WorkTimeRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class LocationController {

    private final TownService townService;
    private final DistrictService districtService;

    private final WorkTimeRepository workTimeRepository;

    public LocationController(TownService townService, DistrictService districtService, WorkTimeRepository workTimeRepository) {
        this.townService = townService;
        this.districtService = districtService;
        this.workTimeRepository = workTimeRepository;
    }

    private Town town = new Town();
    private List<District> districts;





    @GetMapping(path = "/availabilities")
    public String getAvailabilitiesPage(Model model) {
        model.addAttribute("town", town);
        List<Town> towns = townService.findAll();

        model.addAttribute("townList", towns);
        return "location";
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
    public String saveTownAndDistricts(@ModelAttribute Town town, Model model) {
        town.getDistricts().forEach(district -> System.out.println(district.getId()));
        return "location";
    }


    @ModelAttribute("districtList")
    public List<District> getDistrictsList() {
        return districts;
    }

//    @ModelAttribute("townList")
//    public List<Town> getTownList() {
//        List<Town> towns = townService.findAll();
//        System.out.println(towns.get(0).getId());
//        System.out.println(towns.get(0).getName());
//        System.out.println(towns.get(1).getId());
//        System.out.println(towns.get(1).getName());
//
//        return towns;
//    }




}
