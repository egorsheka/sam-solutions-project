package by.sam.mvc.controllers.cook;


import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.controllers.MainPageController;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThymeleafConfig.class})
@WebAppConfiguration
public class MainPageControllerTest {


    @Mock
    private TownService townService;
    @Mock
    private DistrictService districtService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        MainPageController controller = new MainPageController(townService, districtService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }


    @Test
    public void test() throws Exception {


        List<Town> towns = new ArrayList<>();
        towns.add(new Town("Minsk"));
        when(townService.findAll()).thenReturn(towns);
        assertEquals(townService.findAll(), towns);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("townList", hasSize(1)))
                .andExpect(model().attribute("townList", hasItem(
                        allOf(
                                hasProperty("name", is("Minsk"))
                        )
                )));
// todo to ask why result is 2
//verify(townService, times(1)).findAll();
// todo to ask why is exceptions. how was it supposed to be?
//verifyNoMoreInteractions(townService);
    }

}


