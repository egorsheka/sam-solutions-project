package by.sam.mvc.service.order.impl;

import by.sam.mvc.model.OrderDto;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.order.OrderType;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.repository.order.OrderRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final DistrictService districtService;
    private final ClientService clientService;
    private final CookService cookService;


    public OrderServiceImpl(OrderRepository orderRepository, MenuService menuService, DistrictService districtService,
                            ClientService clientService, CookService cookService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.districtService = districtService;
        this.clientService = clientService;
        this.cookService = cookService;
    }

    @Transactional
    @Override
    public void create(Order order) {
        orderRepository.create(order);
    }
    @Transactional
    @Override
    public Order read(int id) {
        return orderRepository.read(id);
    }
    @Transactional
    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }
    @Transactional
    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    //todo is it ok that parametrs are id of cook and client
    @Transactional
    @Override
    public void create(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderTime("00:00");
        order.setEventTime(orderDto.getTime());
        order.setDate(orderDto.getDate());
        order.setCountOfGuests(orderDto.getCountOfGuests());
        order.setDistrict(districtService.read(orderDto.getDistrict().getId()));
        order.setAddress(orderDto.getAddress());
        order.setMenu(menuService.read(orderDto.getMenuId()));

        Client client = clientService.read(orderDto.getClientId());
        client.setName(orderDto.getClientName());
        client.setSurname(orderDto.getClientSurName());
        client.setMobile(orderDto.getClientMobile());
        Cook cook = cookService.getCookByMenuId(orderDto.getMenuId());
        order.setClient(client);
        order.setCook(cook);
        order.setOrderType(OrderType.valueOf("NEW"));
        orderRepository.create(order);

        cookService.createOrderItem(cook.getId(), order);

        client.setAddress(orderDto.getAddress());
        clientService.update(client);
        clientService.createOrderItem(orderDto.getClientId(), order);
    }

    @Transactional
    @Override
    public void makeOrderInProcess(int id) {
        Order order = read(id);
        order.setOrderType(OrderType.IN_PROCESS);
        update(order);
    }

    @Transactional
    @Override
    public void makeOrderClosed(int id) {
        Order order = read(id);
        order.setOrderType(OrderType.CLOSED);
        update(order);
    }
}
