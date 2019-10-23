package by.sam.mvc.repository.menu;

import java.util.List;

public interface CrudDao<T> {

    void create(T object);
    T read(int id);
    T update(int id);
    void delete(int id);

    List<T> findAll();
}
