package by.sam.mvc.repository.location;

import by.sam.mvc.entity.location.Town;
import by.sam.mvc.repository.Repository;

import java.util.List;

public interface TownRepository extends Repository<Town> {
    List<Town> findAll();
}
