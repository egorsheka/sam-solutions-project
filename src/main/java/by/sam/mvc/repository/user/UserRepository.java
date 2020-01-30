package by.sam.mvc.repository.user;

import by.sam.mvc.entity.user.Role;
import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.repository.Repository;

public interface UserRepository extends Repository<UserEntity> {

    UserEntity read(String email);
    Role readRole(String role);
    boolean isVerifyUser(String id);

}
