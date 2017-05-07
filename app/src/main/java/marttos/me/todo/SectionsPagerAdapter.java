package marttos.me.todo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import marttos.me.todo.model.WeekDay;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private List<WeekDay> week;

    public SectionsPagerAdapter(FragmentManager fm, List<WeekDay> week)
    {
        super(fm);

        this.week = week;
    }

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance(week.get(position));
    }

    @Override
    public int getCount() {
        return week.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return week.get(position).getDay();
    }
}
