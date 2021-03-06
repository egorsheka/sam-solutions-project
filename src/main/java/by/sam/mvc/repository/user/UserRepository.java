package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.Role;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.repository.Repository;

public interface UserRepository extends Repository<UserEntity> {

    UserEntity read(String email);
    Role readRole(String role);

}
