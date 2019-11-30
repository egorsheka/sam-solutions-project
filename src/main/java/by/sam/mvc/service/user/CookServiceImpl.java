package by.sam.mvc.service.user;

import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CookServiceImpl implements CookService {

    private final CookRepository cookRepository;
    private final MenuService menuService;
    private final DistrictService districtService;
    private final WorkTimeService workTimeService;



    public CookServiceImpl(CookRepository cookRepository, MenuService menuService, DistrictService districtService, WorkTimeService workTimeService) {
        this.cookRepository = cookRepository;
        this.menuService = menuService;
        this.districtService = districtService;
        this.workTimeService = workTimeService;
    }

    @Transactional
    @Override
    public void create(Cook cook) {
        cookRepository.create(cook);
    }

    @Transactional
    @Override
    public Cook read(int id) {
        return cookRepository.read(id);
    }

    @Transactional
    @Override
    public void update(Cook cook) {
        Cook updateCook = cookRepository.read(cook.getId());

        updateCook.setName(cook.getName());
        updateCook.setSurname(cook.getSurname());
        updateCook.setEmail(cook.getEmail());
        updateCook.setPassword(cook.getPassword());
        updateCook.setBirthday(cook.getBirthday());
        updateCook.setMobile(cook.getMobile());
        updateCook.setStatus(cook.getStatus());

        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public void delete(int id) {
        cookRepository.delete(id);
    }

    @Transactional
    @Override
    public Cook getAuthenticationCook(UserDetails currentUser) {

        return cookRepository.read(currentUser.getUsername());
    }

    @Transactional
    @Override
    public void updateDistricts(int cookId, int townId, List<District> districts) {

        Cook cook = cookRepository.read(cookId);

        if (!(districts == null || districts.isEmpty())) {

            districts = districts.stream()
                    .map(District::getId)
                    .map(districtService::read)
                    .collect(Collectors.toList());

            cook.setDistricts(districts);
            cookRepository.update(cook);
        }
    }

    @Transactional
    @Override
    public void updateWorkTime(int id, List<WorkTime> times) {
        Cook cook = cookRepository.read(id);

        boolean cookWorkTimeIsEmpty = cook.getWorkTime().isEmpty();
        List<WorkTime> cookOldList = cook.getWorkTime();
        cook.setWorkTime(new ArrayList<>());
        cookRepository.update(cook);
        if (!cookWorkTimeIsEmpty) {
            for (WorkTime time : cookOldList) {
                workTimeService.delete(time.getId());
            }
        }
        cook.setWorkTime(times);
        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public void addMenuItem(int id, Menu menu) {
        Cook cook = cookRepository.read(id);
        menuService.create(menu);
        cook.getMenu().add(menu);
        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public void updateMenuItem(int id, Menu menu) {
        Cook cook = cookRepository.read(id);
        Menu oldMenu = menuService.read(menu.getId());

        cook.getMenu().remove(oldMenu);
        cook.getMenu().add(menu);

        menuService.update(menu);

        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public void deleteMenuItem(int cookId, int menuId) {
        Cook cook = cookRepository.read(cookId);
        Menu menu = menuService.read(menuId);

        cook.getMenu().remove(menu);

        menuService.delete(menuId);
        cookRepository.update(cook);
    }


}
