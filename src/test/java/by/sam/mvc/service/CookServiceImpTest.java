package by.sam.mvc.service;

import by.sam.mvc.configuration.ThymeleafConfig;
import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.entity.worktime.WorkTime;
import by.sam.mvc.model.DistrictDto;
import by.sam.mvc.model.OrderDto;
import by.sam.mvc.model.WorkTimeDto;
import by.sam.mvc.repository.user.CookRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.user.impl.CookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;


@WebAppConfiguration
public class CookServiceImpTest {

    private MockMvc mockMvc;

    @Mock
    private CookRepository cookRepository;
    @Mock
    private DistrictService districtService;
    @InjectMocks
    CookServiceImpl cookService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(cookService).build();
    }


    @Test
    public void updateWorkTimeWithWrongData() {
        List<WorkTimeDto> workTimesOne = new ArrayList<>(Arrays.asList(new WorkTimeDto("MONDAY", "30:00", "00:00")));
        List<WorkTimeDto> workTimesTwo = new ArrayList<>(Arrays.asList(new WorkTimeDto("MONDAY", "00:70", "00:00")));
        List<WorkTimeDto> workTimesThree = new ArrayList<>(Arrays.asList(new WorkTimeDto("MONDAY", "25:00", "00:00")));

        assertFalse(cookService.updateWorkTime(1, workTimesOne));
        assertFalse(cookService.updateWorkTime(1, workTimesTwo));
        assertFalse(cookService.updateWorkTime(1, workTimesThree));

    }


    @Test
    public void filterCooksByWorkTime() {
        OrderDto dto = new OrderDto();
        dto.setDate(LocalDate.of(2020, 9, 7));
        dto.setTime("19:00");

        List<WorkTime> workTimesOne = new ArrayList<>(Arrays.asList(new WorkTime(DayOfWeek.MONDAY, "12:00", "20:00")));
        List<WorkTime> workTimesTwo = new ArrayList<>(Arrays.asList(new WorkTime(DayOfWeek.MONDAY, "15:00", "23:00")));
        List<WorkTime> workTimesThree = new ArrayList<>(Arrays.asList(new WorkTime(DayOfWeek.MONDAY, "13:00", "02:00")));
        List<WorkTime> workTimesFour = new ArrayList<>(Arrays.asList(new WorkTime(DayOfWeek.MONDAY, "02:00", "12:00")));
        List<WorkTime> workTimesFive = new ArrayList<>(Arrays.asList(new WorkTime(DayOfWeek.TUESDAY, "12:00", "20:00")));


        List<Cook> cooks = new ArrayList<>(Arrays.asList(new Cook(), new Cook(), new Cook(), new Cook(), new Cook()));
        cooks.get(0).setWorkTime(workTimesOne);
        cooks.get(1).setWorkTime(workTimesTwo);
        cooks.get(2).setWorkTime(workTimesThree);
        cooks.get(3).setWorkTime(workTimesFour);
        cooks.get(4).setWorkTime(workTimesFive);

        List<Cook> expectList = new ArrayList<>();
        expectList.add(cooks.get(0));
        expectList.add(cooks.get(1));
        expectList.add(cooks.get(2));


        List<Cook> filterCooks = cookService.filterCooksByWorkTime(cooks, dto);


        assertEquals(expectList, filterCooks);
    }


    @Test
    public void getSortedDistrictDtoListByTown() {

        Town town = new Town(1, "Minsk");
        District part = new District(4, "Партизанский", town);
        District zav = new District(5, "Заводской", town);
        District len = new District(6, "Ленинский", town);
        District okt = new District(7, "Октябрьский", town);

        List<District> districtList = new ArrayList<>(Arrays.asList(
                new District(1, "Центральный", town),
                new District(2, "Советский", town),
                new District(3, "Первомайский", town),
                part, zav, len, okt,
                new District(8, "Московский", town),
                new District(9, "Фрунзенский", town)));

        Cook cook = new Cook();
        cook.setDistricts(new ArrayList<>(Arrays.asList(part, zav, len, okt)));

        when(cookRepository.read(anyInt())).thenReturn(cook);
        when(districtService.getDistrictListByTown(any())).thenReturn(districtList);

        List<DistrictDto> expectList = new ArrayList<>(Arrays.asList(
                new DistrictDto(5, "Заводской"),
                new DistrictDto(6, "Ленинский"),
                new DistrictDto(7, "Октябрьский"),
                new DistrictDto(4, "Партизанский"),
                new DistrictDto(8, "Московский"),
                new DistrictDto(3, "Первомайский"),
                new DistrictDto(2, "Советский"),
                new DistrictDto(9, "Фрунзенский"),
                new DistrictDto(1, "Центральный")));


        List<DistrictDto> sortedDistrictDtoListByTown = cookService.getSortedDistrictCookDtoListByTown(town, 1);

        assertEquals(expectList, sortedDistrictDtoListByTown);

    }

}

