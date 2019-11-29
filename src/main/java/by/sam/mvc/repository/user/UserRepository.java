package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.repository.Repository;

public interface UserRepository{

    UserEntity read(String email);


}
