package by.sam.mvc.service;


import by.sam.mvc.config.ThymeleafConfig;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.model.OrdersDto;
import by.sam.mvc.service.order.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThymeleafConfig.class})
@WebAppConfiguration
public class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    private List<Order> orders;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        orders = new ArrayList<>();

        orders.add(new Order(LocalDate.of(2020, 2, 14)));
        orders.add(new Order(LocalDate.of(2020, 2, 10)));
        orders.add(new Order(LocalDate.of(2020, 2, 7)));
        orders.add(new Order(LocalDate.of(2020, 2, 4)));

        orders.add(new Order(LocalDate.of(2020, 2, 13)));
        orders.add(new Order(LocalDate.of(2020, 2, 5)));
        orders.add(new Order(LocalDate.of(2020, 2, 2)));
        orders.add(new Order(LocalDate.of(2020, 2, 3)));

        orders.add(new Order(LocalDate.of(2020, 2, 9)));
        orders.add(new Order(LocalDate.of(2020, 2, 12)));
        orders.add(new Order(LocalDate.of(2020, 2, 1)));
        orders.add(new Order(LocalDate.of(2020, 2, 8)));


        orders.add(new Order(LocalDate.of(2020, 2, 6)));
        orders.add(new Order(LocalDate.of(2020, 2, 11)));
    }



    @Test
    public void getNextListOrders_FirstPage_ListSize14() {

        OrdersDto dto = orderService.getNextListOrders(orders, 1);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));
        expectList.add(new Order(LocalDate.of(2020, 2, 12)));
        expectList.add(new Order(LocalDate.of(2020, 2, 11)));

        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());

    }


    @Test
    public void getNextListOrders_FirstPage_ListSize2() {

        orders = new ArrayList<>();
        orders.add(new Order(LocalDate.of(2020, 2, 14)));
        orders.add(new Order(LocalDate.of(2020, 2, 13)));

        OrdersDto dto = orderService.getNextListOrders(orders, 1);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));


        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());

    }

    @Test
    public void getNextListOrders_SecondPage() {

        OrdersDto dto = orderService.getNextListOrders(orders, 2);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 10)));
        expectList.add(new Order(LocalDate.of(2020, 2, 9)));
        expectList.add(new Order(LocalDate.of(2020, 2, 8)));
        expectList.add(new Order(LocalDate.of(2020, 2, 7)));

        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());

    }




    @Test
    public void getNextListOrders_LastPage() {

        OrdersDto dto = orderService.getNextListOrders(orders, 4);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 2)));
        expectList.add(new Order(LocalDate.of(2020, 2, 1)));

        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());
    }


    @Test
    public void getNextListOrders_FailedPage() {

        OrdersDto dto = orderService.getNextListOrders(orders, 10);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 2)));
        expectList.add(new Order(LocalDate.of(2020, 2, 1)));

        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());
    }

    @Test
    public void getNextListOrders_FailedPage2() {

        OrdersDto dto = orderService.getNextListOrders(orders, -1);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 2)));
        expectList.add(new Order(LocalDate.of(2020, 2, 1)));

        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());
    }

    @Test
    public void getPreviousListOrders_FailedPage_ListSize2() {

        orders = new ArrayList<>();
        orders.add(new Order(LocalDate.of(2020, 2, 14)));
        orders.add(new Order(LocalDate.of(2020, 2, 13)));

        OrdersDto dto = orderService.getPreviousListOrders(orders, -3);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));


        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());

    }

    @Test
    public void getPreviousListOrders_FirstPage_ListSize2() {

        orders = new ArrayList<>();
        orders.add(new Order(LocalDate.of(2020, 2, 14)));
        orders.add(new Order(LocalDate.of(2020, 2, 13)));

        OrdersDto dto = orderService.getPreviousListOrders(orders, 1);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));


        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());

    }

    @Test
    public void getPreviousListOrders_FailedPage_ListSize14() {

        OrdersDto dto = orderService.getPreviousListOrders(orders, -2);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));
        expectList.add(new Order(LocalDate.of(2020, 2, 12)));
        expectList.add(new Order(LocalDate.of(2020, 2, 11)));

        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());
    }

    @Test
    public void getPreviousListOrders_FailedPage2_ListSize14() {

        OrdersDto dto = orderService.getPreviousListOrders(orders, 15);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 2)));
        expectList.add(new Order(LocalDate.of(2020, 2, 1)));
        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());
    }

    @Test
    public void getPreviousListOrders_FirstPage_ListSize14() {

        OrdersDto dto = orderService.getPreviousListOrders(orders, 1);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 14)));
        expectList.add(new Order(LocalDate.of(2020, 2, 13)));
        expectList.add(new Order(LocalDate.of(2020, 2, 12)));
        expectList.add(new Order(LocalDate.of(2020, 2, 11)));

        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());
    }

    @Test
    public void getPreviousListOrders_SecondPage() {

        OrdersDto dto = orderService.getPreviousListOrders(orders, 2);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 10)));
        expectList.add(new Order(LocalDate.of(2020, 2, 9)));
        expectList.add(new Order(LocalDate.of(2020, 2, 8)));
        expectList.add(new Order(LocalDate.of(2020, 2, 7)));

        assertEquals(expectList, dto.getOrders());
        assertTrue(dto.isCorrectPage());
    }




    @Test
    public void getPreviousListOrders_LastPage() {

        OrdersDto dto = orderService.getPreviousListOrders(orders, 4);

        List<Order> expectList = new ArrayList<>();
        expectList.add(new Order(LocalDate.of(2020, 2, 2)));
        expectList.add(new Order(LocalDate.of(2020, 2, 1)));

        assertEquals(expectList, dto.getOrders());
        assertFalse(dto.isCorrectPage());
    }



}
