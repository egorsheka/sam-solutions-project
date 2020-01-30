package by.sam.mvc.service.location.impl;

import by.sam.mvc.model.DistrictDto;
import by.sam.mvc.entity.location.District;
import by.sam.mvc.entity.location.Town;
import by.sam.mvc.repository.location.DistrictRepository;
import by.sam.mvc.service.location.DistrictService;
import by.sam.mvc.service.location.TownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final TownService townService;

    public DistrictServiceImpl(DistrictRepository districtRepository, TownService townService) {
        this.districtRepository = districtRepository;
        this.townService = townService;
    }

    @Transactional
    @Override
    public void create(District district) {
        Town town = district.getTown();
        if (town.getId() == 0) {
            townService.create(town);
        } else {
            town = townService.read(town.getId());
        }
        town.addDistrict(district);
        district.setTown(town);

        districtRepository.create(district);
    }

    @Transactional
    @Override
    public District read(int id) {

        District district = districtRepository.read(id);
        Town town = townService.read(district.getTown().getId());
        district.setTown(town);
        return district;
    }

    @Transactional
    @Override
    public void update(District district) {
        District updateDistrict = districtRepository.read(district.getId());

        updateDistrict.setName(district.getName());

        Town town = district.getTown();
        if (town.getId() == 0) {
            townService.create(town);
        } else {
            town = townService.read(town.getId());
        }

        town.addDistrict(updateDistrict);
        updateDistrict.setTown(town);

        districtRepository.update(district);
    }

    @Transactional
    @Override
    public void delete(int id) {
        districtRepository.delete(id);
    }

    @Transactional
    @Override
    public List<DistrictDto> getDistrictDtoListByTown(Town town) {
        return districtRepository.getDistrictListByTown(town).stream().map(d -> new DistrictDto(d.getId(), d.getName())).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<District> getDistrictListByTown(Town town) {
        return districtRepository.getDistrictListByTown(town);
    }
}
