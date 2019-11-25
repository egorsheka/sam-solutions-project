package by.sam.mvc.service.user;

import by.sam.mvc.models.WeekDay;
import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.Service;

import java.util.List;

public interface CookService extends Service<Cook> {

    void updateDistricts(int cookId, int townId, List<District> districts);
    void updateWorkTime(int id, List<WorkTime> times);
    void updateMenu(int id, List<Menu> menus);

}
