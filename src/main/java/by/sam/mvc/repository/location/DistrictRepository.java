package by.sam.mvc.repository.location;

import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface DistrictRepository extends Repository<District> {

    List<District> getDistrictListByTown(Town town);

}
