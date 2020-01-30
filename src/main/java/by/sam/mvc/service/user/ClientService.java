package by.sam.mvc.service.user;

import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.model.PersonDto;
import by.sam.mvc.service.Service;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientService {

    boolean create(PersonDto client);
    Client read(int id);
    void update(Client object);
    void delete(int id);



    Client getAuthenticationCook(UserDetails currentUser);

    void createOrderItem(int id, Order order);
    void updateOrderItem(int id, Order order);




}
