package by.sam.mvc.repository.menu.dish;

import by.sam.mvc.models.menu.Dish;
import by.sam.mvc.repository.menu.CrudDao;

public interface DishDao extends CrudDao<Dish> {
    int isExist(Dish dish);
}
