package by.sam.mvc.repository.menu.impl;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.repository.menu.CuisineRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


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


    @Override
    public List<Cuisine> findAll() {
        return manager.createQuery("from Cuisine", Cuisine.class).getResultList();
    }

    @Override
    public Cuisine read(String name) {
        return manager.createQuery("from Cuisine c where c.name = :name", Cuisine.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
