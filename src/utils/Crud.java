package utils;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Crud<T> {
    Optional<T> get(int id) throws SQLException;
    Collection<T> getAll() throws SQLException;
    boolean create(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
}
