package by.sam.mvc.service.menu;


import by.sam.mvc.models.menu.Menu;

import java.util.List;

public interface MenuService {

    void create(Menu menu);
    void delete(int id);
    void update(Menu menu);
    Menu read(int id);
    List<Menu> findAll();
}