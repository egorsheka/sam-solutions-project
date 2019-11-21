package by.sam.mvc.controllers;


import by.sam.mvc.models.WeekDay;
import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.repository.location.DistrictRepository;
import by.sam.mvc.repository.location.TownRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LocationController {

    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;

    public LocationController(TownRepository townRepository, DistrictRepository districtRepository) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
    }

    private Town town = new Town();
    private List<District> districts;


    @GetMapping(path = "/timeWork")
    public String getTimeWorkPage(Model model) {
        Map<Integer, Integer> selectedBox = new HashMap<>();
        for (int i = 1; i <= 7; ++i) {
            selectedBox.put(i, 0);
        }
        model.addAttribute("selectedBox", selectedBox);
        return "timeWork";
    }

    @PostMapping(value = "/timeWorkBox")
    public String saveWeekDays(@RequestParam Map<String, String> checkbox, Model model) {
        Map<Integer, Integer> selectedBox = new HashMap<>();
        for (int i = 0; i < 7; ++i) {
            selectedBox.put(i, 0);
        }


        List<WorkTime> workTimeList = new ArrayList<>();

        checkbox.forEach((k, v) -> {
            if (k.matches("\\d")) {
                selectedBox.put(Integer.valueOf(k), 1);
                workTimeList.add(new WorkTime());
            }
        });
        System.out.println(workTimeList.size());
        model.addAttribute("selectedBox", selectedBox);
        model.addAttribute("workTimeList", workTimeList);
        return "timeWorKWithTime";
    }

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
        System.out.println("town:" + town.getId());
        System.out.println("district");
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


    @ModelAttribute("weekDays")
    public WeekDay[] getWeekDays() {
        return WeekDay.values();
    }

    @ModelAttribute("time")
    public String[] getTime() {
        return new String[]{"00:00","01:00","02:00","03:00","04:00","05:00",
                            "06:00","07:00","08:00","09:00","10:00","11:00",
                            "12:00","13:00","14:00","15:00","16:00","17:00",
                            "18:00","19:00","20:00","21:00","22:00","23:00"};
    }

}
