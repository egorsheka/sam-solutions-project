package by.sam.mvc.service.menu;


import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.repository.menu.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {

    private final MenuRepository repository;

    public DefaultMenuService(MenuRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(Menu menu) {
        repository.create(menu);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Menu menu) {
        repository.update(menu);
    }

    @Override
    public Menu read(int id) {
        return repository.read(id);
    }

    @Override
    public List<Menu> findAll() {
        return repository.findAll();
    }
}
