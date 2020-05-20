package dao;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public interface DAO<T> {

    T get(int id);
    List<T> getAllBy(Predicate<T> p);
    List<T> getAll();
    boolean add(T object) ;
    boolean remove(T object);
}
