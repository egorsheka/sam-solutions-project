package by.sam.mvc.model;

import by.sam.mvc.entity.order.Order;

import java.util.List;

public class OrdersDto {
    private boolean isCorrectPage;
    private List<Order> orders;


    public OrdersDto(boolean isCorrectPage, List<Order> orders) {
        this.isCorrectPage = isCorrectPage;
        this.orders = orders;
    }

    public boolean isCorrectPage() {
        return isCorrectPage;
    }

    public void setCorrectPage(boolean correctPage) {
        isCorrectPage = correctPage;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
