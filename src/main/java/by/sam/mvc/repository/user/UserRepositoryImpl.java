package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
