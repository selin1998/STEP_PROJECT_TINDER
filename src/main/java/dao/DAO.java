package dao;

import db.DatabaseConnection;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;

public interface DAO<T> {

    T get(int id);
    int getMaxId();
    List<T> getAllBy(Predicate<T> p);
    List<T> getAll();
    boolean add(T object);
    boolean remove(int id);
}
