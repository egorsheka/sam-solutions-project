package by.sam.mvc.repository.worktime;

import by.sam.mvc.models.worktime.WorkTime;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class WorkTimeRepositoryImpl implements WorkTimeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(WorkTime time) {
        manager.persist(time);
    }

    @Override
    public WorkTime read(int id) {
        return manager.find(WorkTime.class, id);
    }

    @Override
    public void update(WorkTime time) {
        manager.merge(time);
    }

    @Override
    public void delete(int id) {
        WorkTime workTime = read(id);
        manager.remove(workTime);
    }


}
