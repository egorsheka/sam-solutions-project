package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.Client;
import by.sam.mvc.repository.Repository;

public interface ClientRepository extends Repository<Client> {

    Client read(String name);
}
