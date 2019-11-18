package by.sam.mvc.repository;

import java.util.List;

public interface Repository<T> {
    void create(T object);
    T read(int id);
    void update(T object);
    void delete(int id);
    List<T> findAll();
}
