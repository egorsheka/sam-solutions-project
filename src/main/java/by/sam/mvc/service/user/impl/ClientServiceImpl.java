package by.sam.mvc.service.user.impl;

import by.sam.mvc.models.user.Client;
import by.sam.mvc.models.user.Role;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.repository.user.ClientRepository;
import by.sam.mvc.service.user.ClientService;
import by.sam.mvc.service.user.RoleService;
import by.sam.mvc.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private UserService userService;
    private RoleService roleService;

    public ClientServiceImpl(ClientRepository clientRepository, UserService userService, RoleService roleService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void create(Client client) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(client.getEmail());
        userEntity.setPassword(client.getPassword());
        userEntity.setRole(new Role("CLIENT"));

        userEntity.setVerify(true);
        //userEntity.setIdVerification(UUID.randomUUID().toString().split("-")[4]);
        //mailSender.send("vikulya0102@gmail.com", "Pdhjw2ks", "Confirm your registration", "http://localhost:8084/sam_solutions_project_war/registration/confirm/" + userEntity.getIdVerification(), cook.getEmail());

        userService.create(userEntity);

        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setSurname(client.getSurname());
        newClient.setEmail(client.getEmail());
        newClient.setPassword(client.getPassword());
        newClient.setMobile(client.getMobile());
        newClient.setUserEntity(userEntity);

        clientRepository.create(newClient);
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
    public Client getAuthenticationCook(UserDetails currentUser) {
        return clientRepository.read(currentUser.getUsername());
    }


}
