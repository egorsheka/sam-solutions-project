package by.sam.mvc.repository;

import by.sam.mvc.models.Menu;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MenuRepository{

    @Autowired
    private SessionFactory sessionFactory;


    public void add(Menu user) {
        sessionFactory.getCurrentSession().save(user);
    }

}