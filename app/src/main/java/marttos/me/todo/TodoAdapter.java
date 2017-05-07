package marttos.me.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import marttos.me.todo.model.Todo;

public class TodoAdapter extends BaseAdapter
{
    private List<Todo> todos;

    public TodoAdapter(List<Todo> todos)
    {
        this.todos = todos;
    }

    @Override
    public int getCount()
    {
        return todos.size();
    }

    @Override
    public Todo getItem(int position)
    {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_task_todo, parent, false);

            holder = new ViewHolder();
            holder.complete = (TextView) convertView.findViewById(R.id.complete);
            holder.task = (TextView) convertView.findViewById(R.id.task);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Todo todo = getItem(position);

        holder.complete.setText(todo.isComplete() ? "Finalizado" : "Pendente");
        holder.task.setText(todo.getTask());

        return convertView;
    }

    private static class ViewHolder
    {
        private TextView task;
        private TextView complete;
    }
}
