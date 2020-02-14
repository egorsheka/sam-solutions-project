package by.sam.mvc.controllers.cook;


import by.sam.mvc.model.WorkTimeDto;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class WorkTimeController {


    private final CookService cookService;


    public WorkTimeController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping(path = "/timeWork")
    public String getTimeWorkPage(Model model) {
        model.addAttribute("workTime", new ArrayList<WorkTimeDto>(7));
        return "cook/workTime";
    }


    @GetMapping(path = "/timeWorkData")
    @ResponseBody
    public List<WorkTimeDto> getCookTimeWorkData(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("workTime", new ArrayList<WorkTimeDto>(7));
        return cookService.readWorkTimeDto(cookService.getAuthenticationCook(currentUser).getId());
    }

    @PostMapping(path = "/saveTimeWorkData")
    public String saveCookTimeWorkData(@RequestBody List<WorkTimeDto> times, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        if(!cookService.updateWorkTime(cookService.getAuthenticationCook(currentUser).getId(), times)){
            model.addAttribute("workTime", new ArrayList<WorkTimeDto>(7));
            return "cook/workTime";
        }
        return "cook/startCook";
    }






}
