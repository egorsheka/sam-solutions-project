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

import java.util.ArrayList;
import java.util.List;

@Controller
public class AvailabilitiesController {

    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;

    public AvailabilitiesController(TownRepository townRepository, DistrictRepository districtRepository) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
    }

    private Town town = new Town();

//
////    @GetMapping(path = "/availabilities")
////    public String getAvailabilitiesPage(Model model){
////        return "availabilities";
////    }
//
//    @PostMapping(value = "/selectTown")
//    public String selectTown(@ModelAttribute Town town, Model model){
//        List<District> districts = districtRepository.getDistrictListByTown(town);
//        model.addAttribute("districtList", districts);
//        return "availabilities";
//    }
//
//    @ModelAttribute("districtList")
//    public List<District> getDistrictsList(){
//        return new ArrayList();
//    }
//    @ModelAttribute("townList")
//    public List<Town> getTownList(){
//        return townRepository.findAll();
//    }
//    @ModelAttribute("town")
//    public Town getEmptyTown(){
//        return town;
//    }





}
