package by.sam.mvc.service.location;

import by.sam.mvc.model.DistrictDto;
import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.service.Service;

import java.util.List;

public interface DistrictService extends Service<District> {
    List<District> getDistrictListByTown(Town town);
    List<DistrictDto> getDistrictDtoListByTown(Town town);
}
