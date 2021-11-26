package dao;
import java.util.List;

public interface Dao<T> {

    List<T> findAll();

    T findById(int id);

}
