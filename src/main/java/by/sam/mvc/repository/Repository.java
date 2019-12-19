package by.sam.mvc.repository;

public interface Repository<T> {
    void create(T object);
    T read(int id);
    void update(T object);
    void delete(int id);
}
