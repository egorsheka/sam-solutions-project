package by.sam.mvc.service.user;

import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserEntity read(String email) {
        return userRepository.read(email);
    }
}
