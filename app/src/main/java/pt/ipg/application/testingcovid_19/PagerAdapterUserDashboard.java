package pt.ipg.application.testingcovid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterUserDashboard extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 1;
    Fragment fragment = null;
    public PagerAdapterUserDashboard(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new UserDashboardHistoryFragment();
                return fragment;
            default:
                return fragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My history";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return  NUM_ITEMS;
    }
}