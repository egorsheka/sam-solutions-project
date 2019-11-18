package by.sam.mvc.repository.user;

import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.user.Cook;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DefaultCookRepository implements CookRepository {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    @Override
    public void create(Cook cook) {
        Town town = cook.getTown();
        if (town.getId() == 0) {
            manager.persist(town);
        } else {
            town = manager.find(Town.class, town.getId());
        }
        town.addCook(cook);
        cook.setTown(town);
        manager.persist(town);



    }

    //TODO test
    @Transactional
    @Override
    public Cook read(int id) {


        Cook cook = manager.find(Cook.class, id);
        Town town = manager.find(Town.class, cook.getTown().getId());
        cook.setTown(town);
        return cook;

    }


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
        updateCook.setWeekForm(cook.getWeekForm());

        Town town = cook.getTown();

        if(town.getId() == 0){
            manager.persist(town);
        }else{
            town = manager.find(Town.class, town.getId());
        }

        town.addCook(updateCook);
        updateCook.setTown(town);

        manager.merge(cook);

    }


    @Transactional
    @Override
    public void delete(int id) {
        Cook cook = manager.find(Cook.class, id);
        manager.remove(cook);
    }


    //TODO test
    @Override
    public List<Cook> findAll() {
        return null;
    }
}
