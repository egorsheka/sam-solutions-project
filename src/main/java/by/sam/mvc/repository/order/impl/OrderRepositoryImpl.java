package by.sam.mvc.repository.order.impl;

import by.sam.mvc.models.order.Order;
import by.sam.mvc.repository.order.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Order order) {
        manager.persist(order);
    }

    @Override
    public Order read(int id) {
        return manager.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        manager.merge(order);
    }

    @Override
    public void delete(int id) {
        Order order = read(id);
        manager.remove(order);
    }


}
