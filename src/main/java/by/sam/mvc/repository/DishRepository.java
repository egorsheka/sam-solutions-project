package by.sam.mvc.repository;

import by.sam.mvc.models.menu.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
