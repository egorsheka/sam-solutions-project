package by.sam.mvc.controllers.cook;


import by.sam.mvc.models.worktime.WeekDay;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WorkTimeController {

    private final WorkTimeService workTimeService;
    private final CookService cookService;

    int cookId = 1;

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
        return "cook/timeWorkDays";
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
        return "cook/timeWorkTime";
    }

    @PostMapping(value = "/timeWorkBox", params = {"saveTime"})
    public String saveTime(@RequestParam Map<String, String> params, @AuthenticationPrincipal UserDetails currentUser) {

        cookId = cookService.getAuthenticationCook(currentUser).getId();
        cookService.updateWorkTime(cookId, workTimeService.createWorkTimeListFromParams(params));
        return "cook/startCook";
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
