package by.sam.mvc.service.user.impl;

import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.repository.user.UserRepository;
import by.sam.mvc.service.user.UserService;
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
    public void create(UserEntity userEntity) {
        userEntity.setRole(userRepository.readRole(userEntity.getRole().getName()));
        userRepository.create(userEntity);
    }

    @Transactional
    @Override
    public UserEntity read(String email) {
        return userRepository.read(email);
    }

    @Transactional
    @Override
    public Long getUserCount(String email) {
        return userRepository.getUserCount(email);
    }

    @Transactional
    @Override
    public void update(UserEntity userEntity) {
        userRepository.update(userEntity);
    }

    @Transactional
    @Override
    public boolean isVerifyUser(String id) {
        return userRepository.isVerifyUser(id);
    }
}
