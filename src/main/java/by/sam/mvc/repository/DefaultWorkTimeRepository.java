package by.sam.mvc.repository;

import by.sam.mvc.models.WorkTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DefaultWorkTimeRepository implements WorkTimeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void create(WorkTime object) {
        manager.persist(object);
    }

    @Transactional
    @Override
    public WorkTime read(int id) {
        return null;
    }

    @Transactional
    @Override
    public void update(WorkTime object) {
        WorkTime updateWorkTime = manager.find(WorkTime.class, object.getId());

        updateWorkTime.setDay(object.getDay());
        updateWorkTime.setStartTime(object.getStartTime());
        updateWorkTime.setEndTime(object.getEndTime());
        manager.merge(object);
    }

    @Transactional
    @Override
    public void delete(int id) {

    }

    @Transactional
    @Override
    public List<WorkTime> findAll() {
        return null;
    }
}
