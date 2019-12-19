package by.sam.mvc.repository.menu.impl;
import by.sam.mvc.models.menu.Cuisine;
import by.sam.mvc.repository.menu.CuisineRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager manager;



    @Override
    public void create(Cuisine cuisine) {
        manager.persist(cuisine);
    }

    @Override
    public Cuisine read(int id) {
        return manager.find(Cuisine.class, id);
    }

    @Override
    public void update(Cuisine cuisine) {
        manager.refresh(cuisine);
    }

    @Override
    public void delete(int id) {
        Cuisine cuisine = read(id);
        manager.remove(cuisine);
    }


}
