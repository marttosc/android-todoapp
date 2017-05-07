package marttos.me.todo.model;

import android.content.Context;

import java.io.Serializable;

import marttos.me.todo.dao.TodoDao;

public class Todo implements Serializable
{
    private Long id;
    private String task;
    private String weekDay;
    private boolean complete;

    public Todo()
    {
        setId(-1L);
        setComplete(false);
    }

    public Todo(String task, String weekDay)
    {
        this(task, weekDay, false);
    }

    public Todo(String task, String weekDay, boolean complete)
    {
        this();

        setTask(task);
        setWeekDay(weekDay);
        setComplete(complete);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTask()
    {
        return task;
    }

    public void setTask(String task)
    {
        this.task = task;
    }

    public String getWeekDay()
    {
        return weekDay;
    }

    public void setWeekDay(String weekDay)
    {
        this.weekDay = weekDay;
    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public Todo save(Context context)
    {
        TodoDao dao = new TodoDao(context);

        dao.insert(this);

        return this;
    }
}
