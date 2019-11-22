package by.sam.mvc.controllers;


import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.repository.WorkTimeRepository;
import by.sam.mvc.repository.location.DistrictRepository;
import by.sam.mvc.repository.location.TownRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class LocationController {

    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;

    private final WorkTimeRepository workTimeRepository;

    public LocationController(TownRepository townRepository, DistrictRepository districtRepository, WorkTimeRepository workTimeRepository) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
        this.workTimeRepository = workTimeRepository;
    }

    private Town town = new Town();
    private List<District> districts;





    @GetMapping(path = "/availabilities")
    public String getAvailabilitiesPage(Model model) {
        return "location";
    }

    @PostMapping(value = "/selectTown")
    public String selectTown(@ModelAttribute Town town, Model model) {
        List<District> districts = districtRepository.getDistrictListByTown(town);
        town.setDistricts(new ArrayList<>());
        town.getDistricts().add(new District());
        this.districts = districts;
        this.town.setId(town.getId());
        model.addAttribute("districtList", districts);
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

    @ModelAttribute("townList")
    public List<Town> getTownList() {
        return townRepository.findAll();
    }

    @ModelAttribute("town")
    public Town getEmptyTown() {
        return town;
    }


}
