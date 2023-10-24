package ap.mini_project.server.db;

import java.util.LinkedList;

public interface DBSet <T> {
    T get(String username);
    LinkedList<T> all();
    void save(T t);
    void remove(T t);
    void update(T t);

}
