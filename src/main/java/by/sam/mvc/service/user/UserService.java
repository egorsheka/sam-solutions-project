package by.sam.mvc.service.user;

import by.sam.mvc.models.user.UserEntity;

public interface UserService {
    UserEntity read(String email);

}
