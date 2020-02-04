package by.sam.mvc.service.user.impl;

import by.sam.mvc.entity.order.Order;
import by.sam.mvc.entity.user.Client;
import by.sam.mvc.entity.user.Role;
import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.model.PersonDto;
import by.sam.mvc.repository.user.ClientRepository;
import by.sam.mvc.service.RegistrationValidator;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import javax.persistence.NoResultException;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private UserService userService;
    private RegistrationValidator registrationValidator;



    public ClientServiceImpl(ClientRepository clientRepository, UserService userService, RegistrationValidator registrationValidator) {
        this.clientRepository = clientRepository;
        this.userService = userService;

        this.registrationValidator = registrationValidator;
    }

    @Transactional
    @Override
    public boolean create(PersonDto client) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(client.getEmail());
        userEntity.setPassword(client.getPassword());
        userEntity.setRole(new Role("CLIENT"));

        userEntity.setVerify(true);
        //userEntity.setIdVerification(UUID.randomUUID().toString().split("-")[4]);
        //mailSender.send("vikulya0102@gmail.com", "Pdhjw2ks", "Confirm your registration", "http://localhost:8084/sam_solutions_project_war/registration/confirm/" + userEntity.getIdVerification(), cook.getEmail());

        try {
            UserEntity user = userService.read(client.getEmail());
            if (user != null){
                return false;
            }
        }catch (NoResultException e){}

        userService.create(userEntity);

        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setSurname(client.getSurname());
        newClient.setEmail(client.getEmail());
        newClient.setPassword(client.getPassword());
        newClient.setMobile(client.getMobile());
        newClient.setUserEntity(userEntity);

        clientRepository.create(newClient);
        return true;
    }



    @Transactional
    @Override
    public Client read(int id) {
        return clientRepository.read(id);
    }

    @Transactional
    @Override
    public void update(Client object) {
        clientRepository.update(object);
    }

    @Transactional
    @Override
    public void delete(int id) {
        clientRepository.delete(id);
    }



    @Transactional
    @Override
    public Client getAuthenticationClient(UserDetails currentUser) {
        return clientRepository.read(currentUser.getUsername());
    }



    @Transactional
    @Override
    public void createOrderItem(int id, Order order) {
        Client client = clientRepository.read(id);
        client.getOrders().add(order);
        clientRepository.update(client);
    }

    //todo check delete order or not
    @Transactional
    @Override
    public void updateOrderItem(int id, Order order) {
//        Client client = clientRepository.read(id);
//        Order oldOrder = orderService.read(order.getId());
//        client.getOrders().remove(oldOrder);
//        client.getOrders().add(order);
//
//        orderService.update(order);
//
//        clientRepository.update(client);
    }

}
