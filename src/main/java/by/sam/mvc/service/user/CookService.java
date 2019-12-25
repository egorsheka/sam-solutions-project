package by.sam.mvc.service.user;

import by.sam.mvc.dto.CookDto;
import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.models.worktime.WorkTime;
import by.sam.mvc.service.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface CookService extends Service<Cook> {

    Cook getAuthenticationCook(UserDetails currentUser);

    void create(CookDto cookDto);

    void updateDistricts(int cookId, int townId, List<District> districts);
    void updateWorkTime(int id, List<WorkTime> times);
    void addMenuItem(int id, Menu menu);
    void updateMenuItem(int id, Menu menu);
    void createMenuItem(int id, Menu menu);
    void deleteMenuItem(int cookId, int menuId);

    List<Cook> getCooksByDistrictId(int id);

    List<Menu> findAllMenuByOrder(OrderDto dto);

    List<Cook> filterCooksByWorkTime(List<Cook> cooks, DayOfWeek weekDay, LocalTime time);



}
