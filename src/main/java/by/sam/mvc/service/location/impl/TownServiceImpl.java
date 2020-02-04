package by.sam.mvc.service.location.impl;


import by.sam.mvc.entity.location.Town;
import by.sam.mvc.model.TownDto;
import by.sam.mvc.repository.location.TownRepository;
import by.sam.mvc.service.location.TownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Transactional
    @Override
    public void create(Town town) {
        townRepository.create(town);
    }

    @Transactional
    @Override
    public Town read(int id) {
        return townRepository.read(id);
    }

    @Transactional
    @Override
    public void update(Town town) {
        townRepository.update(town);
    }

    @Transactional
    @Override
    public void delete(int id) {
        townRepository.delete(id);
    }

    @Transactional
    @Override
    public List<TownDto> findAllTownDto() {
        return townRepository.findAll().stream().map(d -> new TownDto(d.getId(), d.getName())).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Town> findAll() {
        return townRepository.findAll();
    }
}
