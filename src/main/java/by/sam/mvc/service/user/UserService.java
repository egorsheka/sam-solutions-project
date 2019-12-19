package by.sam.mvc.service.user;

import by.sam.mvc.models.user.UserEntity;

public interface UserService {
    void create(UserEntity userEntity);
    UserEntity read(String email);

}
