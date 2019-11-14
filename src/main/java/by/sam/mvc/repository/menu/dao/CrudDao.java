package by.sam.mvc.repository.menu.dao;

import java.util.List;

public interface CrudDao<T> {

    void create(T object);
    T read(int id);
    void update(T object);
    void delete(int id);

    List<T> findAll();
}
