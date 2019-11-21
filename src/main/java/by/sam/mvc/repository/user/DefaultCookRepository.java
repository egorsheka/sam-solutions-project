package by.sam.mvc.repository.user;

import by.sam.mvc.models.WorkTime;
import by.sam.mvc.models.location.District;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.WorkTimeRepository;
import by.sam.mvc.repository.location.DistrictRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DefaultCookRepository implements CookRepository {

    @PersistenceContext
    private EntityManager manager;

    private final DistrictRepository districtRepository;
    private final WorkTimeRepository workTimeRepository;

    //SQL
    private static String FIND_ALL_QUERY = "SELECT * FROM cooks";



    public DefaultCookRepository(DistrictRepository districtRepository, WorkTimeRepository workTimeRepository) {
        this.districtRepository = districtRepository;
        this.workTimeRepository = workTimeRepository;
    }

    @Transactional
    @Override
    public void create(Cook cook) {
        for(District district: cook.getDistricts()){
            districtRepository.create(district);
        }
        manager.persist(cook);

    }

    //TODO test
    @Transactional
    @Override
    public Cook read(int id) {
        Cook cook = manager.find(Cook.class, id);
        Hibernate.initialize(cook.getDistricts());
        return cook;
    }


    //TODO test
    @Transactional
    @Override
    public void update(Cook cook) {

        Cook updateCook = manager.find(Cook.class, cook.getId());

        updateCook.setName(cook.getName());
        updateCook.setSurname(cook.getSurname());
        updateCook.setEmail(cook.getEmail());
        updateCook.setPassword(cook.getPassword());
        updateCook.setBirthday(cook.getBirthday());
        updateCook.setMobile(cook.getMobile());
        updateCook.setStatus(cook.getStatus());


        if(!(cook.getDistricts() == null || cook.getDistricts().isEmpty())) {
            updateCook.setDistricts(cook.getDistricts());
        }

        updateOnlyDistricts(updateCook);
        updateOnlyWeekDays(updateCook);

    }
    //TODO test
    @Transactional
    @Override
    public void updateOnlyDistricts(Cook cook) {
        if(!(cook.getDistricts() == null || cook.getDistricts().isEmpty())) {
            for (District district : cook.getDistricts()) {
                if (district.getId() == 0) {
                    districtRepository.create(district);
                } else {
                    districtRepository.update(district);
                }
            }
            manager.merge(cook);
        }

    }

    //TODO test
    @Transactional
    @Override
    public void updateOnlyWeekDays(Cook cook) {
        if(!(cook.getWorkTime() == null || cook.getWorkTime().isEmpty())) {
            for (WorkTime day : cook.getWorkTime()) {
                if (day.getId() == 0) {
                    workTimeRepository.create(day);
                } else {
                    workTimeRepository.update(day);
                }
            }
            manager.merge(cook);
        }

    }

    @Transactional
    @Override
    public void delete(int id) {
        Cook cook = manager.find(Cook.class, id);
        manager.remove(cook);
    }


    @Transactional
    @Override
    public List<Cook> findAll() {
        return manager.createNativeQuery(FIND_ALL_QUERY, Cook.class).getResultList();
    }


    private void merge(Cook cook){
        manager.merge(cook);
    }

}
