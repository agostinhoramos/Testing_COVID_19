package pt.ipg.application.testingcovid_19;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    Fragment fragment ;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                fragment = new SignupActivity();
                return fragment;
            case 1:
                fragment = new SigninActivity();
                return fragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SignUp";
            case 1:
                return "SignIn";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return  NUM_ITEMS;
    }
}
