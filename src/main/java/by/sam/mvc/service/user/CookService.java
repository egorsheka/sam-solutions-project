package by.sam.mvc.service.user;

import by.sam.mvc.model.*;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.service.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface CookService extends Service<Cook> {

    Cook getAuthenticationCook(UserDetails currentUser);

    boolean create(PersonDto personDto);

    void updateDistricts(int cookId, List<DistrictDto> dtoList);
    boolean updateWorkTime(int id, List<WorkTimeDto> times);

    void deleteMenuItem(int id, Menu menu);

    void createMenuItem(int id, Menu menu);
    void updateMenuItem(int id, Menu menu);

    void createOrderItem(int id, Order order);
    void updateOrderItem(int id, Order order);


    Cook getCookByMenuId(int menuId);

    boolean isAdmissibleCountOfMenu(int id);

    List<WorkTimeDto> readWorkTimeDto(int id);

    List<DistrictDto> readCookLocation(int id);

    List<DistrictDto> getSortedDistrictCookDtoListByTown(Town town, int id);

    List<Cook> getCooksByDistrictId(int id);

    List<Menu> findAllMenuByOrder(OrderDto dto);

    List<Cook> filterCooksByWorkTime(List<Cook> cooks, OrderDto dto);

    CookProfileDto fillCookProfileDto(Cook cook);
    void updateProfileData(CookProfileDto dto);

}
