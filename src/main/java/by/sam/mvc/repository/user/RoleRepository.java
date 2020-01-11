package by.sam.mvc.repository.user;

import by.sam.mvc.models.user.Role;
import by.sam.mvc.repository.Repository;

public interface RoleRepository {

    Role read(int id);
}
