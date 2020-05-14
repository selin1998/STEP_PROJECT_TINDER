package dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);
    int getMaxId();
    List<T> getAll();
    boolean add(T object);
    boolean remove(int id);
}
