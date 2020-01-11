package by.sam.mvc.service.order.impl;

import by.sam.mvc.dto.OrderDto;
import by.sam.mvc.models.order.Order;
import by.sam.mvc.repository.order.OrderRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final DistrictService districtService;
    private final ClientService clientService;


    public OrderServiceImpl(OrderRepository orderRepository, MenuService menuService, DistrictService districtService, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.districtService = districtService;
        this.clientService = clientService;
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
        order.setClient(clientService.read(orderDto.getClientId()));
        orderRepository.create(order);
    }
}
