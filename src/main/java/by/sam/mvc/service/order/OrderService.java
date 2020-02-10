package by.sam.mvc.service.order;

import by.sam.mvc.model.OrderDto;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.model.OrdersDto;
import by.sam.mvc.service.Service;

import java.util.List;
import java.util.Optional;


public interface OrderService extends Service<Order> {

    void create(OrderDto orderDto);
    void makeOrderInProcess(int id);
    void sendMailClientToConfirmService(int id);
    void makeOrderClosed(int id);
    List<Order> sortOrders(List<Order> orders);

    OrdersDto getNextListOrders(List<Order> orders, int page);
    OrdersDto getPreviousListOrders(List<Order> orders,int page);

}
