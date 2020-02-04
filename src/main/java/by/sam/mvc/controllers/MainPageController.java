package by.sam.mvc.controllers;


import by.sam.mvc.model.DistrictDto;
import by.sam.mvc.model.OrderDto;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.model.TownDto;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// todo
@Controller
public class MainPageController {


    private TownService townService;
    private DistrictService districtService;



    public MainPageController(TownService townService, DistrictService districtService) {
        this.townService = townService;
        this.districtService = districtService;
    }

    @GetMapping(value = "/getAllTowns")
    @ResponseBody
    public List<TownDto> getAllTowns() {
        return townService.findAllTownDto();
    }

    @GetMapping(path = "/")
    public String getMainPage(){
        return "mainPage";
    }


    @PostMapping(value = "/getDistrictsByTown")
    @ResponseBody
    public List<DistrictDto> getDistrictsByTown(@RequestBody Town town) {
        return districtService.getDistrictDtoListByTown(town);
    }


    //todo is ok?
    @ModelAttribute("order")
    public OrderDto getOrder() {
        OrderDto dto = new OrderDto();
        dto.setDate(LocalDate.now());
        return dto;
    }

    @ModelAttribute("townList")
    public List<Town> getTownList() {
        return townService.findAll();
    }

}
