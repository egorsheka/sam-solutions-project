package by.sam.mvc.service;


import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.repository.user.UserRepository;
import by.sam.mvc.service.user.impl.CookServiceImpl;
import by.sam.mvc.service.user.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThymeleafConfig.class})
@WebAppConfiguration
public class UserServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;



    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        UserServiceImpl service = new UserServiceImpl(userRepository);

        mockMvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    

}
