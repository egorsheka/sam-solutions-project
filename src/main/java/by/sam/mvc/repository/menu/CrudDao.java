package by.sam.mvc.repository.menu;

import java.util.List;

public interface CrudDao<T> {

    void create(T object);
    T read(int id);
    void update(T object);
    void delete(int id);

    List<T> findAll();
}
