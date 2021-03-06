package by.sam.mvc.service.location;

import by.sam.mvc.models.location.Town;
import by.sam.mvc.service.Service;

import java.util.List;

public interface TownService extends Service<Town> {
    List<Town> findAll();
}
