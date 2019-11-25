package by.sam.mvc.controllers;


import by.sam.mvc.models.WeekDay;
import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class WorkTimeController {

    private final WorkTimeService workTimeService;
    private final CookService cookService;

    public WorkTimeController(WorkTimeService workTimeService, CookService cookService) {
        this.workTimeService = workTimeService;
        this.cookService = cookService;
    }

    @GetMapping(path = "/timeWork")
    public String getTimeWorkPage(Model model) {
        Map<Integer, Integer> selectedBox = new HashMap<>();
        for (int i = 1; i <= 7; ++i) {
            selectedBox.put(i, 0);
        }
        model.addAttribute("selectedBox", selectedBox);
        return "timeWorkDays";
    }

    @PostMapping(value = "/timeWorkBox")
    public String saveWeekDays(@RequestParam Map<String, String> checkbox, Model model) {
        Map<Integer, Integer> selectedBox = new HashMap<>();
        for (int i = 0; i < 7; ++i) {
            selectedBox.put(i, 0);
        }
        checkbox.forEach((k, v) -> {
            if (k.matches("\\d")) {
                selectedBox.put(Integer.valueOf(k), 1);
            }
        });
        model.addAttribute("selectedBox", selectedBox);
        return "timeWorkTime";
    }

    @PostMapping(value = "/timeWorkBox", params = {"saveTime"})
    public String saveTime(@RequestParam Map<String, String> params, Model model) {
        Cook cook = new Cook();
        cook.setWorkTime(workTimeService.createWorkTimeListFromParams(params));
        cookService.create(cook);
        return "startCook";
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
