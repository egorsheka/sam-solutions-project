package by.sam.mvc.service.order.impl;

import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.order.OrderType;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.service.mail.GmailSenderService;
import by.sam.mvc.model.OrderDto;
import by.sam.mvc.model.OrdersDto;
import by.sam.mvc.repository.order.OrderRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.menu.MenuService;
import by.sam.mvc.service.order.OrderService;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.CookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final DistrictService districtService;
    private final ClientService clientService;
    private final CookService cookService;
    private final GmailSenderService mailSender;
    private final int SIZE_ORDERS_LIST = 4;


    public OrderServiceImpl(OrderRepository orderRepository, MenuService menuService, DistrictService districtService,
                            ClientService clientService, CookService cookService, GmailSenderService mailSender) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
        this.districtService = districtService;
        this.clientService = clientService;
        this.cookService = cookService;
        this.mailSender = mailSender;
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
        order.setAddInfo(orderDto.getAddInfo());

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
        if(order.getAddInfo() == null) {
            mailSender.send("Новый заказ", "У вас появился новый заказ, перейдите на сайт, чтобы подтвердить его.", cook.getEmail());
        }else {
            String message = " Дополниетельная информация клиента: " + order.getAddInfo();
            mailSender.send("Новый заказ", "У вас появился новый заказ, перейдите на сайт, чтобы подтвердить его." + message, cook.getEmail());

        }

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
    public void sendMailClientToConfirmService(int id) {
        Order order = read(id);
        Client client = order.getClient();
        mailSender.send("Повар подтвердил ваш заказ", "Ожидайте звонка или сообщения от повара.", client.getEmail());
    }

    @Transactional
    @Override
    public void makeOrderClosed(int id) {
        Order order = read(id);
        order.setOrderType(OrderType.CLOSED);
        update(order);
    }

    @Override
    public List<Order> sortOrders(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getDate));
        Collections.reverse(orders);
        return orders;

    }

    @Override
    public OrdersDto getNextListOrders(List<Order> orderList, int page) {
        orderList = sortOrders(orderList);

        if (orderList.size() >= page * SIZE_ORDERS_LIST && page > 0) {
            return new OrdersDto(true,
                    orderList.subList((page - 1) * SIZE_ORDERS_LIST, page * SIZE_ORDERS_LIST));}

        if (orderList.size() <= SIZE_ORDERS_LIST) { return new OrdersDto(true, orderList); }

        int lastOrders = orderList.size() % SIZE_ORDERS_LIST;
        return new OrdersDto(false, orderList.subList(orderList.size() - lastOrders, orderList.size()));

    }

    @Override
    public OrdersDto getPreviousListOrders(List<Order> orderList, int page) {
        orderList = sortOrders(orderList);

        if (page <= 1) {
            boolean firstPage = false;
            if(page == 1){
                firstPage = true;
            }
            if (orderList.size() >= SIZE_ORDERS_LIST) {
                return new OrdersDto(firstPage, orderList.subList(0, SIZE_ORDERS_LIST));
            } else {
                return new OrdersDto(firstPage, orderList);
            }
        }

        if(page * SIZE_ORDERS_LIST > orderList.size()){
            int lastOrders = orderList.size() % SIZE_ORDERS_LIST;
            return new OrdersDto(false, orderList.subList(orderList.size() - lastOrders, orderList.size()));
        }
        return new OrdersDto(true, orderList.subList((page - 1) * SIZE_ORDERS_LIST, page * SIZE_ORDERS_LIST));
    }
}
