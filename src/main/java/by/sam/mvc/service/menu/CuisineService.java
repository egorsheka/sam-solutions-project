package by.sam.mvc.service.menu;

import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.service.Service;

import java.util.List;

public interface CuisineService extends Service<Cuisine> {
    List<Cuisine> findAll();
    Cuisine read(String name);
}
