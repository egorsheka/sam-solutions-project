package by.sam.mvc.repository.user;

import by.sam.mvc.entity.user.Cook;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface CookRepository extends Repository<Cook> {
    Cook read(String email);
    List<Cook> findAll();
    List<Cook> getCooksByDistrictId(int id);
    Cook getCookByMenuId(int menuId);
}
