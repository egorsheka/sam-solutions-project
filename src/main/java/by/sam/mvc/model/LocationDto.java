package by.sam.mvc.model;

import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;

import java.util.List;

public class LocationDto {
    private Town town;
    private List<District> districts;

    public LocationDto() {}

    public LocationDto(Town town, List<District> districts) {
        this.town = town;
        this.districts = districts;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}