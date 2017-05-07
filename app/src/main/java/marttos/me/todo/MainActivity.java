package marttos.me.todo;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import marttos.me.todo.dao.TodoDao;
import marttos.me.todo.model.Todo;
import marttos.me.todo.model.WeekDay;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        loadTodos();
    }

    private void loadTodos()
    {
        Context context = getApplicationContext();

        TodoDao todoDao = new TodoDao(context);

        List<Todo> todos = todoDao.getModels();

        List<Todo> monday = new LinkedList<>();
        List<Todo> tuesday = new LinkedList<>();
        List<Todo> wednesday = new LinkedList<>();
        List<Todo> thursday = new LinkedList<>();
        List<Todo> friday = new LinkedList<>();
        List<Todo> saturday = new LinkedList<>();
        List<Todo> sunday = new LinkedList<>();

        // If has todos...
        for (Todo todo : todos)
        {
            List<Todo> list = null;

            switch (todo.getWeekDay().toLowerCase())
            {
                case "monday":
                    list = monday;
                    break;
                case "tuesday":
                    list = tuesday;
                    break;
                case "wednesday":
                    list = wednesday;
                    break;
                case "thursday":
                    list = thursday;
                    break;
                case "friday":
                    list = friday;
                    break;
                case "saturday":
                    list = saturday;
                    break;
                case "sunday":
                    list = sunday;
                    break;
                default:
                    break;
            }

            if (list != null)
            {
                list.add(todo);
            }
        }

        // If doesn't have todos...
        if (todos.isEmpty())
        {
            monday = Arrays.asList(
                    new Todo("Wake up", "monday", true).save(context),
                    new Todo("Brush teeth", "monday", true).save(context),
                    new Todo("Take breakfast", "monday").save(context),
                    new Todo("Meeting with John", "monday").save(context)
            );

            tuesday = Arrays.asList(
                    new Todo("Wake up", "tuesday", true).save(context),
                    new Todo("Brush teeth", "tuesday", true).save(context),
                    new Todo("Take breakfast", "tuesday", true).save(context),
                    new Todo("Meeting with Carol", "tuesday").save(context)
            );

            wednesday = Arrays.asList(
                    new Todo("Wake up", "wednesday").save(context),
                    new Todo("Brush teeth", "wednesday").save(context),
                    new Todo("Take breakfast", "wednesday").save(context),
                    new Todo("Meeting with Joshua", "wednesday").save(context)
            );

            thursday = Arrays.asList(
                    new Todo("Wake up", "thursday").save(context),
                    new Todo("Brush teeth", "thursday").save(context),
                    new Todo("Take breakfast", "thursday").save(context),
                    new Todo("Meeting with Anne", "thursday").save(context)
            );

            friday = Arrays.asList(
                    new Todo("Wake up", "friday").save(context),
                    new Todo("Brush teeth", "friday").save(context),
                    new Todo("Take breakfast", "friday").save(context),
                    new Todo("Meeting with Natalie", "friday").save(context)
            );

            saturday = Arrays.asList(
                    new Todo("Sleep all day", "saturday", true).save(context)
            );

            sunday = Arrays.asList(
                    new Todo("Sleep all day", "sunday", true).save(context)
            );
        }

        List<WeekDay> week = new LinkedList<>();

        week.add(new WeekDay("Monday", monday));
        week.add(new WeekDay("Tuesday", tuesday));
        week.add(new WeekDay("Wednesday", wednesday));
        week.add(new WeekDay("Thursday", thursday));
        week.add(new WeekDay("Friday", friday));
        week.add(new WeekDay("Saturday", saturday));
        week.add(new WeekDay("Sunday", sunday));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), week);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
