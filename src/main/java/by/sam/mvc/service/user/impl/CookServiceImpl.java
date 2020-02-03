package by.sam.mvc.service.user.impl;

import by.sam.mvc.model.*;
import by.sam.mvc.mail.GmailSenderService;
import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.menu.Menu;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.entity.user.Role;
import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.entity.worktime.WorkTime;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.user.CookService;
import by.sam.mvc.service.user.UserService;
import by.sam.mvc.service.worktime.WorkTimeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
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




    public CookServiceImpl(CookRepository cookRepository, MenuService menuService, DistrictService districtService,
                           WorkTimeService workTimeService, UserService userService, GmailSenderService mailSender) {
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
    @Transactional(noRollbackFor = Exception.class)
    @Override
    public boolean create(PersonDto cook) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(cook.getEmail());
        userEntity.setPassword(cook.getPassword());
        //todo
        // set role by userentity Service
        userEntity.setRole(new Role("COOK"));

        //
        userEntity.setVerify(false);
        userEntity.setIdVerification(UUID.randomUUID().toString().split("-")[4]);
        //todo еренести в ресурсы через mailSendrt
        //mailSender.send("Confirm your registration", "http://localhost:8084/sam_solutions_project_war/registration/confirm/" + userEntity.getIdVerification(), cook.getEmail());

        if(userService.getUserCount(cook.getEmail()) != 0){
            return false;
        }


        userService.create(userEntity);
        Cook newCook = new Cook();

        newCook.setName(cook.getName());
        newCook.setSurname(cook.getSurname());
        newCook.setPassword(cook.getPassword());
        newCook.setEmail(cook.getEmail());
        newCook.setUserEntity(userEntity);

        cookRepository.create(newCook);
        return true;
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
    public boolean updateWorkTime(int id, List<WorkTimeDto> times) {

        List<WorkTime> timeList = times.stream().map(time ->
                new WorkTime(DayOfWeek.valueOf(time.getDay().toUpperCase()), time.getStartTime(), time.getEndTime())).collect(Collectors.toList());
        for (WorkTime workTime: timeList){
            if(!(workTime.getStartTime().matches("((0|1)\\d|2[0-4]):[0-5]\\d") && workTime.getEndTime().matches("((0|1)\\d|2[0-4]):[0-5]\\d"))){
                return false;
            }
//            String[] startTime = workTime.getStartTime().split(":");
//            String[] endTime = workTime.getEndTime().split(":");
//
//            int startTimeInteger = Integer.parseInt(startTime[0])*100 + Integer.parseInt(startTime[1]);
//            int endTimeInteger = Integer.parseInt(endTime[0])*100 + Integer.parseInt(endTime[1]);
//            if (startTimeInteger > endTimeInteger){
//                return false;
//            }
        }

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



        cook.setWorkTime(timeList);
        cookRepository.update(cook);
        return true;
    }

    @Transactional
    @Override
    public void updateMenu(int id, Menu menu) {

        Cook cook = cookRepository.read(id);
        cook.getMenu().remove(menuService.read(menu.getId()));
        cookRepository.update(cook);
        menuService.delete(menu.getId());
    }





    @Transactional
    @Override
    public void createMenuItem(int id, Menu menu) {
        menuService.create(menu);
        Cook cook = cookRepository.read(id);
        cook.getMenu().add(menu);
        cookRepository.update(cook);
    }

    //todo check delete menu or not
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
    public void createOrderItem(int id, Order order) {
        Cook cook = cookRepository.read(id);
        cook.getOrders().add(order);
        cookRepository.update(cook);
    }

    //todo check delete order or not
    @Transactional
    @Override
    public void updateOrderItem(int id, Order order) {
//        Cook cook = cookRepository.read(id);
//        Order oldOrder = orderService.read(order.getId());
//        cook.getOrders().remove(oldOrder);
//        cook.getOrders().add(order);
//
//        orderService.update(order);
//
//        cookRepository.update(cook);
    }

    @Transactional
    @Override
    public Cook getCookByMenuId(int menuId) {
        return cookRepository.getCookByMenuId(menuId);
    }

    @Transactional
    @Override
    public boolean isAdmissibleCountOfMenu(int id) {
        Cook cook = read(id);
        return cook.getMenu().size() < 6;
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

    @Override
    public CookProfileDto fillCookProfileDto(Cook cook) {
        CookProfileDto dto = new CookProfileDto();
        dto.setId(cook.getId());
        dto.setName(cook.getName());
        dto.setSurname(cook.getSurname());
        dto.setEmail(cook.getEmail());
        dto.setPassword(cook.getPassword());
        dto.setMobile(cook.getMobile());
        dto.setAddress(cook.getAddress());
        dto.setCity(cook.getCity());

        if(cook.getBirthday() != null){
            String[] dates =  cook.getBirthday().split("/");
            dto.setBirthdayDay(dates[0]);
            dto.setBirthdayMonth(dates[1]);
            dto.setBirthdayYear(dates[2]);
        }
        return dto;
    }

    @Transactional
    @Override
    public void updateProfileData(CookProfileDto dto) {
        Cook cook = read(dto.getId());
        cook.setName(dto.getName());
        cook.setSurname(dto.getSurname());
        cook.setEmail(dto.getEmail());
        cook.setPassword(dto.getPassword());
        cook.getUserEntity().setEmail(dto.getEmail());
        cook.getUserEntity().setPassword(dto.getPassword());
        cook.setMobile(dto.getMobile());
        cook.setAddress(dto.getAddress());
        cook.setCity(dto.getCity());
        cook.setBirthday(dto.getBirthdayDay() + "/" + dto.getBirthdayMonth() + "/" + dto.getBirthdayYear());
        update(cook);
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



        if(dto.getCuisineList() != null){
            List<Cuisine> cuisines = new ArrayList<>(dto.getCuisineList());
            for(Cuisine cuisine: cuisines){
                if(cuisine.getName() == null){
                    dto.getCuisineList().remove(cuisine);
                }
            }
            if(!dto.getCuisineList().isEmpty()){
                menus = menuService.filterMenuByCuisine(menus, dto.getCuisineList());
            }
        }

        return menus;
    }

}
