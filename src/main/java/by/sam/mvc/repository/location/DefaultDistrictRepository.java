package by.sam.mvc.repository.location;

import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DefaultDistrictRepository implements DistrictRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void create(District district) {

        Town town = district.getTown();
        if (town.getId() == 0) {
            manager.persist(town);
        } else {
            town = manager.find(Town.class, town.getId());
        }
        town.addDistrict(district);
        district.setTown(town);

        manager.persist(district);
    }


    @Transactional
    @Override
    public District read(int id) {

        District district = manager.find(District.class, id);
        Town town = manager.find(Town.class, district.getId());
        district.setTown(town);
        return district;

    }

    @Transactional
    @Override
    public void update(District district) {

        District updateDistrict = manager.find(District.class, district.getId());

        updateDistrict.setName(district.getName());

        Town town = district.getTown();
        if (town.getId() == 0) {
            manager.persist(town);
        } else {
            town = manager.find(Town.class, town.getId());
        }
        town.addDistrict(updateDistrict);
        updateDistrict.setTown(town);

        manager.merge(district);


    }

    @Transactional
    @Override
    public void delete(int id) {
        District district = manager.find(District.class, id);
        manager.remove(district);
    }

    @Override
    public List<District> findAll() {
        return null;
    }
}
