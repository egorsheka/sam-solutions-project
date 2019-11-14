package by.sam.mvc.repository.menu;
import by.sam.mvc.models.menu.Cuisine;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void add(Cuisine dish) {
        em.persist(dish);
    }
}
