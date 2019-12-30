package by.sam.mvc.service.user;

import by.sam.mvc.dto.CookDto;
import by.sam.mvc.dto.DistrictDto;
import by.sam.mvc.dto.LocationDto;
import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
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

    void updateDistricts(int cookId, List<DistrictDto> dtoList);
    void updateWorkTime(int id, List<WorkTime> times);
    void updateMenu(int id, List<Menu> menus);
    void addMenuItem(int id, Menu menu);
    void updateMenuItem(int id, Menu menu);
    void createMenuItem(int id, Menu menu);
    void deleteMenuItem(int cookId, int menuId);

    List<DistrictDto> readCookLocation(int id);

    List<DistrictDto> getSortedDistrictDtoListByTown(Town town, int id);

    List<Cook> getCooksByDistrictId(int id);

    List<Menu> findAllMenuByOrder(OrderDto dto);

    List<Cook> filterCooksByWorkTime(List<Cook> cooks, DayOfWeek weekDay, LocalTime time);



}
