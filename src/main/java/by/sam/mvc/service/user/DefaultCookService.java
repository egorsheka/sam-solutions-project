package by.sam.mvc.service.user;

import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.user.CookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCookService implements CookService {

    private final CookRepository repository;

    public DefaultCookService(CookRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(Cook cook) {
        repository.create(cook);
    }

    @Override
    public Cook read(int id) {
        return repository.read(id);
    }

    @Override
    public void update(Cook cook) {
        repository.update(cook);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Cook> findAll() {
        return null;
    }
}
