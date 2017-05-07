package marttos.me.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import marttos.me.todo.model.Todo;

public class TodoDao implements Dao<Todo>
{
    public static final String TABLE_NAME = "todos";

    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_WEEKDAY = "weekday";
    public static final String COLUMN_COMPLETE = "complete";

    private static final String BOOL_TYPE = " INTEGER DEFAULT 0";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                " _id INTEGER PRIMARY KEY" + COMMA_SEP +
                COLUMN_TASK + TEXT_TYPE + COMMA_SEP +
                COLUMN_WEEKDAY + TEXT_TYPE + COMMA_SEP +
                COLUMN_COMPLETE + BOOL_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private TodoDatabase database;

    public TodoDao(Context context)
    {
        this.database = new TodoDatabase(context);
    }

    private TodoDatabase db()
    {
        return this.database;
    }

    @Override
    public boolean insert(Todo model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, model.getTask());
        values.put(COLUMN_WEEKDAY, model.getWeekDay());
        values.put(COLUMN_COMPLETE, model.isComplete() ? 1 : 0);

        model.setId(db.insert(TABLE_NAME, null ,values));

        return model.getId() != -1;
    }

    @Override
    public void update(Todo model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, model.getTask());
        values.put(COLUMN_WEEKDAY, model.getWeekDay());
        values.put(COLUMN_COMPLETE, model.isComplete() ? 1 : 0);

        db.update(TABLE_NAME, values, "_id = " + model.getId(), null);
    }

    @Override
    public void delete(Todo model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        model.setId((long) db.delete(TABLE_NAME, "_id = ?", new String[] { model.getId().toString() }));
    }

    @Override
    public Todo getModel(Long id)
    {
        SQLiteDatabase db = db().getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE _id = " + id, null);

        if (c == null)
        {
            return null;
        }

        c.moveToFirst();
        db.close();

        return toModel(c);
    }

    public List<Todo> getModels()
    {
        List<Todo> todos = new LinkedList<>();

        SQLiteDatabase db = db().getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (c.moveToFirst())
        {
            do
            {
                Todo todo = toModel(c);

                if (todo != null)
                {
                    todos.add(todo);
                }
            }
            while (c.moveToNext());
        }

        if (c != null && !c.isClosed())
        {
            c.close();
        }

        return todos;
    }

    @Override
    public Todo toModel(Cursor c)
    {
        if (c.getCount() == 0)
        {
            return null;
        }

        Todo todo = new Todo();

        todo.setId(c.getLong(c.getColumnIndex("_id")));
        todo.setTask(c.getString(c.getColumnIndex(COLUMN_TASK)));
        todo.setWeekDay(c.getString(c.getColumnIndex(COLUMN_WEEKDAY)));
        todo.setComplete(c.getInt(c.getColumnIndex(COLUMN_COMPLETE)) == 1);

        return todo;
    }
}
