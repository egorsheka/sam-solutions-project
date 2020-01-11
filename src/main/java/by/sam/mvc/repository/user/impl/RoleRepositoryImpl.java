package by.sam.mvc.repository.user.impl;

import by.sam.mvc.models.user.Role;
import by.sam.mvc.repository.user.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Role read(int id) {
        return manager.find(Role.class, id);
    }


}
