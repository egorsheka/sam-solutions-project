package by.sam.mvc.repository.user;

import by.sam.mvc.models.location.District;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface CookRepository extends Repository<Cook> {
    void updateOnlyDistricts(Cook cook);
    public void updateOnlyWeekDays(Cook cook);
}
