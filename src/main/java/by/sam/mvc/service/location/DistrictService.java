package by.sam.mvc.service.location;

import by.sam.mvc.models.location.District;
import by.sam.mvc.models.location.Town;
import by.sam.mvc.service.Service;

import java.util.List;

public interface DistrictService extends Service<District> {
    List<District> getDistrictListByTown(Town town);
}
