package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.Cook;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CookRepositoryImpl implements CookRepository {

    @PersistenceContext
    private EntityManager manager;



    @Override
    public void create(Cook cook) {
        manager.persist(cook);
    }


    @Override
    public Cook read(int id) {
        Cook cook = manager.find(Cook.class, id);
        Hibernate.initialize(cook.getDistricts());
        Hibernate.initialize(cook.getWorkTime());
        Hibernate.initialize(cook.getMenu());
        return cook;
    }


    @Override
    public void update(Cook cook) {
        manager.merge(cook);
    }


    @Override
    public void delete(int id) {
        Cook cook = manager.find(Cook.class, id);
        manager.remove(cook);
    }


    @Override
    public List<Cook> findAll() {
        return manager.createQuery("from Cook", Cook.class).getResultList();
    }


}
