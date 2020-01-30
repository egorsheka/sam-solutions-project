package by.sam.mvc.service.order;

import by.sam.mvc.model.OrderDto;
import by.sam.mvc.entity.order.Order;
import by.sam.mvc.service.Service;


public interface OrderService extends Service<Order> {

    void create(OrderDto orderDto);
    void makeOrderInProcess(int id);
    void makeOrderClosed(int id);
}
