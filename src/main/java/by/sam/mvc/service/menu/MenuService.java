package by.sam.mvc.service.menu;


import by.sam.mvc.models.menu.Menu;

public interface MenuService {


    void create(Menu menu);
    void delete(int id);
    void update(Menu menu);
    Menu read(int id);
    //List<Menu> findAllMenuByOrder(OrderDto dto);
}
