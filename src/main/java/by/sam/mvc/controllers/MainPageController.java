package by.sam.mvc.controllers;


import by.sam.mvc.dto.DistrictDto;
import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    //todo
    // ок, что контролер выаолняет преобразование?
    @PostMapping(value = "/getAllTowns")
    @ResponseBody
    public List<DistrictDto> getAllTowns(@RequestBody int i) {
        List<DistrictDto> list = townService.findAll().stream().map(d -> new DistrictDto(d.getId(), d.getName())).collect(Collectors.toList());
        return list;
    }

    @GetMapping(path = "/")
    public String getMainPage(Model model){
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
