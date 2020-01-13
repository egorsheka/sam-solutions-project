package by.sam.mvc.service.user;

import by.sam.mvc.models.order.Order;
import by.sam.mvc.models.user.Client;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.service.Service;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientService extends Service<Client> {

    void create(Client client);
    Client getAuthenticationCook(UserDetails currentUser);

    void createOrderItem(int id, Order order);
    void updateOrderItem(int id, Order order);


}
