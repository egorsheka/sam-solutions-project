package by.sam.mvc.repository.location;

import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class DefaultTownRepository implements TownRepository {

    //SQL
    private static String FIND_ALL_QUERY = "SELECT * FROM towns";

    @PersistenceContext
    private EntityManager manager;

    private final DistrictRepository districtRepository;

    public DefaultTownRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Transactional
    @Override
    public void create(Town object) {
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Town object) {

    }

    @Override
    public Town read(int id) {
        return null;
    }

    @Transactional
    @Override
    public List<Town> findAll() {
        return manager.createNativeQuery(FIND_ALL_QUERY, Town.class).getResultList();
    }
}
