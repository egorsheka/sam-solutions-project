package by.sam.mvc.repository.user.impl;

import by.sam.mvc.models.menu.Menu;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.user.CookRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//TODO
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
        hibernateInitialize(cook);
        return cook;
    }
    @Override
    public Cook read(String email) {
        Cook cook =  manager.createQuery("from Cook c where c.email = :email", Cook.class)
                .setParameter("email", email)
                .getSingleResult();
        hibernateInitialize(cook);
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

    @Override
    public List<Menu> getMenuListByCookId(int id) {
        return manager.createQuery("select cook.menu from Cook cook where cook.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Cook> getCooksByDistrictId(int id) {
        return manager.createQuery("select cook from Cook cook join cook.districts d where d.id =:id")
                .setParameter("id", id)
                .getResultList();
    }
    // TODO
    // Is ok?
    private void hibernateInitialize(Cook cook){
        if(!cook.getDistricts().isEmpty())
            Hibernate.initialize(cook.getDistricts());
        if(!cook.getWorkTime().isEmpty())
            Hibernate.initialize(cook.getWorkTime());
        if(!cook.getMenu().isEmpty()){
            Hibernate.initialize(cook.getMenu());
            for(Menu menu: cook.getMenu()){
                Hibernate.initialize(menu.getDishes());
            }
        }
    }

}
