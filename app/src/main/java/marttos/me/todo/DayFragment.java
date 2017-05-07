package marttos.me.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import marttos.me.todo.model.WeekDay;

public class DayFragment extends Fragment
{
    private static final String WEEK_DAY = "week_day";

    public DayFragment()
    {
        //
    }

    public static DayFragment newInstance(WeekDay weekDay)
    {
        Bundle args = new Bundle();
        args.putSerializable(WEEK_DAY, weekDay);

        DayFragment fragment = new DayFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Bundle arguments = getArguments();
        WeekDay weekDay = (WeekDay) arguments.getSerializable(WEEK_DAY);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new TodoAdapter(weekDay.getTasks()));
        listView.setDivider(null);

        return rootView;
    }
}
