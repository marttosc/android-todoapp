package marttos.me.todo.dao;

import android.database.Cursor;

public interface Dao<T>
{
    boolean insert(T model);
    void update(T model);
    void delete(T model);
    T getModel(Long id);
    T toModel(Cursor c);

    String SQL_CREATE_ENTRIES = "";
}
