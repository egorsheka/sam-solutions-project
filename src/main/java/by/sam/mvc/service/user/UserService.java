package by.sam.mvc.service.user;

import by.sam.mvc.models.user.UserEntity;

import java.util.Optional;

public interface UserService {
    void create(UserEntity userEntity);
    UserEntity read(String email);
    void update(UserEntity userEntity);
    boolean isVerifyUser(String id);

}
