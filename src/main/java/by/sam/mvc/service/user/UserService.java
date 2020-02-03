package by.sam.mvc.service.user;

import by.sam.mvc.entity.user.UserEntity;

public interface UserService {
    void create(UserEntity userEntity);
    UserEntity read(String email);
    Long getUserCount(String email);
    void update(UserEntity userEntity);
    boolean isVerifyUser(String id);

}
