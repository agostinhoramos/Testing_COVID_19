package pt.ipg.application.testingcovid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterDashboard extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    Fragment fragment = null;
    public PagerAdapterDashboard(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new DoctorDashboardListFragment();
                return fragment;
            case 1:
                fragment = new DoctorDashboardCreateFragment();
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
                return "List";
            case 1:
                return "Create";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return  NUM_ITEMS;
    }
}