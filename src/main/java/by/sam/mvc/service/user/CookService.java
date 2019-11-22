package by.sam.mvc.service.user;

import by.sam.mvc.models.user.Cook;

import java.util.List;

public interface CookService {

    void create(Cook object);
    Cook read(int id);
    void update(Cook object);
    void delete(int id);
    List<Cook> findAll();
}
