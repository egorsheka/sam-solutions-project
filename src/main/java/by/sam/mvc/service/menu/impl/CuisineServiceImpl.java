package by.sam.mvc.service.menu.impl;

import by.sam.mvc.models.menu.Cuisine;
import by.sam.mvc.repository.menu.CuisineRepository;
import by.sam.mvc.service.menu.CuisineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository cuisineRepository;

    public CuisineServiceImpl(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }


    @Transactional
    @Override
    public void create(Cuisine cuisine) {
        cuisineRepository.create(cuisine);
    }

    @Transactional
    @Override
    public Cuisine read(int id) {
        return cuisineRepository.read(id);
    }

    @Transactional
    @Override
    public void update(Cuisine cuisine) {
        cuisineRepository.update(cuisine);
    }

    @Transactional
    @Override
    public void delete(int id) {
        cuisineRepository.delete(id);
    }

    @Override
    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    @Transactional
    @Override
    public Cuisine read(String name) {
        return cuisineRepository.read(name);
    }

}
