package by.sam.mvc.repository.menu;

import by.sam.mvc.models.menu.Menu;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    public void findAll() {
        List<Menu> menu = new ArrayList<>();

    }

}