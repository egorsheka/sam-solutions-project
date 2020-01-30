package by.sam.mvc.service.user.impl;

import by.sam.mvc.entity.user.Role;
import by.sam.mvc.repository.user.RoleRepository;
import by.sam.mvc.service.user.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Transactional
    @Override
    public Role read(int id) {
        return roleRepository.read(id);
    }

}
