package marttos.me.todo.model;

import java.io.Serializable;
import java.util.List;

public class WeekDay implements Serializable
{
    private String day;
    private List<Todo> tasks;

    public WeekDay(String day, List<Todo> tasks)
    {
        setDay(day);
        setTasks(tasks);
    }

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public List<Todo> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Todo> tasks)
    {
        this.tasks = tasks;
    }
}
