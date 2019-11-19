package by.sam.mvc.controllers;


import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.repository.location.DistrictRepository;
import by.sam.mvc.repository.location.TownRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AvailabilitiesController {

    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;

    public AvailabilitiesController(TownRepository townRepository, DistrictRepository districtRepository) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
    }

    @ModelAttribute("town")
    public Town checkOptions(){
        return new Town();
    }
    @GetMapping(path = "/availabilities")
    public String getAvailabilitiesPage(Model model){
        List<Town> list =  townRepository.findAll();

        //model.addAttribute("town", new Town());
        model.addAttribute("townList", townRepository.findAll());
        return "availabilities";
    }

    @PostMapping(value = "/selectTown")
    public String selectTown(@ModelAttribute Town town, Model model){
        System.out.println(town.getName() + town.getId());
//        List<District> districts = districtRepository.getDistrictListByTown(town);
//        model.addAttribute("districts", townRepository.findAll());
        return "availabilities";
    }



}
