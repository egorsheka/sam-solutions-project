package by.sam.mvc.repository.user.impl;

import by.sam.mvc.models.user.Role;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.repository.user.UserRepository;
import by.sam.mvc.service.user.UserService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserEntity read(String email) {
        return manager.createQuery("from UserEntity u where u.email = :email", UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();

    }

    @Override
    public Role readRole(String role) {
       return manager.createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", role)
                .getSingleResult();
    }

    @Override
    public boolean isVerifyUser(String id) {

        return manager.createQuery("from UserEntity u where u.idVerification = :idVerification", UserEntity.class)
                .setParameter("idVerification", id)
                .getResultList()
                .stream()
                .findFirst()
                .isPresent();
    }

    @Override
    public void create(UserEntity userEntity) {
        manager.persist(userEntity);
    }

    @Override
    public UserEntity read(int id) {
        UserEntity userEntity = manager.find(UserEntity.class, id);
        return userEntity;
    }

    @Override
    public void update(UserEntity user) {
        manager.merge(user);
    }

    @Override
    public void delete(int id) {
        UserEntity userEntity = manager.find(UserEntity.class, id);
        manager.remove(userEntity);
    }
}
