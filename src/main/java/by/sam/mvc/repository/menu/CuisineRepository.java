package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Cuisine;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface CuisineRepository extends Repository<Cuisine> {
    List<Cuisine> findAll();
    Cuisine read(String name);
}
