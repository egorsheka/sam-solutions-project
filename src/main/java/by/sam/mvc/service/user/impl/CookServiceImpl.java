package by.sam.mvc.service.user.impl;

import by.sam.mvc.dto.*;
import by.sam.mvc.mail.GmailSenderService;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.models.user.Role;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.models.worktime.WorkTime;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.user.UserService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//todo
@Service
public class CookServiceImpl implements CookService {

    private final CookRepository cookRepository;
    private final MenuService menuService;
    private final DistrictService districtService;
    private final WorkTimeService workTimeService;
    private final UserService userService;
    private final GmailSenderService mailSender;



    public CookServiceImpl(CookRepository cookRepository, MenuService menuService, DistrictService districtService, WorkTimeService workTimeService, UserService userService, GmailSenderService mailSender) {
        this.cookRepository = cookRepository;
        this.menuService = menuService;
        this.districtService = districtService;
        this.workTimeService = workTimeService;
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Transactional
    @Override
    public void create(Cook cook) {
        cookRepository.create(cook);
    }

    //todo
    @Transactional
    @Override
    public void create(PersonDto cook) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(cook.getEmail());
        userEntity.setPassword(cook.getPassword());
        //
        // set role by userentity Service
        userEntity.setRole(new Role("COOK"));

        //
        userEntity.setVerify(false);
        userEntity.setIdVerification(UUID.randomUUID().toString().split("-")[4]);
//todo еренести в ресурсы через mailSendrt
        mailSender.send("Confirm your registration", "http://localhost:8084/sam_solutions_project_war/registration/confirm/" + userEntity.getIdVerification(), cook.getEmail());



        userService.create(userEntity);

        Cook newCook = new Cook();

        newCook.setName(cook.getName());
        newCook.setPassword(cook.getSurname());
        newCook.setEmail(cook.getEmail());
        newCook.setPassword(cook.getPassword());
        newCook.setUserEntity(userEntity);

        cookRepository.create(newCook);
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
    public void updateDistricts(int cookId, List<DistrictDto> dtoList) {
        List<District> districts = new ArrayList<>();
        for(DistrictDto dto: dtoList){
            districts.add(districtService.read(dto.getId()));
        }
        Cook cook = cookRepository.read(cookId);
        cook.setDistricts(districts);
        update(cook);
    }

    @Transactional
    @Override
    public void updateWorkTime(int id, List<WorkTimeDto> times) {
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


        List<WorkTime> timeList = times.stream().map(time ->
                new WorkTime(DayOfWeek.valueOf(time.getDay().toUpperCase()), time.getStartTime(), time.getEndTime())).collect(Collectors.toList());
        cook.setWorkTime(timeList);
        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public void updateMenu(int id, List<Menu> menus) {
        Cook cook = cookRepository.read(id);
        cook.setMenu(menus);
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


    //todo newMenu = menuService.read(id);
    //todo cook.add(newM)
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
    public void createMenuItem(int id, Menu menu) {
        menuService.create(menu);
        Cook cook = cookRepository.read(id);
        cook.getMenu().add(menu);
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

    @Transactional
    @Override
    public List<WorkTimeDto> readWorkTimeDto(int id) {
        Cook cook = read(id);
        return cook.getWorkTime()
                .stream()
                .map(workTime -> new WorkTimeDto(workTime.getDay().toString(), workTime.getStartTime(), workTime.getEndTime()))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<DistrictDto> readCookLocation(int id) {
        Cook cook = read(id);
        List<District> districts = cook.getDistricts();
        List<DistrictDto> districtList = new ArrayList<>();
        if(districts != null && !districts.isEmpty()) {
            districtList.add(new DistrictDto(districts.get(0).getTown().getId(), districts.get(0).getTown().getName()));
            for (District district : districts) {
                districtList.add(new DistrictDto(district.getId(), district.getName()));
            }
        }
        return districtList;
    }

    @Transactional
    @Override
    public List<DistrictDto> getSortedDistrictDtoListByTown(Town town, int id) {
        Cook cook = read(id);
         List<District> districtList = districtService.getDistrictListByTown(town);
         districtList.sort(Comparator.comparing(District::getName));
         List<District> districtCookList = cook.getDistricts();
         districtList.removeAll(districtCookList);
         districtList.addAll(0, districtCookList);

         return districtList.stream().map(d -> new DistrictDto(d.getId(), d.getName())).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public List<Cook> getCooksByDistrictId(int id) {
        return cookRepository.getCooksByDistrictId(id);
    }

    @Override
    public List<Cook> filterCooksByWorkTime(List<Cook> cooks, DayOfWeek weekDay, LocalTime time) {
        List<Cook> filterCooks = new ArrayList<>();


        for(Cook cook: cooks){
            for(WorkTime workTime: cook.getWorkTime()){
                LocalTime endTime = LocalTime.parse(workTime.getEndTime() + ":00");
                LocalTime startTime = LocalTime.parse(workTime.getStartTime() + ":00");
                if(workTime.getDay().equals(weekDay) && startTime.isBefore(time) &&  endTime.isAfter(time)){
                    filterCooks.add(cook);
                }
            }
        }

        return filterCooks;
    }


    @Transactional
    @Override
    public List<Menu> findAllMenuByOrder(OrderDto dto) {


        List<Cook> cooks = getCooksByDistrictId(dto.getDistrict().getId());


        DayOfWeek dayOfWeek = dto.getDate().getDayOfWeek();
        LocalTime time = LocalTime.parse(dto.getTime() + ":00");

         cooks = filterCooksByWorkTime(cooks, dayOfWeek, time);


        List<Menu> menus = new ArrayList<>();
        for (Cook cook : cooks) {
            menus.addAll(cook.getMenu());
        }

        return menus;
    }

}
