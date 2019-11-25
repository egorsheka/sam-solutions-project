package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface MenuRepository extends Repository<Menu> {
    List<Menu> findAll();
}
