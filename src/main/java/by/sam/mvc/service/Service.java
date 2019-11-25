package by.sam.mvc.service;

public interface Service<T> {
    void create(T object);
    T read(int id);
    void update(T object);
    void delete(int id);
}
