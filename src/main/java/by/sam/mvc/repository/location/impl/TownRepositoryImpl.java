package by.sam.mvc.repository.location.impl;


import by.sam.mvc.models.location.Town;
import by.sam.mvc.repository.location.TownRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class TownRepositoryImpl implements TownRepository {

    //SQL
    private static String FIND_ALL_QUERY = "SELECT * FROM towns";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(Town town) {
        manager.persist(town);
    }

    @Override
    public void delete(int id) {
        Town town = read(id);
        manager.remove(town);
    }

    @Override
    public void update(Town town) {
        manager.refresh(town);
    }

    @Override
    public Town read(int id) {
        return manager.find(Town.class, id);
    }

    //todo no nativeQuery


    @Override
    public List<Town> findAll() {
          return manager.createQuery("from Town", Town.class).getResultList();
    }
}
